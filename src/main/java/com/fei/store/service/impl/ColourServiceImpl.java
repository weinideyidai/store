package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.ColourMapper;
import com.fei.store.service.ColourService;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class ColourServiceImpl implements ColourService {

    @Autowired
    private ColourMapper colourMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<BrandsVO> brandsVOS = colourMapper.findAllByPage(pageVO);

        PageInfo info = new PageInfo(brandsVOS);
        return info;
    }

    @Override
    public List<ColourVO> findAll() {
        List<ColourVO> colourVOS =  colourMapper.findAll();
        return colourVOS;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = colourMapper.disableClasses(id);
                break;
            case 1:
                i = colourMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            colourMapper.disableClasses(id);
        }
    }

    @Override
    public void save(ColourVO colourVO) {
        colourVO.setStatus(1);
        int i = colourMapper.save(colourVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public ColourVO findColourById(Integer id) {
        ColourVO colourVO = colourMapper.findColourById(id);
        return colourVO;
    }

    @Override
    public void updateColour(ColourVO colourVO) {
        int i = colourMapper.updateBrands(colourVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public String findColourByName(String name) {
        ColourVO colourVO = colourMapper.findColourByName(name);
        return StringUtils.isEmpty(colourVO)?"可用":"已存在";
    }
}
