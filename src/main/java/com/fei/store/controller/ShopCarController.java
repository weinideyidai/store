package com.fei.store.controller;

import com.fei.store.common.utils.PageVO;
import com.fei.store.service.ShopCarService;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.ShopCarVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("car")
@RestController
public class ShopCarController {

    @Autowired
    private ShopCarService shopCarService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = shopCarService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("findAll")
    public ResponseEntity<?> findAll(){
        List<ShopCarVO> shopCarVOS = shopCarService.findAll();
        return ResponseEntity.ok(shopCarVOS);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        shopCarService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        shopCarService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody ShopCarVO shopCarVO){
        shopCarService.save(shopCarVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findShopCarById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(shopCarService.findShopCarById(id));
    }

    @RequestMapping("updateColour")
    public ResponseEntity<?> updateShopCar(@RequestBody ShopCarVO shopCarVO){
        shopCarService.updateShopCar(shopCarVO);
        return ResponseEntity.ok(null);
    }
}
