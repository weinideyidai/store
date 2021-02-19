package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.ShopCarVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ShopCarService {
    PageInfo findAllByPage(PageVO pageVO);

    List<ShopCarVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(ShopCarVO shopCarVO);

    ShopCarVO findShopCarById(Integer id);

    void updateShopCar(ShopCarVO shopCarVO);
}
