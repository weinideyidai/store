package com.fei.store.security.service;

import com.fei.store.mapper.UserMapper;
import com.fei.store.security.domain.JwtUserFactory;
import com.fei.store.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 重新UserDetailsService的loadUserByUsername方法执行授权
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class JwtUserService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserVO user = userMapper.getUserByName(username);

        return JwtUserFactory.create(user);
    }
}
