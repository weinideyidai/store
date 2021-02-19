package com.fei.store.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodTypeVO implements Serializable {

    private Integer id;
    @NotBlank(message = "商品类型不能为空")
    @Excel(name = "商品类型", orderNum = "6")
    private String name;
    private Integer status;
}
