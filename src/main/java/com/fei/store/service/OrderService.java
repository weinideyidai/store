package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.OrderVO;
import com.fei.store.vo.ReportVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {
    PageInfo findAllByPage(PageVO pageVO);

    List<OrderVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(OrderVO orderVO);

    OrderVO findOrderById(Integer id);

    List<ReportVO> findAllByReport();
}
