package com.fei.store.controller;

import com.fei.store.common.utils.ExcelUtiles;
import com.fei.store.service.GoodsService;
import com.fei.store.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<GoodsVO> goodsVOS = goodsService.findAll();
        ExcelUtiles.exportExcel(goodsVOS, "商品", "商品", GoodsVO.class, "商品.xls", response);

    }

    @PostMapping("/importExcel")
    public void importExcel2(@RequestParam("file") MultipartFile file) {
        List<GoodsVO> goodsVOS = ExcelUtiles.importExcel(file, 1, 1, GoodsVO.class);
        for (GoodsVO goodsVO : goodsVOS){
            goodsService.save(goodsVO);
        }
        System.out.println(goodsVOS);
    }

}
