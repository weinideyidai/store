package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.ShopCarVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShopCarMapper {

    @Select("<script>" +
            "SELECT * FROM shopcar " +
            "<where> " +
            " <if test=\"key.uid!=null and key.uid!=''\"> " +
            "and uid = #{key.uid}" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<ShopCarVO> findAllByPage(PageVO pageVO);

    @Select("SELECT * FROM shopcar where status = 1")
    List<ShopCarVO> findAll();

    @Update("UPDATE `shopcar` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `shopcar` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `shopcar`(uid,gid,count,status) values(#{uid},#{gid},#{count},#{status})")
    int save(ShopCarVO shopCarVO);

    @Select("select * from shopcar where id = #{id}")
    ShopCarVO findShopCarById(Integer id);

    @Update("<script>" +
            "UPDATE  shopcar " +
            "<set> " +
            " <if test=\"count!=null and count!=''\"> " +
            " count = #{count}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateShopCar(ShopCarVO shopCarVO);
}
