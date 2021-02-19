package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.GoodTypeMapper;
import com.fei.store.service.GoodTypeService;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.GoodTypeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class GoodTypeServiceImpl implements GoodTypeService {

    @Autowired
    private GoodTypeMapper goodTypeMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<BrandsVO> brandsVOS = goodTypeMapper.findAllByPage(pageVO);

        PageInfo info = new PageInfo(brandsVOS);
        return info;
    }

    @Override
    public List<BrandsVO> findAll() {
        List<BrandsVO> brandsVOS =  goodTypeMapper.findAll();
        return brandsVOS;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = goodTypeMapper.disableClasses(id);
                break;
            case 1:
                i = goodTypeMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            goodTypeMapper.disableClasses(id);
        }
    }

    @Override
    public void save(GoodTypeVO goodTypeVO) {
        goodTypeVO.setStatus(1);
        int i = goodTypeMapper.save(goodTypeVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public Object findGoodTypeById(Integer id) {
        GoodTypeVO goodTypeVO = goodTypeMapper.findGoodTypeById(id);
        return goodTypeVO;
    }

    @Override
    public void updateGoodType(GoodTypeVO goodTypeVO) {
        int i = goodTypeMapper.updateGoodType(goodTypeVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public String findGoodTypeByName(String name) {
        GoodTypeVO goodTypeVO = goodTypeMapper.findGoodTypeByName(name);
        return StringUtils.isEmpty(goodTypeVO)?"可用":"已存在";
    }
}
