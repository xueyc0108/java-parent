package com.javakc.cms.service;

import com.javakc.commonutils.api.APICODE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * oss 服务接口
 */
@FeignClient("service-oss") //要调用的服务
@Component  //注解防止，在其他位置注入CodClient时idea报错
public interface OssService {
    @DeleteMapping("/oss/deleteFile") //要调用的方法  调用oss微服务的删除方法
    APICODE deleteFile(String url);
}
