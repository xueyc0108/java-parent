package com.javakc.copyright.Introduction.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 版权引进模块  批次管理实体类
 */
@Data
@Entity
@Table(name = "copyright_batch")
@ApiModel(value = "批次管理")
public class Batch {
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value ="主键，自增长" )
    private Integer id ;

    /** 外键;对应接口主键 */
    private Integer intefaceId ;


    @Column(name = "batch_name")
    @ApiModelProperty(value = "批次名称")
    private String batchName ;

    @Column(name = "authorization_start_time")
    @ApiModelProperty(value = "授权开始时间")
    private Date authorizationStartTime ;

    @Column(name = "authorization_end_time")
    @ApiModelProperty(value = "授权结束时间")
    private String authorizationEndTime ;

    @Column(name = "first_party")
    @ApiModelProperty(value = "甲方分成")
    private String firstParty ;

    @Column(name = "second_party")
    @ApiModelProperty(value = "乙方分成")
    private String secondParty ;
}
