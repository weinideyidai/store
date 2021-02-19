package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.PermissionMapper;
import com.fei.store.mapper.RoleMapper;
import com.fei.store.service.RoleService;
import com.fei.store.vo.PermissionVO;
import com.fei.store.vo.RoleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public RoleVO selectByName(String roleName) {
        RoleVO roleVO = roleMapper.selectByName(roleName);
        return roleVO;
    }

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<RoleVO> roleVOS = roleMapper.findAllByPage(pageVO);
        for (RoleVO roleVO : roleVOS){
            List<PermissionVO> permissionVOS = permissionMapper.listByRoleId(roleVO.getId());
            roleVO.setPermissionVOS(permissionVOS);
        }
        PageInfo info = new PageInfo(roleVOS);
        return info;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = roleMapper.disableClasses(id);
                break;
            case 1:
                i = roleMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            roleMapper.disableClasses(id);
        }
    }

    @Override
    public void save(RoleVO roleVO) {
        roleVO.setStatus(1);
        int i = roleMapper.save(roleVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public Object findRoleById(Integer id) {
        RoleVO roleVO = roleMapper.findRoleById(id);
        List<PermissionVO> allById = permissionMapper.findOtherpermission(roleVO.getId());
        roleVO.setPermissionVOS(allById);
        if (StringUtils.isEmpty(roleVO)) throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        return roleVO;
    }

    @Override
    public void updateRole(RoleVO roleVO) {
        int i = roleMapper.updateRole(roleVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void roleAddPermission(Integer id, Integer id1) {
        int i = roleMapper.roleAddPermission(id,id1);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public String findRoleByName(String name) {
        RoleVO roleVO = roleMapper.selectByName(name);
        return StringUtils.isEmpty(roleVO)?"角色可用":"角色已存在";
    }

    @Override
    public List<RoleVO> findAllRole() {
        return roleMapper.findAllRole();
    }

    @Override
    public void deletRoleByUid(Integer id) {
        roleMapper.deletRoleByUid(id);
    }
}
