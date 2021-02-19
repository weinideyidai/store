package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.SizeMapper;
import com.fei.store.service.SizeService;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.SizeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class SizerServiceImpl implements SizeService {

    @Autowired
    private SizeMapper sizeMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<SizeVO> sizeVOS = sizeMapper.findAllByPage(pageVO);

        PageInfo info = new PageInfo(sizeVOS);
        return info;
    }

    @Override
    public List<SizeVO> findAll() {
        List<SizeVO> sizeVOS =  sizeMapper.findAll();
        return sizeVOS;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = sizeMapper.disableClasses(id);
                break;
            case 1:
                i = sizeMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            sizeMapper.disableClasses(id);
        }
    }

    @Override
    public void save(SizeVO sizeVO) {
        sizeVO.setStatus(1);
        int i = sizeMapper.save(sizeVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public Object findSizeById(Integer id) {
        SizeVO sizeVO = sizeMapper.findSizeById(id);
        return sizeVO;
    }

    @Override
    public void updateSize(SizeVO sizeVO) {
        int i = sizeMapper.updateSize(sizeVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public String findSizeByName(String name) {
        SizeVO sizeVO = sizeMapper.findSizeByName(name);
        return StringUtils.isEmpty(sizeVO)?"可用":"已存在";
    }
}
