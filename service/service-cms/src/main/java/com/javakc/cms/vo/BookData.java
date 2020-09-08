package com.javakc.cms.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BookData {
    
    @ExcelProperty(value = "书名", index = 0)
    private String title;

    @ExcelProperty(value = "作者", index = 1)
    private String author;

    @ExcelProperty(value = "一级分类", index = 2)
    private String firstSort;

    @ExcelProperty(value = "二级分类", index = 3)
    private String secondSort;

    @ExcelProperty(value = "是否连载", index = 4)
    private Integer serialize;

    @ExcelProperty(value = "是否上线或未上线状态", index = 5)
    private Integer status;

    @ExcelProperty(value = "是否付费", index = 6)
    private Integer free;

    @ExcelProperty(value = "授权开始时间", index = 7)
    private Date startTime;

    @ExcelProperty(value = "授权结束时间", index = 8)
    private Date endTime;

    @ExcelProperty(value = "是否原创", index = 9)
    private Integer original;
}
