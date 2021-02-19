package com.fei.store.controller;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.service.CustomerService;
import com.fei.store.vo.CustomerVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = customerService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        customerService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        customerService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid CustomerVO customerVO, BindingResult result){
        if (result.hasErrors()){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        customerService.save(customerVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @RequestMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerVO customerVO){
        customerService.updateCustomer(customerVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("findAll")
    public ResponseEntity<?> findAll(){
        List<CustomerVO> productVOList = customerService.findAll();
        return ResponseEntity.ok(productVOList);
    }

    @RequestMapping("findName/{name}")
    public ResponseEntity<?> findCustomerByName(@PathVariable("name") String name){
        String str = customerService.findCustomerByName(name);
        return ResponseEntity.ok(str);
    }
}
