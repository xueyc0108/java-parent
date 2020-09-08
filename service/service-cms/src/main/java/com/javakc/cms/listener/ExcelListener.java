package com.javakc.cms.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.javakc.cms.entity.Book;
import com.javakc.cms.service.BookService;
import com.javakc.cms.utils.BookUtils;
import com.javakc.cms.vo.BookData;

public class ExcelListener extends AnalysisEventListener<BookData> {


    private BookService bookService;

    public ExcelListener() {
    }
    public ExcelListener(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void invoke(BookData bookData, AnalysisContext analysisContext) {
        Book book=new Book();

        bookService.saveOrUpdate(BookUtils.copyBookData(book, bookData));

    }




    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
