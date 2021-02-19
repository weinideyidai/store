package com.fei.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionVO implements Serializable {

    private Integer id;
    @NotBlank(message = "名称不能为空")
    private String mname;
    @NotBlank(message = "url不能为空")
    private String url;
    private Integer status;

}
