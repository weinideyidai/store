package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.BrandsVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrandsMapper {

    @Select("<script>" +
            "SELECT * FROM brands " +
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

    @Select("SELECT * FROM brands where status = 1")
    List<BrandsVO> findAll();

    @Update("UPDATE `brands` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `brands` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `brands`(name,img,status) values(#{name},#{img},#{status})")
    int save(BrandsVO brandsVO);

    @Select("select * from brands where id = #{id}")
    BrandsVO findBrandsById(Integer id);

    @Update("<script>" +
            "UPDATE  brands " +
            "<set> " +
            " <if test=\"name!=null and name!=''\"> " +
            " name = #{name}," +
            "</if> " +
            " <if test=\"img!=null and img!=''\"> " +
            " img = #{img}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateBrands(BrandsVO brandsVO);

    @Select("SELECT * FROM brands where name = #{name}")
    BrandsVO findBrandsByName(String name);
}
