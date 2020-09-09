package com.javakc.copyright.Introduction.service;

import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.copyright.Introduction.dao.OwnerDao;
import com.javakc.copyright.Introduction.entity.Owner;
import com.javakc.copyright.Introduction.vo.QueryOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 版权方管理逻辑层
 */
@Service
public class OwnerService extends BaseService<OwnerDao, Owner> {

    public List<Owner> findAll(){
        return dao.findAll();
    }

    public Page<Owner> findPageOwner(QueryOwner owner, int pageNo , int pageSize) {
        SimpleSpecificationBuilder simpleSpecificationBuilder = new SimpleSpecificationBuilder();
        if (null != owner){
            if (!StringUtils.isEmpty(owner.getCopyrightName())) {
                simpleSpecificationBuilder.and("copyrightName", ":", owner.getCopyrightName());
            }
            if (!StringUtils.isEmpty(owner.getCompany())) {
                simpleSpecificationBuilder.and("company", ":", owner.getCompany());
            }
            if (!StringUtils.isEmpty(owner.getNoteName())) {
                simpleSpecificationBuilder.and("noteName", ":", owner.getNoteName());
            }
        }
        Specification<Owner> specification = simpleSpecificationBuilder.getSpecification();

        Page page = dao.findAll(specification, PageRequest.of(pageNo - 1, pageSize));

        return page;
    }
}
