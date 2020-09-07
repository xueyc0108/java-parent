package com.javakc.cms.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_book")
@ApiModel(value = "书籍实体类")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @OneToOne
    @JoinColumn(name = "first_sort")
    private Classification classification1;


    @OneToOne
    @JoinColumn(name = "second_sort")
    private Classification classification2;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "uuid")
    @ApiModelProperty(value = "书籍主键,采用hibernate的uuid生成32位字符串")
    private String id;

    @Column(name = "title")
    @ApiModelProperty(value = "书名")
    private String title;
    @Column(name = "author")
    @ApiModelProperty(value = "作者名")
    private String author;

//    @Column(name = "first_sort")
//    @ApiModelProperty(value = "一级分类")
//    private Integer firstSort;
//
//    @Column(name = "second_sort")
//    @ApiModelProperty(value = "二级分类")
//    private Integer secondSort;

    @Column(name = "is_serial")
    @ApiModelProperty(value = "是否连载")
    private Integer serialize;

    @Column(name = "word_number")
    @ApiModelProperty(value = "字数")
    private Integer wordNumber;

    @Column(name = "is_state")
    @ApiModelProperty(value = "状态 是否上线")
    private Integer status;

    @Column(name = "is_charge")
    @ApiModelProperty(value = "是否收费")
    private Integer free;

    @Column(name = "start_time")
    @ApiModelProperty(value = "授权开始时间")
    private Date startTime;

    @Column(name = "end_time")
    @ApiModelProperty(value = "授权结束时间")
    private Date endTime;

    @Column(name = "is_original")
    @ApiModelProperty(value = "是否原创")
    private Integer original;

    @Column(name = "info")
    @ApiModelProperty(value = "简介")
    private String info;

    @Column(name = "image_url")
    @ApiModelProperty(value = "书封")
    private String imageUrl;


    @Column(name = "gmt_create",nullable = false,updatable = false)
    @ApiModelProperty(value = "创建时间", example = "2020-12-12 9:00:00")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
    private Date gmtCreate;

    @Column(name = "gmt_modified",nullable = false,insertable = false)
    @ApiModelProperty(value = "更新时间", example = "2020-12-12 9:00:00")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
    private Date gmtModified;
}
