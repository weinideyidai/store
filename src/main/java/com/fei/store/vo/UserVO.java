package com.fei.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    //主键
    private Integer id;
    //用户名
    @NotBlank(message = "姓名不能为空")
    private String username;
    //密码
    @NotBlank(message = "密码不能为空")
    private String password;
    //电话号码
    @NotBlank(message = "电话不能为空")
    private String phone;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;
    //用户状态
    private Integer status;
    //地址id
    @NotNull(message = "地址不能为空")
    private Integer aid;
    //地址名称
    private String aidName;

    private List<RoleVO> roles = new ArrayList<>();

    private List<Integer> ids= new ArrayList<>();


}
