package com.javakc.cms.controller;

import com.javakc.cms.service.CategoryService;
import com.javakc.cms.vo.category.FirstCategory;
import com.javakc.commonutils.api.APICODE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "一二级分类管理")
@RestController
@RequestMapping("/cms/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取树形分类数据")
    @GetMapping
    public APICODE getCategoryList() {
        List<FirstCategory> categoryList = categoryService.getCategoryList();
        return APICODE.OK().data("items", categoryList);
    }
}
