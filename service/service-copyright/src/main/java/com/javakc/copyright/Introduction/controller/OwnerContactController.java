package com.javakc.copyright.Introduction.controller;

import com.javakc.commonutils.api.APICODE;
import com.javakc.copyright.Introduction.entity.Owner;
import com.javakc.copyright.Introduction.entity.OwnerContact;
import com.javakc.copyright.Introduction.service.OwnerContactService;
import com.javakc.copyright.Introduction.vo.OwnerContactQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "版权方客户管理")
@RestController
@RequestMapping("copyright/introduction/ownerContact")
@CrossOrigin
public class OwnerContactController {
    @Autowired
    private OwnerContactService ownerContactService;

    @GetMapping
    @ApiOperation(value = "查询此版权所有客户",response = OwnerContact.class)
    public APICODE findAll(){
        List<OwnerContact> all = ownerContactService.findAll();
        return APICODE.OK().data("items",all);
    }

    @ApiOperation(value = "根据条件进行分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageOwner(@RequestBody(required = false) OwnerContactQuery ownerContactQuery, @PathVariable int pageNo, @PathVariable int pageSize){
        Page<OwnerContact> page = ownerContactService.findPageOwnerContact(ownerContactQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<OwnerContact> list=page.getContent();

        return APICODE.OK().data("total",totalElements).data("items",list);
    }

    @ApiOperation(value = "新增版权客户")
    @PostMapping("saveOwnerContact")
    public APICODE saveBook(@RequestBody OwnerContact ownerContact) {
        ownerContactService.saveOrUpdate(ownerContact);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取客户")
    @GetMapping("getOwnerContactById/{OwnerContactId}")
    public APICODE getOwnerContactById(@PathVariable Integer OwnerContactId) {
        OwnerContact ownerContact = ownerContactService.getById(OwnerContactId);
        return APICODE.OK().data("book", ownerContact);
    }

    @ApiOperation(value = "修改客户信息")
    @PostMapping("updateOwnerContact")
    public APICODE updateOwnerContact(@RequestBody OwnerContact ownerContact) {
        ownerContactService.saveOrUpdate(ownerContact);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除客户")
    @DeleteMapping("deleteById/{ownerContactId}")
    public APICODE deleteById(@PathVariable Integer ownerContactId) {
        ownerContactService.removeById(ownerContactId);
        return APICODE.OK();
    }

}
