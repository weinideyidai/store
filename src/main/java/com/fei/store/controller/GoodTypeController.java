package com.fei.store.controller;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.service.GoodTypeService;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.GoodTypeVO;
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
@RequestMapping("goodType")
public class GoodTypeController {

    @Autowired
    private GoodTypeService goodTypeService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = goodTypeService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("findAll")
    public ResponseEntity<?> findAll(){
        List<BrandsVO> brandsVOS = goodTypeService.findAll();
        return ResponseEntity.ok(brandsVOS);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        goodTypeService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        goodTypeService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid GoodTypeVO goodTypeVO , BindingResult result){
        if (result.hasErrors()){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        goodTypeService.save(goodTypeVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findGoodTypeById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(goodTypeService.findGoodTypeById(id));
    }

    @RequestMapping("updateGoodType")
    public ResponseEntity<?> updateGoodType(@RequestBody GoodTypeVO goodTypeVO){
        goodTypeService.updateGoodType(goodTypeVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("findName/{name}")
    public ResponseEntity<?> findGoodTypeByName(@PathVariable("name") String name){
        String str = goodTypeService.findGoodTypeByName(name);
        return ResponseEntity.ok(str);
    }
}
