package com.fei.store.controller;

import com.fei.store.common.utils.PageVO;
import com.fei.store.service.OrderItemsService;
import com.fei.store.vo.OrderItemsVO;
import com.fei.store.vo.OrderVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = orderItemsService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("findAll/{id}")
    public ResponseEntity<?> findAll(@PathVariable("id") Integer id){
        List<OrderItemsVO> orderVOS = orderItemsService.findAll(id);
        return ResponseEntity.ok(orderVOS);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        orderItemsService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        orderItemsService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody OrderItemsVO orderItemsVO){
        orderItemsService.save(orderItemsVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findOrderItemById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderItemsService.findOrderItemById(id));
    }

}
