package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.AddressMapper;
import com.fei.store.mapper.CustomerMapper;
import com.fei.store.service.CustomerService;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.CustomerVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<CustomerVO> customerVOS = customerMapper.findAllByPage(pageVO);
        for (CustomerVO customerVO : customerVOS){
            AddressVO addressVO = addressMapper.findAddressById(customerVO.getAid());
            customerVO.setAidName(addressVO.getAddress());
        }
        PageInfo info = new PageInfo(customerVOS);
        return info;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = customerMapper.disableClasses(id);
                break;
            case 1:
                i = customerMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            customerMapper.disableClasses(id);
        }
    }

    @Override
    public void save(CustomerVO customerVO) {
        String encode = passwordEncoder.encode(customerVO.getPassword());
        customerVO.setPassword(encode);
        customerVO.setStatus(1);
        int i = customerMapper.save(customerVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public Object findCustomerById(Integer id) {
        CustomerVO customerVO = customerMapper.findCustomerById(id);
        if (StringUtils.isEmpty(customerVO)) throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        return customerVO;
    }

    @Override
    public void updateCustomer(CustomerVO customerVO) {
        int i = customerMapper.updateCustomer(customerVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public List<CustomerVO> findAll() {
        List<CustomerVO> customerVOS = customerMapper.findAll();
        return customerVOS;
    }

    @Override
    public String findCustomerByName(String name) {
        CustomerVO customerVO = customerMapper.getUserByName(name);
        return StringUtils.isEmpty(customerVO)?"该用户可用":"已存在";
    }
}
