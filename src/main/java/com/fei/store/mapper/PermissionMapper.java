package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.PermissionVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PermissionMapper {

    @Select("SELECT * FROM permission a where a.id in (select pid from role_permission where rid = #{id}) AND `status` = 1")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "mname",column = "mname"),
            @Result(property = "pid",column = "pid"),
            @Result(property = "url",column = "url"),
            @Result(property = "status",column = "status")
    })
    public List<PermissionVO> listByRoleId(Integer roleId);

    @Select("<script>" +
            "SELECT * FROM permission " +
            "<where> " +
            " <if test=\"key.mname!=null and key.mname!=''\"> " +
            "and mname like  CONCAT('%', #{key.mname}, '%')" +
            "</if> " +
            " <if test=\"key.url!=null and key.url!=''\"> " +
            "and url like  CONCAT('%', #{key.url}, '%')" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<PermissionVO> findAllByPage(PageVO pageVO);

    @Select("select * from permission where id not in (select pid from role_permission where rid = #{id}) " +
            "and status = 1")
    List<PermissionVO> findOtherpermission(Integer id);

    @Update("UPDATE `permission` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `permission` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `permission`(mname,url,status) values(#{mname},#{url},#{status})")
    int save(PermissionVO permissionVO);

    @Select("SELECT * FROM permission where id = #{id}")
    PermissionVO findPermissionById(Integer id);

    @Update("<script>" +
            "UPDATE  permission " +
            "<set> " +
            " <if test=\"mname!=null and mname!=''\"> " +
            " mname = #{mname}," +
            "</if> " +
            " <if test=\"url!=null and url!=''\"> " +
            " url = #{url}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updatePermission(PermissionVO permissionVO);

    @Select("SELECT * FROM permission where mname = #{name}")
    PermissionVO findPermissionByName(String name);
}
