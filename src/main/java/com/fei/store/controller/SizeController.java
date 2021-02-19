package com.fei.store.controller;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.mapper.SizeMapper;
import com.fei.store.service.SizeService;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
import com.fei.store.vo.SizeVO;
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

@RequestMapping("size")
@RestController
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = sizeService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("findAll")
    public ResponseEntity<?> findAll(){
        List<SizeVO> brandsVOS = sizeService.findAll();
        return ResponseEntity.ok(brandsVOS);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        sizeService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        sizeService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid SizeVO sizeVO, BindingResult result){
        if (result.hasErrors()){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        sizeService.save(sizeVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findSizeById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(sizeService.findSizeById(id));
    }

    @RequestMapping("updateColour")
    public ResponseEntity<?> updateSize(@RequestBody SizeVO sizeVO){
        sizeService.updateSize(sizeVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("findName/{name}")
    public ResponseEntity<?> findSizeByName(@PathVariable("name") String name){
        String str = sizeService.findSizeByName(name);
        return ResponseEntity.ok(str);
    }
}
