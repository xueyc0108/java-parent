package com.javakc.cms.vo.category;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类
 */
@Data
public class FirstCategory {
    private String id;
    private String title;
    private List<SecondCategory> secondCategoryList=new ArrayList<>();
}
