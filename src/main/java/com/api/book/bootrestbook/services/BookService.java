package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.Entities.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {
    private static List<Book> list=new ArrayList<>();
    static {
        list.add(new Book(123,"java book","JOhn K"));
        list.add(new Book(333,"math book","Popo"));
        list.add(new Book(456,"science book","Keith"));
    }

    public List<Book> getAllBooks(){
        return list;
    }
    public Book getBookById(int id){
        Book book=null;
        try {
            book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return book;
    }
    public Book addBook(Book b){
        list.add(b);
        return b;
    }

    public  void deleteBook(int bid){
         list=list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());

    }
    public  void updateBook(Book book, int bid){ //map traverses through each object
       list= list.stream().map(b->{
            if (b.getId()==bid){
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
    }
}
