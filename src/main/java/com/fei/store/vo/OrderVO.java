package com.fei.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO implements Serializable {

    private Integer id;
    private Integer uid;
    private Integer aid;
    private Integer sum;
    private Date time;
    private Integer status;

    private CustomerVO customerVO;
    private AddressVO addressVO;

    private List<OrderItemsVO> orderItemsVOS = new ArrayList<>();

}
