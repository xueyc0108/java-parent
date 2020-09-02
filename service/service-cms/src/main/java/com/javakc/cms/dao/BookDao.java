package com.javakc.cms.dao;

import com.javakc.cms.entity.Book;
import com.javakc.commonutils.jpa.base.dao.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;


public interface BookDao extends BaseDao<Book,String> {
}
