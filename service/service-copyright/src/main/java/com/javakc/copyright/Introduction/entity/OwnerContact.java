package com.javakc.copyright.Introduction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "copyright_owner_contact")
@ApiModel(value = "版权方联系人")
public class OwnerContact {
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value ="主键，自增长" )
    private Integer id ;

//    @Column(name = "owner_id")
//    @ApiModelProperty(value ="版权方管理外键" )
//    private Integer ownerId ;


    @ApiModelProperty(value ="版权方管理外键" )
    @JoinColumn(name = "owner_id")
    @ManyToOne
    private Owner owner;



    @Column(name = "name")
    @ApiModelProperty(value = "姓名")
    private String name ;

    @Column(name = "sex")
    @ApiModelProperty(value = "性别")
    private Integer sex ;

    @Column(name = "date_of_birth")
    @ApiModelProperty(value = "出生日期",example = "2020-12-12")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dateOfBirth ;

    @Column(name = "duties")
    @ApiModelProperty(value = "职务")
    private String duties ;

    @Column(name = "mobile_phone")
    @ApiModelProperty(value = "手机号")
    private String mobilePhone ;

    @Column(name = "tele_phone")
    @ApiModelProperty(value = "座机号")
    private String telePhone ;

    @Column(name = "email")
    @ApiModelProperty(value = "邮箱")
    private String email ;

    @Column(name = "qq")
    @ApiModelProperty(value = "qq")
    private String qq ;

    @Column(name = "company_address")
    @ApiModelProperty(value = "公司地址")
    private String companyAddress ;

    @Column(name = "remarks")
    @ApiModelProperty(value = "备注")
    private String remarks ;
}
