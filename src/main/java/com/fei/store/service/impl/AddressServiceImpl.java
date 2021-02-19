package com.fei.store.service.impl;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.AddressMapper;
import com.fei.store.service.AddressService;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.ReportVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public PageInfo findAllByPage(PageVO pageVO) {
        //分页
        PageHelper.startPage(pageVO.getPage(), pageVO.getRows());
        //查询
        List<AddressVO> customerVOS = addressMapper.findAllByPage(pageVO);

        PageInfo info = new PageInfo(customerVOS);

        return info;
    }

    @Override
    public List<AddressVO> findAll() {
        List<AddressVO> cityVOS =  addressMapper.findAll();
        return cityVOS;
    }

    @Override
    public void updateStateById(Integer id, Integer type) {
        int i = 0;
        switch (type) {
            case 0:
                i = addressMapper.disableClasses(id);
                break;
            case 1:
                i = addressMapper.enableClasses(id);
                break;
        }
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids) {
        for (Integer id : ids) {
            addressMapper.disableClasses(id);
        }
    }

    @Override
    public void save(AddressVO addressVO) {
        addressVO.setStatus(1);
        int i = addressMapper.save(addressVO);
        if (i == 0) {
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public AddressVO findAddressById(Integer id) {
        AddressVO addressVO = addressMapper.findCityById(id);
        return addressVO;
    }

    @Override
    public void updateAddress(AddressVO addressVO) {
        int i = addressMapper.updateAddress(addressVO);
        if (i == 0){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
    }

    @Override
    public List<ReportVO> findAllByReport() {
        List<ReportVO> reportVOS =  addressMapper.findAllByReport();
        return reportVOS;
    }

    @Override
    public String findAddressByName(String name) {
        AddressVO addressVO = addressMapper.findAddressByName(name);
        return StringUtils.isEmpty(addressVO)?"地址可用":"已存在";
    }
}
