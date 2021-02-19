package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.GoodsVO;
import org.springframework.data.domain.Page;

public interface GoodsESService {

    Page<GoodsVO> findAllByPage(PageVO pageVO);

    void saveOrUpdate(GoodsVO goodsVO);

    void delete(GoodsVO goodsVO);


}
