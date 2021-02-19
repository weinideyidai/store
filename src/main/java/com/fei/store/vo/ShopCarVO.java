package com.fei.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCarVO implements Serializable {

    private Integer id;
    private Integer uid;
    private Integer gid;
    private Integer count;
    private Integer status;

    private CustomerVO customerVO;
    private GoodsVO goodsVO;
}
