package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.model.Room;
import net.javaguides.springboot.model.RoomType;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.repository.BookRepository;
import net.javaguides.springboot.repository.RoomRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    @PostMapping("/room")
    public ResponseEntity<?> book(@Valid @RequestBody BookRequest bookRequest){

        Optional<User> optionalUser = userRepository.findById(bookRequest.getUserID());
        User user = optionalUser.get();
        Optional<Room> optionalRoom = roomRepository.findById(bookRequest.getRoomID());
        Room room = optionalRoom.get();
        double bill = 0;

        Date from = bookRequest.getFromDate();
        Date to = bookRequest.getToDate();

        List<Integer> days = new ArrayList<>(8);

        while(from.getTime() < to.getTime()) {
            int weekDay = getDayNumberOld(from);
            days.set(weekDay, days.get(weekDay) + 1);

            from.setTime(from.getTime() + 86400000);
        }
        RoomType roomType = room.getRoomType();
        bill += days.get(1) * roomType.getPrice_W();
        bill += days.get(2) * roomType.getPrice_R();
        bill += days.get(3) * roomType.getPrice_F();
        bill += days.get(4) * roomType.getPrice_St();
        bill += days.get(5) * roomType.getPrice_Sn();
        bill += days.get(6) * roomType.getPrice_M();
        bill += days.get(7) * roomType.getPrice_T();

        Book book = new Book(bookRequest.getFromDate(), bookRequest.getToDate(), room, user, bill);
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
