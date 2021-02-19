package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LogService {

    void save(SysLog sysLog);

    void deletById(Integer id);

    PageInfo findAllByPage(PageVO pageVO);

    void deletByIds(List<Integer> ids);
}
