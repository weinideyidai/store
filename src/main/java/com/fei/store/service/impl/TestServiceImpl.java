package com.fei.store.service.impl;

import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.TestMapper;
import com.fei.store.service.TestService;
import com.fei.store.vo.TestVO;
import com.fei.store.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        List<TestVO> testVOS = testMapper.findAllByPage(pageVO);
        PageInfo info = new PageInfo(testVOS);
        return info;
    }
}
