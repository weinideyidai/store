package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.CustomerVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CustomerService {
    PageInfo findAllByPage(PageVO pageVO);

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(CustomerVO customerVO);

    Object findCustomerById(Integer id);

    void updateCustomer(CustomerVO customerVO);

    List<CustomerVO> findAll();

    String findCustomerByName(String name);
}
