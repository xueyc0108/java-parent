package com.javakc.copyright.Introduction.controller;


import com.javakc.commonutils.api.APICODE;
import com.javakc.copyright.Introduction.entity.Owner;
import com.javakc.copyright.Introduction.service.OwnerService;
import com.javakc.copyright.Introduction.vo.QueryOwner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 版权方管理 表现层
 *
 */
@Api(tags = "版权方管理")
@RestController
@RequestMapping("copyright/introduction/owner")
@CrossOrigin
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    @ApiOperation(value = "查询所有版权方")
    public APICODE findAll(){
        return APICODE.OK().data("items",ownerService.findAll());
    }

    @ApiOperation(value = "根据条件进行分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageOwner(@RequestBody(required = false) QueryOwner owner, @PathVariable int pageNo, @PathVariable int pageSize){
        Page<Owner> page = ownerService.findPageOwner(owner, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<Owner> list=page.getContent();

        return APICODE.OK().data("total",totalElements).data("items",list);
    }

    @ApiOperation(value = "新增版权方")
    @PostMapping("saveOwner")
    public APICODE saveOwner(@RequestBody Owner owner) {
        ownerService.saveOrUpdate(owner);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取版权方")
    @GetMapping("getOwnerById/{ownerId}")
    public APICODE getOwnerById(@PathVariable Integer ownerId) {
        Owner owner = ownerService.getById(ownerId);
        return APICODE.OK().data("owner", owner);
    }

    @ApiOperation(value = "修改版权方")
    @PostMapping("updateOwner")
    public APICODE updateOwner(@RequestBody Owner owner) {
        ownerService.saveOrUpdate(owner);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除版权方")
    @DeleteMapping("deleteById/{ownerId}")
    public APICODE deleteById(@PathVariable Integer ownerId) {
        ownerService.removeById(ownerId);
        return APICODE.OK();
    }

}
