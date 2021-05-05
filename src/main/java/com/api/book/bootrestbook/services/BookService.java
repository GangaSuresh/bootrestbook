package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.Entities.Book;
import com.api.book.bootrestbook.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {
//    private static List<Book> list=new ArrayList<>();
//    static {
//        list.add(new Book(123,"java book","JOhn K"));
//        list.add(new Book(333,"math book","Popo"));
//        list.add(new Book(456,"science book","Keith"));
//    }
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
       List<Book> list= (List<Book>) this.bookRepository.findAll();
       return list;
    }



//    public List<Book> getAllBooks(){
//        return list;
//    }
    public Book getBookById(int id){
        Book book=null;
        try {
            //book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book=this.bookRepository.findById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return book;
    }
    public Book addBook(Book b){
        //list.add(b);
        Book result=bookRepository.save(b);
        return result;
    }

    public  void deleteBook(int bid){
        // list=list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
        bookRepository.deleteById(bid);

    }
    public  void updateBook(Book book, int bid){ //map traverses through each object
//       list= list.stream().map(b->{
//            if (b.getId()==bid){
//                b.setTitle(book.getTitle());
//                b.setAuthor(book.getAuthor());
//            }
//            return b;
//        }).collect(Collectors.toList());

        book.setId(bid);
        bookRepository.deleteById(bid); //remove old
        bookRepository.save(book); //add new
    }
}
