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
public class ColourVO implements Serializable {

    private Integer id;
    @Excel(name = "颜色", orderNum = "5")
    @NotBlank(message = "颜色不能为空")
    private String name;
    private Integer status;
}
