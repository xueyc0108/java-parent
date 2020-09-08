package com.javakc.copyright.Introduction.service;

import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.copyright.Introduction.dao.OwnerContactDao;
import com.javakc.copyright.Introduction.entity.Owner;
import com.javakc.copyright.Introduction.entity.OwnerContact;
import com.javakc.copyright.Introduction.vo.OwnerContactQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.awt.print.Book;
import java.util.List;

@Service
public class OwnerContactService extends BaseService<OwnerContactDao, OwnerContact> {
    @Autowired
    private OwnerContactDao ownerContactDao;

    /**
     * 查询所有人员
     * @return
     */
    public List<OwnerContact> findAll(){
        return ownerContactDao.findAll();
    }
    public Page<OwnerContact> findPageOwnerContact(OwnerContactQuery ownerContactQuery, int pageNo , int pageSize) {
        Specification<OwnerContact> specification = new Specification<OwnerContact>() {
            @Override
            public Predicate toPredicate(Root<OwnerContact> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();//动态SQL表达式
                List<Expression<Boolean>> exList = predicate.getExpressions();//动态SQL表达式集合
                if(!StringUtils.isEmpty(ownerContactQuery.getName())){
                    exList.add(cb.equal(root.get("name"), ownerContactQuery.getName()));
                }
                if (!StringUtils.isEmpty(ownerContactQuery.getCopyrightName())) {
                    Join<Owner, OwnerContact> join = root.join("owner", JoinType.LEFT);
                    exList.add(cb.equal(join.get("copyrightName"), ownerContactQuery.getCopyrightName()));
                }
                return predicate;
            }
        };

        Page page = ownerContactDao.findAll(specification, PageRequest.of(pageNo - 1, pageSize));

        return page;
    }


}
