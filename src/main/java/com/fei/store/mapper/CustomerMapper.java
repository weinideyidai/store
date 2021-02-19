package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.CustomerVO;
import com.fei.store.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CustomerMapper {

    @Select("<script>" +
            "SELECT * FROM customer " +
            "<where> " +
            " <if test=\"key.username!=null and key.username!=''\"> " +
            "and username like  CONCAT('%', #{key.username}, '%')" +
            "</if> " +
            " <if test=\"key.begin!=null and key.end!=''\"> " +
            "and created between #{key.begin} and #{key.end}" +
            "</if> " +
            " <if test=\"key.aid!=null and key.aid!=''\"> " +
            "and aid = #{key.aid} " +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<CustomerVO> findAllByPage(PageVO pageVO);

    @Update("UPDATE `customer` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `customer` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `customer`(username,password,phone,created,status,aid) values(#{username},#{password},#{phone},NOW(),#{status},#{aid})")
    int save(CustomerVO customerVO);

    @Select("SELECT * FROM customer where id = #{id}")
    CustomerVO findCustomerById(Integer id);

    @Update("<script>" +
            "UPDATE  customer " +
            "<set> " +
            " <if test=\"username!=null and username!=''\"> " +
            " username = #{username}," +
            "</if> " +
            " <if test=\"phone!=null and phone!=''\"> " +
            " phone = #{phone}," +
            "</if> " +
            " <if test=\"aid!=null and aid!=''\"> " +
            " aid = #{aid}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateCustomer(CustomerVO customerVO);

    @Select("SELECT * FROM customer where status = 1")
    List<CustomerVO> findAll();

    @Select("select * from customer where username = #{username}")
    CustomerVO getUserByName(String name);
}
