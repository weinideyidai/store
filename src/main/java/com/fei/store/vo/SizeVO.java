package com.fei.store.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeVO {

    private Integer id;
    @NotBlank(message = "商品尺寸不能为空")
    @Excel(name = "商品尺寸", orderNum = "7")
    private String size;
    private Integer status;
}
