package com.api.book.bootrestbook.Controllers;

import com.api.book.bootrestbook.Entities.Book;
import com.api.book.bootrestbook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    //get all books
    @RequestMapping(value = "/books",method = RequestMethod.GET)
    //@ResponseBody
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list=bookService.getAllBooks();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    //get single book
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book=bookService.getBookById(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }


    //create
    @PostMapping("/books")

    public ResponseEntity<Book> addBook(@RequestBody Book book){ //to convert to object book
        Book b =null;
        try {
            b=this.bookService.addBook(book);
            System.out.println(b);
            return ResponseEntity.status(HttpStatus.CREATED).build();
            //return ResponseEntity.of(Optional.of(book));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //delete
    @DeleteMapping("/books/{bookId}")
    public  ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
        try {
            this.bookService.deleteBook(bookId);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update
    @PutMapping("/books/{bookId}")
    public  ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
        try {
            this.bookService.updateBook(book,bookId);
            return ResponseEntity.ok().body(book);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
