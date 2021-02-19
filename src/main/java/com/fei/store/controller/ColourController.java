package com.fei.store.controller;

import com.fei.store.common.enums.ExceptionEnum;
import com.fei.store.common.exception.MyException;
import com.fei.store.common.utils.PageVO;
import com.fei.store.service.ColourService;
import com.fei.store.vo.BrandsVO;
import com.fei.store.vo.ColourVO;
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
@RequestMapping("colour")
public class ColourController {

    @Autowired
    private ColourService colourService;

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = colourService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }

    @RequestMapping("findAll")
    public ResponseEntity<?> findAll(){
        List<ColourVO> colourVOS = colourService.findAll();
        return ResponseEntity.ok(colourVOS);
    }

    @RequestMapping("updateStateId/{id}/{type}")
    public ResponseEntity<?> updateStateById(@PathVariable("id") Integer id , @PathVariable("type") Integer type){
        colourService.updateStateById(id,type);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("updateStateIds")
    public ResponseEntity<?> updateStateByIds(@RequestBody List<Integer> ids){
        colourService.updateStateByIds(ids);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid ColourVO colourVO, BindingResult result){
        if (result.hasErrors()){
            throw new MyException(ExceptionEnum.SERVER_CONGESTION);
        }
        colourService.save(colourVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("find/{id}")
    public ResponseEntity<?> findColourById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(colourService.findColourById(id));
    }

    @RequestMapping("updateColour")
    public ResponseEntity<?> updateColour(@RequestBody ColourVO colourVO){
        colourService.updateColour(colourVO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping("findName/{name}")
    public ResponseEntity<?> findColourByName(@PathVariable("name") String name){
        String str = colourService.findColourByName(name);
        return ResponseEntity.ok(str);
    }
}
