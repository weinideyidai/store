package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.GoodTypeVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodTypeMapper {

    @Select("<script>" +
            "SELECT * FROM goodtype " +
            "<where> " +
            " <if test=\"key.name!=null and key.name!=''\"> " +
            "and name like  CONCAT('%', #{key.name}, '%')" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<BrandsVO> findAllByPage(PageVO pageVO);

    @Select("SELECT * FROM goodtype where status = 1")
    List<BrandsVO> findAll();

    @Update("UPDATE `goodtype` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `goodtype` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `goodtype`(name,status) values(#{name},#{status})")
    int save(GoodTypeVO goodTypeVO);

    @Select("select * from goodtype where id = #{id}")
    GoodTypeVO findGoodTypeById(Integer id);

    @Update("<script>" +
            "UPDATE  goodtype " +
            "<set> " +
            " <if test=\"name!=null and name!=''\"> " +
            " name = #{name}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateGoodType(GoodTypeVO goodTypeVO);

    @Select("select * from goodtype where name = #{name}")
    GoodTypeVO findGoodTypeByName(String name);
}
