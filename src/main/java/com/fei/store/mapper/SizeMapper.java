package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.SizeVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SizeMapper {

    @Select("<script>" +
            "SELECT * FROM size " +
            "<where> " +
            " <if test=\"key.size!=null and key.size!=''\"> " +
            "and size like  CONCAT('%', #{key.size}, '%')" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<SizeVO> findAllByPage(PageVO pageVO);

    @Select("SELECT * FROM size where status = 1")
    List<SizeVO> findAll();

    @Update("UPDATE `size` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `size` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `size`(`size`,status) values(#{size},#{status})")
    int save(SizeVO colourVO);

    @Select("select * from size where id = #{id}")
    SizeVO findSizeById(Integer id);

    @Update("<script>" +
            "UPDATE  size " +
            "<set> " +
            " <if test=\"size!=null and size!=''\"> " +
            " size = #{size}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateSize(SizeVO sizeVO);

    @Select("select * from size where size = #{name}")
    SizeVO findSizeByName(String name);
}
