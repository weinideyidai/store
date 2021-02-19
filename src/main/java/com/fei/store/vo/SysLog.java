package com.fei.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {
    //主键id
    private Integer id;
    //访问时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitTime;
    //用户名
    private String username;
    //ip地址
    private String ip;
    //url地址
    private String url;
    //执行时间
    private Integer executionTime;
    //执行方法
    private String method;
}
