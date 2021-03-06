package com.fei.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVO implements Serializable {

    //主键
    private Integer id;
    //用户名
    @NotBlank(message = "名称不能为空")
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
    @NotNull(message = "地址id不能为空")
    private Integer aid;
    //地址名称
    private String aidName;
}
