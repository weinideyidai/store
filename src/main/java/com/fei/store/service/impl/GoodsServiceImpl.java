package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.*;
import com.fei.store.service.GoodsService;
import com.fei.store.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodTypeMapper goodTypeMapper;

    @Autowired
    private BrandsMapper brandsMapper;

    @Autowired
    private ColourMapper colourMapper;

    @Autowired
    private SizeMapper sizeMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<GoodsVO> goodsVOS = goodsMapper.findAllByPage(pageVO);

        for (GoodsVO goodsVO : goodsVOS){
            BrandsVO brands = brandsMapper.findBrandsById(goodsVO.getBid());
            ColourVO colour = colourMapper.findColourById(goodsVO.getCid());
            SizeVO size = sizeMapper.findSizeById(goodsVO.getSid());
            GoodTypeVO goodTypeVO = goodTypeMapper.findGoodTypeById(goodsVO.getTid());
            goodsVO.setBrandsVO(brands);
            goodsVO.setColourVO(colour);
            goodsVO.setSizeVO(size);
            goodsVO.setGoodTypeVO(goodTypeVO);
        }

        PageInfo info = new PageInfo(goodsVOS);
        return info;
    }

    @Override
    public List<GoodsVO> findAll() {
        List<GoodsVO> all = goodsMapper.findAll();
        for (GoodsVO goodsVO : all){
            BrandsVO brands = brandsMapper.findBrandsById(goodsVO.getBid());
            ColourVO colour = colourMapper.findColourById(goodsVO.getCid());
            SizeVO size = sizeMapper.findSizeById(goodsVO.getSid());
            GoodTypeVO goodTypeVO = goodTypeMapper.findGoodTypeById(goodsVO.getTid());
            goodsVO.setBrandsVO(brands);
            goodsVO.setColourVO(colour);
            goodsVO.setSizeVO(size);
            goodsVO.setGoodTypeVO(goodTypeVO);
        }

        return all;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = goodsMapper.disableClasses(id);
                break;
            case 1:
                i = goodsMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            goodsMapper.disableClasses(id);
        }
    }

    @Override
    public void save(GoodsVO goodsVO) {
        goodsVO.setStatus(1);
        GoodsVO goods = goodsMapper.findGoodsByGoods(goodsVO);
        if (StringUtils.isEmpty(goods)){
            int i = goodsMapper.save(goodsVO);
            if (i == 0) throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }else{
            int count = goods.getCount();
            goodsVO.setCount(goodsVO.getCount()+count);
            goodsMapper.updateGoods(goodsVO);
        }
    }

    @Override
    public GoodsVO findGoodsById(Integer id) {
        GoodsVO goodsVO = goodsMapper.findGoodsById(id);
        BrandsVO brands = brandsMapper.findBrandsById(goodsVO.getBid());
        ColourVO colour = colourMapper.findColourById(goodsVO.getCid());
        SizeVO size = sizeMapper.findSizeById(goodsVO.getSid());
        GoodTypeVO goodTypeVO = goodTypeMapper.findGoodTypeById(goodsVO.getTid());
        goodsVO.setBrandsVO(brands);
        goodsVO.setColourVO(colour);
        goodsVO.setSizeVO(size);
        goodsVO.setGoodTypeVO(goodTypeVO);
        return goodsVO;
    }

    @Override
    public void updateGoods(GoodsVO goodsVO) {
        int i = goodsMapper.updateGoods(goodsVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }
}
