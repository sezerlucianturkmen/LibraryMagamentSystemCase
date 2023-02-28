package com.example.demo.utility;

import com.example.demo.entity.book.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BookFactory {


    public  Book getBook(String type, String title, String author, String isbn, Integer availableCopies){
        if("journal".equalsIgnoreCase(type)) return Journal.builder().title(title).author(author).isbn(isbn).availableCopies(availableCopies).type(Type.JOURNAL).build();
        else if ("magazine".equalsIgnoreCase(type))  return Magazine.builder().title(title).author(author).isbn(isbn).availableCopies(availableCopies).type(Type.MAGAZINE).build();
        else if ("novel".equalsIgnoreCase(type))  return Novel.builder().title(title).author(author).isbn(isbn).availableCopies(availableCopies).type(Type.NOVEL).build();
        else if ("sciencebook".equalsIgnoreCase(type))  return ScienceBook.builder().title(title).author(author).isbn(isbn).availableCopies(availableCopies).type(Type.SCIENCEBOOK).build();
        else return null;
    }
}
