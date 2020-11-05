package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.response.JwtResponse;
import net.javaguides.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/room")
    public ResponseEntity<?> book(@Valid @RequestBody BookRequest bookRequest){
        Book book = new Book(bookRequest.getRoom(), bookRequest.getUserId(), bookRequest.getFromDate(), bookRequest.getToDate());
        bookRepository.save(book);

        return ResponseEntity.ok().body("You have booked!");
    }
}
