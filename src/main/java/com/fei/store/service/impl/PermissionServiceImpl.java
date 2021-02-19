package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.PermissionMapper;
import com.fei.store.service.PermissionService;
import com.fei.store.vo.PermissionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<PermissionVO> listByRoleId(Integer roleId) {
        List<PermissionVO> permissionVOS = permissionMapper.listByRoleId(roleId);
        return permissionVOS;
    }

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<PermissionVO> permissionVOS = permissionMapper.findAllByPage(pageVO);
        PageInfo info = new PageInfo(permissionVOS);
        return info;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = permissionMapper.disableClasses(id);
                break;
            case 1:
                i = permissionMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            permissionMapper.disableClasses(id);
        }
    }

    @Override
    public void save(PermissionVO permissionVO) {
        permissionVO.setStatus(1);
        int i = permissionMapper.save(permissionVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public Object findPermissionById(Integer id) {
        PermissionVO permissionVO = permissionMapper.findPermissionById(id);
        if (StringUtils.isEmpty(permissionVO)) throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        return permissionVO;
    }

    @Override
    public void updatePermission(PermissionVO permissionVO) {
        int i = permissionMapper.updatePermission(permissionVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public String findPermissionByName(String name) {
        PermissionVO permissionVO = permissionMapper.findPermissionByName(name);
        return StringUtils.isEmpty(permissionVO)?"权限可用":"权限已存在";
    }

    @Override
    public List<PermissionVO> findAll() {
        return null;
    }
}
