package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.OrderItemsVO;
import com.fei.store.vo.OrderVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderItemsService {
    PageInfo findAllByPage(PageVO pageVO);

    List<OrderItemsVO> findAll(Integer id);

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(OrderItemsVO orderItemsVO);

    Object findOrderItemById(Integer id);

}
