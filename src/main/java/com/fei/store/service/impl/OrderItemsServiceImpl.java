package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.GoodsMapper;
import com.fei.store.mapper.OrderItemsMapper;
import com.fei.store.service.OrderItemsService;
import com.fei.store.vo.GoodsVO;
import com.fei.store.vo.OrderItemsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<OrderItemsVO> orderItemsVOList = orderItemsMapper.findAllByPage(pageVO);
        for (OrderItemsVO orderItemsVO : orderItemsVOList){
            GoodsVO goods = goodsMapper.findGoodsById(orderItemsVO.getGid());
            orderItemsVO.setGoodsVO(goods);
        }
        PageInfo info = new PageInfo(orderItemsVOList);
        return info;
    }

    @Override
    public List<OrderItemsVO> findAll(Integer id) {
        List<OrderItemsVO> orderItemsVOList =  orderItemsMapper.findAll(id);
        for (OrderItemsVO orderItemsVO : orderItemsVOList){
            GoodsVO goods = goodsMapper.findGoodsById(orderItemsVO.getGid());
            orderItemsVO.setGoodsVO(goods);
        }
        return orderItemsVOList;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = orderItemsMapper.disableClasses(id);
                break;
            case 1:
                i = orderItemsMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            orderItemsMapper.disableClasses(id);
        }
    }

    @Override
    public void save(OrderItemsVO orderItemsVO) {
        orderItemsVO.setStatus(1);
        int i = orderItemsMapper.save(orderItemsVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public OrderItemsVO findOrderItemById(Integer id) {
        OrderItemsVO orderItemsVO = orderItemsMapper.findOrderById(id);
        GoodsVO goods = goodsMapper.findGoodsById(orderItemsVO.getGid());
        orderItemsVO.setGoodsVO(goods);
        return orderItemsVO;
    }

}
