package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ColourService {
    PageInfo findAllByPage(PageVO pageVO);

    List<ColourVO> findAll();

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(ColourVO colourVO);

    ColourVO findColourById(Integer id);

    void updateColour(ColourVO colourVO);

    String findColourByName(String name);
}
