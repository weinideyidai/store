package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.ReportVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AddressService {
    PageInfo findAllByPage(PageVO pageVO);

    List<AddressVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(AddressVO addressVO);

    AddressVO findAddressById(Integer id);

    void updateAddress(AddressVO addressVO);

    List<ReportVO> findAllByReport();

    String findAddressByName(String name);
}
