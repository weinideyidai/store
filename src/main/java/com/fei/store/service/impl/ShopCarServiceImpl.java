package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.CustomerMapper;
import com.fei.store.mapper.GoodsMapper;
import com.fei.store.mapper.ShopCarMapper;
import com.fei.store.mapper.UserMapper;
import com.fei.store.service.ShopCarService;
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
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    private ShopCarMapper shopCarMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private GoodsMapper goodsMapper;

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
        List<ShopCarVO> shopCarVOS = shopCarMapper.findAllByPage(pageVO);

        for (ShopCarVO shopCarVO : shopCarVOS){
            CustomerVO customerVO = customerMapper.findCustomerById(shopCarVO.getUid());
            GoodsVO goods = goodsMapper.findGoodsById(shopCarVO.getGid());
            shopCarVO.setCustomerVO(customerVO);
            shopCarVO.setGoodsVO(goods);
        }

        PageInfo info = new PageInfo(shopCarVOS);
        return info;
    }

    @Override
    public List<ShopCarVO> findAll() {
        List<ShopCarVO> shopCarVOS =  shopCarMapper.findAll();
        for (ShopCarVO shopCarVO : shopCarVOS){
            CustomerVO customerVO = customerMapper.findCustomerById(shopCarVO.getUid());
            GoodsVO goods = goodsMapper.findGoodsById(shopCarVO.getGid());
            shopCarVO.setCustomerVO(customerVO);
            shopCarVO.setGoodsVO(goods);
        }
        return shopCarVOS;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = shopCarMapper.disableClasses(id);
                break;
            case 1:
                i = shopCarMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            shopCarMapper.disableClasses(id);
        }
    }

    @Override
    public void save(ShopCarVO shopCarVO) {
        shopCarVO.setStatus(1);
        int i = shopCarMapper.save(shopCarVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public ShopCarVO findShopCarById(Integer id) {
        ShopCarVO shopCarVO = shopCarMapper.findShopCarById(id);
        CustomerVO customerVO = customerMapper.findCustomerById(shopCarVO.getUid());
        GoodsVO goods = goodsMapper.findGoodsById(shopCarVO.getGid());
        shopCarVO.setCustomerVO(customerVO);
        shopCarVO.setGoodsVO(goods);
        return shopCarVO;
    }

    @Override
    public void updateShopCar(ShopCarVO shopCarVO) {
        int i = shopCarMapper.updateShopCar(shopCarVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }
}
