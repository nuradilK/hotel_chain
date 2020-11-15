package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.repository.BookRepository;
import net.javaguides.springboot.repository.RoomRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/room")
    public ResponseEntity<?> book(@Valid @RequestBody BookRequest bookRequest){

        Book book = new Book(bookRequest.getFromDate(), bookRequest.getToDate());
        bookRepository.save(book);

        return ResponseEntity.ok().body("You have booked!");
    }
    @GetMapping
    public List<Book> getAllBooks() { return bookRepository.findAll(); }
}
