package com.javakc.cms.service;

import antlr.ASTNULLType;
import com.javakc.cms.dao.ClassificationDao;
import com.javakc.cms.entity.Book;
import com.javakc.cms.entity.Classification;
import com.javakc.cms.vo.BookQuery;
import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClassificationService extends BaseService<ClassificationDao, Classification> {


    public List<Classification> findPageBook(Classification classification) {
        SimpleSpecificationBuilder simpleSpecificationBuilder = new SimpleSpecificationBuilder();
        if (null != classification){
            if (!StringUtils.isEmpty(classification.getLevel())) {
                simpleSpecificationBuilder.and("level", "=", classification.getLevel());

            }
            if (!StringUtils.isEmpty(classification.getCode())){
                simpleSpecificationBuilder.and("ecoding", "=", classification.getCode());
            }

        }
        Specification<Classification> specification = simpleSpecificationBuilder.getSpecification();

        List<Classification> list = dao.findAll(specification);

        return list;
    }
}
