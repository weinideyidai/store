package com.fei.store.mapper;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from sy_user where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "created",column = "created"),
            @Result(property = "status",column = "status"),
            @Result(property = "aid",column = "aid"),
            @Result(property = "lastPasswordResetDate",column = "lastPasswordResetDate"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.fei.store.mapper.RoleMapper.findRoleByUserId"))
    })
    UserVO getUserByName(String username);

    @Select("<script>" +
            "SELECT * FROM sy_user " +
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
    List<UserVO> findAllByPage(PageVO pageVO);

    @Update("UPDATE `sy_user` SET status = 0 WHERE id = #{id}")
    int disableClasses(Integer id);

    @Update("UPDATE `sy_user` SET status = 1 WHERE id = #{id}")
    int enableClasses(Integer id);

    @Insert("insert into `sy_user`(username,password,phone,created,status,aid) values(#{username},#{password},#{phone},NOW(),#{status},#{aid})")
    int save(UserVO userVO);

    @Select("SELECT * FROM sy_user where id = #{id}")
    UserVO findUserById(Integer id);

    @Update("<script>" +
            "UPDATE  sy_user " +
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
    int updateUser(UserVO userVO);

    @Insert("insert into `user_role`(uid,rid,status) values(#{id},#{id1},1)")
    int userAddRole(Integer id, Integer id1);

    @Update("UPDATE `sy_user` SET password = #{password} WHERE username = #{username}")
    void updatePassword(UserVO userVO);
}
