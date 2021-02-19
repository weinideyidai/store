package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.GoodTypeVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodTypeService {
    PageInfo findAllByPage(PageVO pageVO);

    List<BrandsVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(GoodTypeVO goodTypeVO);

    Object findGoodTypeById(Integer id);

    void updateGoodType(GoodTypeVO goodTypeVO);

    String findGoodTypeByName(String name);
}
