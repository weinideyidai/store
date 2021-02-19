package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.*;
import com.fei.store.service.OrderService;
import com.fei.store.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        Map<Object, Object> key = pageVO.getKey();
        CustomerVO name = customerMapper.getUserByName((String) key.get("name"));
        if (!StringUtils.isEmpty(name)){
            key.put("uid",name.getId());
        }
        else if(!StringUtils.isEmpty(key.get("name"))){
            key.put("uid",-1);
        }else key.put("uid",null);
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<OrderVO> orderVOS = orderMapper.findAllByPage(pageVO);
        for (OrderVO orderVO : orderVOS){
            CustomerVO customerVO = customerMapper.findCustomerById(orderVO.getUid());
            AddressVO address = addressMapper.findAddressById(orderVO.getAid());
            orderVO.setCustomerVO(customerVO);
            orderVO.setAddressVO(address);
        }
        PageInfo info = new PageInfo(orderVOS);
        return info;
    }

    @Override
    public List<OrderVO> findAll() {
        List<OrderVO> orderVOS =  orderMapper.findAll();
        for (OrderVO orderVO : orderVOS){
            CustomerVO customerVO = customerMapper.findCustomerById(orderVO.getUid());
            AddressVO address = addressMapper.findAddressById(orderVO.getAid());
            orderVO.setCustomerVO(customerVO);
            orderVO.setAddressVO(address);
        }
        return orderVOS;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = orderMapper.disableClasses(id);
                break;
            case 1:
                i = orderMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            orderMapper.disableClasses(id);
        }
    }

    @Override
    public void save(OrderVO orderVO) {
        orderVO.setStatus(1);
        int i = orderMapper.save(orderVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        List<OrderItemsVO> orderItemsVOS = orderVO.getOrderItemsVOS();
        for (OrderItemsVO orderItemsVO : orderItemsVOS){
            orderItemsVO.setOid(i);
            int save = orderItemsMapper.save(orderItemsVO);
            if(save==0){
                throw new MyException(ExceptionEnum.SERVER_CONGESTION);
            }
        }
    }

    @Override
    public OrderVO findOrderById(Integer id) {
        OrderVO orderVO = orderMapper.findOrderById(id);
        CustomerVO customerVO = customerMapper.findCustomerById(orderVO.getUid());
        AddressVO address = addressMapper.findAddressById(orderVO.getAid());
        List<OrderItemsVO> all = orderItemsMapper.findAll(orderVO.getId());
        orderVO.setOrderItemsVOS(all);
        orderVO.setCustomerVO(customerVO);
        orderVO.setAddressVO(address);
        return orderVO;
    }

    @Override
    public List<ReportVO> findAllByReport() {
        List<ReportVO> reportVOList = orderMapper.findAllByReport();
        return reportVOList;
    }
}
