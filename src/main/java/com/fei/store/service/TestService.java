package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.github.pagehelper.PageInfo;

public interface TestService {
    PageInfo findAllByPage(PageVO pageVO);
}
