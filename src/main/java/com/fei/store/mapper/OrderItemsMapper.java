package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.OrderItemsVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderItemsMapper {

    @Select("<script>" +
            "SELECT * FROM order_items " +
            "<where> " +
            " <if test=\"key.oid!=null and key.oid!=''\"> " +
            "and oid = #{key.oid}" +
            "</if> " +
            " <if test=\"key.gid!=null and key.gid!=''\"> " +
            "and gid = #{key.gid}" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<OrderItemsVO> findAllByPage(PageVO pageVO);

    @Select("SELECT * FROM order_items where status = 1 and oid = #{id}")
    List<OrderItemsVO> findAll(Integer id);

    @Update("UPDATE `order_items` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `order_items` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `order_items`(oid,gid,count,status) values(#{oid},#{gid},#{count},#{status})")
    int save(OrderItemsVO orderItemsVO);

    @Select("select * from order_items where id = #{id}")
    OrderItemsVO findOrderById(Integer id);

}
