package com.fei.store.controller;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.service.AddressService;
import com.fei.store.vo.AddressVO;
import com.fei.store.vo.ReportVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = addressService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("findAll")
    public ResponseEntity<?> findAll(){
        List<AddressVO> cityVOS = addressService.findAll();
        return ResponseEntity.ok(cityVOS);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        addressService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        addressService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid AddressVO addressVO, BindingResult result){
        if (result.hasErrors()){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        addressService.save(addressVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(addressService.findAddressById(id));
    }

    @RequestMapping("updateCustomer")
    public ResponseEntity<?> updateAddress(@RequestBody AddressVO addressVO){
        addressService.updateAddress(addressVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("report")
    public ResponseEntity<?> findAllByReport(){
        List<ReportVO> report = addressService.findAllByReport();
        return ResponseEntity.ok(report);
    }

    @RequestMapping("findName/{name}")
    public ResponseEntity<?> findAddressByName(@PathVariable String name){
        String str = addressService.findAddressByName(name);
        return ResponseEntity.ok(str);
    }
}
