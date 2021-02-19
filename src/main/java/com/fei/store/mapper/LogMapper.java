package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogMapper {

    @Insert("INSERT INTO syslog(visitTime,username,ip,url,executionTime,method)  " +
            "VALUES(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    int save(SysLog sysLog);

    @Select("select * from syslog")
    List<SysLog> findAll();

    @Delete("DELETE FROM syslog WHERE id = #{id} ")
    int deletById(Integer id);

    @Select("<script>" +
            "SELECT * FROM syslog " +
            "<where> " +
            "<if test=\"key.username!=null and key.username!=''\">" +
            "and username like  CONCAT('%', #{key.username}, '%') " +
            "</if>" +
            "<if test=\"key.begin!=null and key.end!=''\">" +
            "and visitTime between #{key.begin} and #{key.end}" +
            "</if>" +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc} " +
            "</if>" +
            "</script>")
    List<SysLog> findAllByPage(PageVO pageVO);
}
