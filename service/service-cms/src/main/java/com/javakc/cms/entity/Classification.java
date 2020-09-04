package com.javakc.cms.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cms_classification")
public class Classification {



    @Column(name = "level")
    @ApiModelProperty(value = "分类级别")
    private Integer level;

    @Column(name = "name")
    @ApiModelProperty(value = "级别名")
    private String name;
    @Id
    @Column(name = "code")
    @ApiModelProperty(value = "级别编号")
    private Integer code;

    @Column(name = "ecoding")
    @ApiModelProperty(value = "上级编号")
    private Integer ecoding;

}
