package com.fei.store.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "shop", type = "goods")
public class GoodsVO implements Serializable {

    @Id
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    @Excel(name = "姓名", orderNum = "1")
    @Field(type = FieldType.Text , analyzer = "ik_max_word")
    private String name;

    @NotNull(message = "价格不能为空")
    @Excel(name = "价格", orderNum = "2")
    private Integer price;

    @Excel(name = "图片", orderNum = "3")
    private String img;

    @NotNull(message = "商品类型id不能为空")
    @Excel(name = "商品类型id", orderNum = "10")
    private Integer tid;

    @NotNull(message = "品牌id不能为空")
    @Excel(name = "品牌id", orderNum = "11")
    private Integer bid;

    @NotNull(message = "颜色id不能为空")
    @Excel(name = "颜色id", orderNum = "12")
    private Integer cid;

    @NotNull(message = "尺寸id不能为空")
    @Excel(name = "尺寸id", orderNum = "13")
    private Integer sid;

    @NotNull(message = "库存不能为空")
    @Excel(name = "库存", orderNum = "8")
    private Integer count;

    @Excel(name = "状态", orderNum = "9")
    private Integer status;

    @ExcelEntity
    private BrandsVO brandsVO;
    @ExcelEntity
    private ColourVO colourVO;
    @ExcelEntity
    private GoodTypeVO goodTypeVO;
    @ExcelEntity
    private SizeVO sizeVO;
}
