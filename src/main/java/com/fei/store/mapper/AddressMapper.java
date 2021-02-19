package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.ReportVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AddressMapper {

    @Select("SELECT * FROM address WHERE id=#{aid}")
    AddressVO findAddressById(Integer aid);

    @Select("<script>" +
            "SELECT * FROM address " +
            "<where> " +
            " <if test=\"key.address!=null and key.address!=''\"> " +
            "and address like  CONCAT('%', #{key.address}, '%')" +
            "</if> " +
            " <if test=\"key.postalcode!=null and key.postalcode!=''\"> " +
            "and postalcode like  CONCAT('%', #{key.postalcode}, '%')" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<AddressVO> findAllByPage(PageVO pageVO);

    @Select("SELECT * FROM address where status = 1")
    List<AddressVO> findAll();

    @Update("UPDATE `address` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `address` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `address`(address,postalcode,status) values(#{address},#{postalcode},#{status})")
    int save(AddressVO addressVO);

    @Select("select * from address where id = #{id}")
    AddressVO findCityById(Integer id);

    @Update("<script>" +
            "UPDATE  address " +
            "<set> " +
            " <if test=\"address!=null and address!=''\"> " +
            " address = #{address}," +
            "</if> " +
            " <if test=\"postalcode!=null and postalcode!=''\"> " +
            " postalcode = #{postalcode}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateAddress(AddressVO addressVO);

    @Select("SELECT c.address genre,count(u.aid) sold FROM address c LEFT JOIN customer u ON c.id = u.aid WHERE c.`status`=1 AND u.`status`=1 GROUP BY c.id")
    List<ReportVO>  findAllByReport();

    @Select("SELECT * FROM address where address = #{name}")
    AddressVO findAddressByName(String name);
}
