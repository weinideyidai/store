package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.GoodsVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {
    PageInfo findAllByPage(PageVO pageVO);

    List<GoodsVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(GoodsVO goodsVO);

    GoodsVO findGoodsById(Integer id);

    void updateGoods(GoodsVO goodsVO);
}
