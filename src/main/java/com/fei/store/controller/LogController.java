package com.fei.store.controller;

import com.fei.store.common.utils.PageVO;
import com.fei.store.service.LogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;


    @RequestMapping("deleteId/{id}")
    public ResponseEntity<?> deleteById(@ApiParam("日志id") @PathVariable("id") Integer id){
        logService.deletById(id);
        return ResponseEntity.ok("操作成功");
    }

    @RequestMapping("deleteIds")
    public ResponseEntity<?> deleteByIds(@ApiParam("日志ids") @RequestBody List<Integer> ids){
        logService.deletByIds(ids);
        return ResponseEntity.ok("操作成功");
    }

    @RequestMapping("all")
    public ResponseEntity<?> findAllByPage(@RequestBody PageVO pageVO){
        PageInfo pageInfo = logService.findAllByPage(pageVO);
        return ResponseEntity.ok(pageInfo);
    }


}
