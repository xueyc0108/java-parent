package com.javakc.cms.utils;

import com.javakc.cms.entity.Book;
import com.javakc.cms.entity.Classification;
import com.javakc.cms.vo.BookData;


public class BookUtils {

    public static BookData copyBook(Book book, BookData bookData){
        bookData.setTitle(book.getTitle());
        bookData.setAuthor(book.getAuthor());
        bookData.setFirstSort(book.getClassification1().getName());
        bookData.setSecondSort(book.getClassification2().getName());
        bookData.setSerialize(book.getSerialize());
        bookData.setOriginal(book.getOriginal());
        bookData.setStatus(book.getStatus());
        bookData.setFree(book.getFree());
        bookData.setStartTime(book.getStartTime());
        bookData.setEndTime(book.getEndTime());

        return bookData;
    }
    public static Book copyBookData(Book book, BookData bookData){

        book.setTitle(bookData.getTitle());
        book.setAuthor(bookData.getAuthor());


        Classification classification1=new Classification();
        classification1.setCode(Integer.valueOf(bookData.getFirstSort()));
        book.setClassification1(classification1);
        Classification classification2=new Classification();
        classification2.setCode(Integer.valueOf(bookData.getSecondSort()));
        book.setClassification2(classification2);


        book.setSerialize(bookData.getSerialize());
        book.setOriginal(bookData.getOriginal());
        book.setStatus(bookData.getStatus());
        book.setFree(bookData.getFree());
        book.setStartTime(bookData.getStartTime());
        book.setEndTime(bookData.getEndTime());

        return book;
    }
}
