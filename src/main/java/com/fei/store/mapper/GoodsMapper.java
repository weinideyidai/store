package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.GoodsVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodsMapper {

    @Select("<script>" +
            "SELECT * FROM goods " +
            "<where> " +
            " <if test=\"key.name!=null and key.name!=''\"> " +
            "and name like  CONCAT('%', #{key.name}, '%')" +
            "</if> " +
            " <if test=\"key.price!=null and key.price!=''\"> " +
            "and price like  CONCAT('%', #{key.price}, '%')" +
            "</if> " +
            " <if test=\"key.bid!=null and key.bid!=''\"> " +
            "and bid = #{key.bid}" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<GoodsVO> findAllByPage(PageVO pageVO);

    @Select("SELECT * FROM goods where status = 1")
    List<GoodsVO> findAll();

    @Update("UPDATE `goods` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `goods` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `goods`(name,price,img,tid,bid,cid,sid,count,status) " +
            "values(#{name},#{price},#{img},#{tid},#{bid},#{cid},#{sid},#{count},#{status})")
    int save(GoodsVO goodsVO);

    @Select("select * from goods where id = #{id}")
    GoodsVO findGoodsById(Integer id);

    @Update("<script>" +
            "UPDATE  goods " +
            "<set> " +
            " <if test=\"name!=null and name!=''\"> " +
            " name = #{name}," +
            "</if> " +
            " <if test=\"price!=null and price!=''\"> " +
            " price = #{price}," +
            "</if> " +
            " <if test=\"count!=null and count!=''\"> " +
            " count = #{count}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateGoods(GoodsVO goodsVO);

    @Select("select * from goods where name=#{name} and price = #{price} " +
            "and img = #{img} and tid = #{tid} and bid = #{bid} and cid = #{cid}" +
            " and sid = #{sid} and count = #{count}")
    GoodsVO findGoodsByGoods(GoodsVO goodsVO);
}
