package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.SizeVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SizeService {
    PageInfo findAllByPage(PageVO pageVO);

    List<SizeVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(SizeVO sizeVO);

    Object findSizeById(Integer id);

    void updateSize(SizeVO sizeVO);

    String findSizeByName(String name);
}
