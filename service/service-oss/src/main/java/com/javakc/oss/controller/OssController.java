package com.javakc.oss.controller;


import com.javakc.commonutils.api.APICODE;
import com.javakc.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "云存储图片")
@RestController
@RequestMapping("oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("uploadFile")
    @ApiOperation(value = "上传图片")
    public APICODE uploadFile(MultipartFile file){
        String url = ossService.uploadFile(file);
        return APICODE.OK().data("url",url);
    }
}
