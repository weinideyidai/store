package com.fei.store.controller;

import com.fei.store.common.utils.PageVO;
import com.fei.store.service.GoodsESService;
import com.fei.store.vo.GoodsVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class GoodsESController {

    @Autowired
    private GoodsESService goodsESService;

    @RequestMapping("/search")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        Page<GoodsVO> page = goodsESService.findAllByPage(pageVO);
        return ResponseEntity.ok(page);
    }

    @RequestMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody GoodsVO goodsVO){
        goodsESService.saveOrUpdate(goodsVO);
        return ResponseEntity.ok(null);
    }



    @RequestMapping("/del")
    public ResponseEntity<?> del(@RequestBody GoodsVO goodsVO){
        goodsESService.delete(goodsVO);
        return ResponseEntity.ok(null);
    }
}
