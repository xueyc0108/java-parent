package com.javakc.cms.service;

import com.javakc.cms.dao.BookDao;
import com.javakc.cms.entity.Book;
import com.javakc.cms.vo.BookQuery;
import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookService extends BaseService<BookDao,Book> {

    @Autowired
    private BookDao bookDao;

    public List<Book> findAll(){

        return bookDao.findAll();
    }

    /**
     * 根据条件进行分页查询
     * @param bookQuery
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> findPageBook(BookQuery bookQuery, int pageNo , int pageSize) {
        SimpleSpecificationBuilder simpleSpecificationBuilder = new SimpleSpecificationBuilder();
        if (null != bookQuery){
            if (!StringUtils.isEmpty(bookQuery.getTitle())) {
                simpleSpecificationBuilder.and("bookName", ":", bookQuery.getTitle());
            }
        }
        Specification<Book> specification = simpleSpecificationBuilder.getSpecification();

        Page page = bookDao.findAll(specification, PageRequest.of(pageNo - 1, pageSize));
        return page;
    }

}
