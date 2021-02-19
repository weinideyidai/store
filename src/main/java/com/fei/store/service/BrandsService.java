package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandsService {
    PageInfo findAllByPage(PageVO pageVO);

    List<BrandsVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(BrandsVO brandsVO);

    BrandsVO findBrandsById(Integer id);

    void updateBrands(BrandsVO brandsVO);

    String findBrandsByName(String name);
}
