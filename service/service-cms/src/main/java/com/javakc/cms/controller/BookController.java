package com.javakc.cms.controller;

import com.alibaba.excel.EasyExcel;
import com.javakc.cms.entity.Book;
import com.javakc.cms.entity.Classification;
import com.javakc.cms.listener.ExcelListener;
import com.javakc.cms.service.BookService;
import com.javakc.cms.service.ClassificationService;
import com.javakc.cms.utils.BookUtils;
import com.javakc.cms.vo.BookData;
import com.javakc.cms.vo.BookQuery;
import com.javakc.commonutils.api.APICODE;
import com.javakc.commonutils.api.ResultCode;
import com.javakc.servicebase.hanler.HctfException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "书籍管理")
@RestController
@RequestMapping("/cms/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ClassificationService classificationService;

    @GetMapping
    @ApiOperation(value = "查询所有书籍",response = Book.class)
    public APICODE findAll(String msg){
        List<Book> all = bookService.findAll();
        return APICODE.OK().data("items",all);
    }

    @ApiOperation(value = "根据条件进行分页查询")
    @PostMapping("{pageNo}/{pageSize}") //@RequestBody(required = false) 设置传入对象可以为空
    public APICODE findPageBook(@RequestBody(required = false) BookQuery bookQuery, @PathVariable int pageNo, @PathVariable int pageSize) {
        Page<Book> page = bookService.findPageBook(bookQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<Book> bookList = page.getContent();
        return APICODE.OK().data("total", totalElements).data("items", bookList);
    }

    @ApiOperation(value = "新增书籍")
    @PostMapping("saveBook")
    public APICODE saveBook(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取书籍")
    @GetMapping("getBookById/{bookId}")
    public APICODE getBookById(@PathVariable String bookId) {
        Book book = bookService.getById(bookId);
        return APICODE.OK().data("book", book);
    }

    @ApiOperation(value = "修改书籍")
    @PostMapping("updateBook")
    public APICODE updateBook(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除书籍")
    @DeleteMapping("deleteById/{bookId}")
    public APICODE deleteById(@PathVariable String bookId) {
        bookService.removeById(bookId);
        return APICODE.OK();
    }
    @ApiOperation(value = "设置书籍上下架")
    @PutMapping("{bookId}/{status}")
    public APICODE upOrDownBook(@PathVariable String bookId, @PathVariable Integer status) {
        // ## 根据id查询书籍数据
        Book book = bookService.getById(bookId);
        book.setId(bookId);
        book.setStatus(status);
        // ## 修改数据
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }
    @PostMapping("queryByLevel")
    public APICODE queryByLevel(@RequestBody Classification classification){

        List<Classification> pageBook = classificationService.findPageBook(classification);

        return APICODE.OK().data("classification",pageBook);
    }

    @ApiOperation(value = "列表导出", notes = "使用阿里EasyExcel导出Excel格式的用户列表数据")
    @GetMapping("exportEasyExcel")
    public void exportEasyExcel(HttpServletResponse response) {
        try {
            // ## 查询所有书籍
            List<Book> bookList = bookService.findAll();
            // ## 定义导出列表集合
            List<BookData> bookDataList = new ArrayList<>();
            for (Book book : bookList) {
                BookData bookData = new BookData();
                BookData bookData1 = BookUtils.copyBook(book, bookData);
                bookDataList.add(bookData1);
            }
            String fileName = "booklist";
            // ## 设置响应信息
            response.reset();
            response.setContentType("application/vnd.ms-excel; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".xlsx");
            EasyExcel.write(response.getOutputStream(), BookData.class).sheet("书籍列表").doWrite(bookDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "列表导入", notes = "使用阿里EasyExcel导入Excel格式的用户列表数据")
    @PostMapping("importEasyExcel")
    public APICODE importEasyExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), BookData.class, new ExcelListener(bookService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return APICODE.OK();
    }

}
