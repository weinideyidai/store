package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.AddressMapper;
import com.fei.store.mapper.RoleMapper;
import com.fei.store.mapper.UserMapper;
import com.fei.store.service.UserService;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.RoleVO;
import com.fei.store.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        List<UserVO> userVOS = userMapper.findAllByPage(pageVO);
        for (UserVO userVO : userVOS){
            AddressVO addressVO = addressMapper.findAddressById(userVO.getAid());
            List<RoleVO> role = roleMapper.findRoleByUserId(userVO.getId());
            userVO.setRoles(role);
            userVO.setAidName(addressVO.getAddress());
        }
        PageInfo info = new PageInfo(userVOS);
        return info;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = userMapper.disableClasses(id);
                break;
            case 1:
                i = userMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.disableClasses(id);
        }
    }

    @Override
    public void save(UserVO userVO) {
        userVO.setStatus(1);
        String encode = passwordEncoder.encode(userVO.getPassword());
        userVO.setPassword(encode);
        int i = userMapper.save(userVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public UserVO findUserById(Integer id) {
        UserVO userVO = userMapper.findUserById(id);
        List<RoleVO> roleByUserId = roleMapper.findOtherRoles(userVO.getId());
        userVO.setRoles(roleByUserId);
        if (StringUtils.isEmpty(userVO)) throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        return userVO;
    }

    @Override
    public void updateUser(UserVO userVO) {
        int i = userMapper.updateUser(userVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void userAddRole(UserVO userVO) {
        List<Integer> ids = userVO.getIds();
        for (Integer id : ids){
            int i = userMapper.userAddRole(userVO.getId(),id);
            if (i == 0) {
                throw new MyException(ExceptionEnum.SERVER_CONGESTION);
            }
        }
    }

    @Override
    public String findUserByName(String name) {
        UserVO user = userMapper.getUserByName(name);
        return StringUtils.isEmpty(user)?"用户可用":"用户已存在";
    }

    @Override
    public void updatePassword(UserVO userVO,String code) {
        UserVO user = userMapper.getUserByName(userVO.getUsername());
        if (!userVO.getPhone().equals(user.getPhone())){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        String userNum = stringRedisTemplate.opsForValue().get(user.getPhone());
        if(!StringUtils.isEmpty(userNum)&&userNum.equals(code)){
            String encode = passwordEncoder.encode(userVO.getPassword());
            userVO.setPassword(encode);
            userMapper.updatePassword(userVO);
        }

    }
}
