package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.OrderVO;
import com.fei.store.vo.ReportVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderMapper {

    @Select("<script>" +
            "SELECT * FROM `order` " +
            "<where> " +
            " <if test=\"key.uid!=null and key.uid!=''\"> " +
            "and uid = #{key.uid}" +
            "</if> " +
            " <if test=\"key.begin!=null and key.end!=''\"> " +
            "and time between #{key.begin} and #{key.end}" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<OrderVO> findAllByPage(PageVO pageVO);

    @Select("SELECT * FROM `order` where status = 1")
    List<OrderVO> findAll();

    @Update("UPDATE `order` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `order` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `order`(time,sum,uid,aid,status) values(#{time},#{sum},#{uid},#{aid},#{status})")
    @Options(useGeneratedKeys=true,keyColumn="id")
    int save(OrderVO orderVO);

    @Select("select * from `order` where id = #{id}")
    OrderVO findOrderById(Integer id);

    @Select("SELECT MONTH(time) genre, count(*) sold " +
            "FROM `order`  " +
            "WHERE  YEAR(time) = YEAR(NOW())  " +
            "GROUP BY  MONTH(time)")
    List<ReportVO> findAllByReport();
}
