package com.fei.store.service;

import com.fei.store.common.utils.PageVO;
import com.fei.store.vo.RoleVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    RoleVO selectByName(String roleName);

    PageInfo findAllByPage(PageVO pageVO);

    void updateStateById(Integer id, Integer type);

    void updateStateByIds(List<Integer> ids);

    void save(RoleVO roleVO);

    Object findRoleById(Integer id);

    void updateRole(RoleVO roleVO);

    void roleAddPermission(Integer id, Integer id1);

    String findRoleByName(String name);

    List<RoleVO> findAllRole();

    void deletRoleByUid(Integer id);
}
