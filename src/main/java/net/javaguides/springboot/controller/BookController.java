package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.repository.BookRepository;
import net.javaguides.springboot.repository.RoomRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

        Optional<User> optionalUser = userRepository.findById(bookRequest.getUserID());
        User user = optionalUser.get();
        Optional<Room> optionalRoom = roomRepository.findById(bookRequest.getRoomID());
        Room room = optionalRoom.get();

        Book book = new Book(bookRequest.getFromDate(), bookRequest.getToDate(), room, user);
        bookRepository.save(book);

        return ResponseEntity.ok().body("You have booked!");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookRequest bookRequest){

        Optional<Book> optionalBook = bookRepository.findById(bookRequest.getBookID());
        Book book = optionalBook.get();
        book.setFromDate(bookRequest.getFromDate());
        book.setToDate(bookRequest.getToDate());

        bookRepository.save(book);

        return ResponseEntity.ok().body("You have changed the booking!");
    }
    @GetMapping
    public List<Book> getAllBooks() { return bookRepository.findAll(); }
}
