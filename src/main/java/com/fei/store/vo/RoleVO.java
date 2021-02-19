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
public class RoleVO implements Serializable {

    private Integer id;
    //角色名称
    @NotBlank(message = "名称不能为空")
    private String name;
    //状态
    private Integer status;

    private List<PermissionVO> permissionVOS = new ArrayList<>();

}
