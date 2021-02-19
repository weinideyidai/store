package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.RoleVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {

    @Select("select * from role where id in(select rid from user_role where uid = #{userId}) and status = 1")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "status",column = "status"),
            @Result(property = "permissionVOS",column = "id",javaType = java.util.List.class,many = @Many(select = "com.fei.store.mapper.PermissionMapper.listByRoleId"))
    })
    List<RoleVO> findRoleByUserId(int userId);

    @Select("select * from role where name = #{roleName} ")
    RoleVO selectByName(String roleName);

    @Select("<script>" +
            "SELECT * FROM role " +
            "<where> " +
            " <if test=\"key.name!=null and key.name!=''\"> " +
            "and name like  CONCAT('%', #{key.name}, '%')" +
            "</if> " +
            "</where>" +
            "<if test=\"sortBy!=null and sortBy!=''\"> " +
            "ORDER BY #{sortBy} #{desc}" +
            "</if>" +
            "</script>")
    List<RoleVO> findAllByPage(PageVO pageVO);

    @Select("select * from role where id in (select rid from user_role where uid = #{id})")
    List<RoleVO> findOtherRoles(Integer id);

    @Update("UPDATE `role` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `role` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `role`(name,status) values(#{name},#{status})")
    int save(RoleVO roleVO);

    @Select("SELECT * FROM role where id = #{id}")
    RoleVO findRoleById(Integer id);

    @Update("<script>" +
            "UPDATE  role " +
            "<set> " +
            " <if test=\"name!=null and name!=''\"> " +
            " name = #{name}," +
            "</if> " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateRole(RoleVO roleVO);

    @Insert("insert into `role_permission`(rid,pid,status) values(#{id},#{id1},1)")
    int roleAddPermission(Integer id, Integer id1);

    @Select("select * from role where status = 1")
    List<RoleVO> findAllRole();

    @Delete("DELETE FROM user_role WHERE user_role.uid=#{id}")
    void deletRoleByUid(Integer id);
}
