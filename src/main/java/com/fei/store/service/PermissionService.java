package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.PermissionVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PermissionService {
    List<PermissionVO> listByRoleId(Integer roleId);

    PageInfo findAllByPage(PageVO pageVO);

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(PermissionVO permissionVO);

    Object findPermissionById(Integer id);

    void updatePermission(PermissionVO permissionVO);

    String findPermissionByName(String name);

    List<PermissionVO> findAll();
}
