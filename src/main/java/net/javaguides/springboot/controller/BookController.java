package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.response.JwtResponse;
import net.javaguides.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/book")
    public ResponseEntity<?> book(@Valid @RequestBody BookRequest bookRequest){
        Book book = new Book(bookRequest.getHotelId(), bookRequest.getRoomTypes(), bookRequest.getRooms());
        bookRepository.save(book);

        return ResponseEntity.ok().body("You have booked!");
    }
}
