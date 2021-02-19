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
public class BrandsVO implements Serializable {

    private Integer id;
    @Excel(name = "品牌", orderNum = "4")
    @NotBlank(message = "品牌不能为空")
    private String name;
    @NotBlank(message = "图片不能为空")
    private String img;
    private Integer status;
}
