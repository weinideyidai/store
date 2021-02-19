package com.fei.store.controller;

import com.fei.store.common.utils.PageVO;
import com.fei.store.service.OrderService;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.OrderVO;
import com.fei.store.vo.ReportVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = orderService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("findAll")
    public ResponseEntity<?> findAll(){
        List<OrderVO> orderVOS = orderService.findAll();
        return ResponseEntity.ok(orderVOS);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        orderService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        orderService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody OrderVO orderVO){
        orderService.save(orderVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    @RequestMapping("report")
    public ResponseEntity<?> findAllByReport(){
        List<ReportVO> report = orderService.findAllByReport();
        return ResponseEntity.ok(report);
    }
}
