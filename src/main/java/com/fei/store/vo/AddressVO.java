package com.fei.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO implements Serializable {

    private Integer id;
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotBlank(message = "邮编不能为空")
    private String postalcode;
    private Integer status;

}
