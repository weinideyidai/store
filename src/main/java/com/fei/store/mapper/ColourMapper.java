package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ColourMapper {

    @Select("<script>" +
            "SELECT * FROM colour " +
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

    @Select("SELECT * FROM colour where status = 1")
    List<ColourVO> findAll();

    @Update("UPDATE `colour` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `colour` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `colour`(name,status) values(#{name},#{status})")
    int save(ColourVO colourVO);

    @Select("select * from colour where id = #{id}")
    ColourVO findColourById(Integer id);

    @Update("<script>" +
            "UPDATE  colour " +
            "<set> " +
            " <if test=\"name!=null and name!=''\"> " +
            " name = #{name}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateBrands(ColourVO colourVO);

    @Select("select * from colour where name = #{name}")
    ColourVO findColourByName(String name);
}
