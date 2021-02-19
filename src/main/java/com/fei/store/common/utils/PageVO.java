package com.fei.store.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {

    //当前页
    @ApiModelProperty("当前页")
    private Integer page;
    //每页显示的条数
    @ApiModelProperty("每页显示的条数")
    private Integer rows;
    //排序的字段
    @ApiModelProperty("排序的字段")
    private String sortBy;
    //排序类型
    @ApiModelProperty("排序类型")
    private String desc="asc";
    //筛选条件
    private Map<Object,Object> key = new HashMap<>();

}
