package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.BrandsMapper;
import com.fei.store.service.BrandsService;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.BrandsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class BrandsServiceImpl implements BrandsService {

    @Autowired
    private BrandsMapper brandsMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<BrandsVO> brandsVOS = brandsMapper.findAllByPage(pageVO);

        PageInfo info = new PageInfo(brandsVOS);
        return info;
    }

    @Override
    public List<BrandsVO> findAll() {
        List<BrandsVO> brandsVOS =  brandsMapper.findAll();
        return brandsVOS;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = brandsMapper.disableClasses(id);
                break;
            case 1:
                i = brandsMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            brandsMapper.disableClasses(id);
        }
    }

    @Override
    public void save(BrandsVO brandsVO) {
        brandsVO.setStatus(1);
        int i = brandsMapper.save(brandsVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public BrandsVO findBrandsById(Integer id) {
        BrandsVO brandsVO = brandsMapper.findBrandsById(id);
        return brandsVO;
    }

    @Override
    public void updateBrands(BrandsVO brandsVO) {
        int i = brandsMapper.updateBrands(brandsVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public String findBrandsByName(String name) {
        BrandsVO brandsVO = brandsMapper.findBrandsByName(name);
        return StringUtils.isEmpty(brandsVO)?"可用":"已存在";
    }
}
