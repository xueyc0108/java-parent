package com.javakc.copyright.Introduction.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 版权引进模块 版权方管理实体类
 */
@Data
@Entity
@Table(name = "copyright_owner")
@ApiModel(value = "版权方")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value ="主键，自增长" )
    private Integer id ;
    /** 版权名 */
    @Column(name = "copyright_name")
    @ApiModelProperty(value = "版权名")
    private String copyrightName ;
    /** 公司 */
    @Column(name = "company")
    @ApiModelProperty(value = "公司")
    private String company ;
    /** 备注名 */
    @Column(name = "note_name")
    @ApiModelProperty(value = "备注名")
    private String noteName ;

//    @Column(name = "cooperation_status")
//    @ApiModelProperty(value = "合作状态")
//    private Integer cooperationStatus;//合作状态  1：生效   0：失效
//
//    @Column(name = "info")
//    @ApiModelProperty(value = "版权简介")
//    private String info;//版权简介

}
