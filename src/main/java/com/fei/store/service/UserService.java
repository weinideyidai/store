package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.UserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    PageInfo findAllByPage(PageVO pageVO);

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(UserVO userVO);

    UserVO findUserById(Integer id);

    void updateUser(UserVO userVO);

    void userAddRole(UserVO userVO);

    String findUserByName(String name);

    void updatePassword(UserVO userVO,String code);
}
