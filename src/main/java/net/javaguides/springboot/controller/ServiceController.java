package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.model.Service;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.request.ServiceRequest;
import net.javaguides.springboot.repository.BookRepository;
import net.javaguides.springboot.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping("/use")
    public ResponseEntity<?> userService(@Valid @RequestBody ServiceRequest serviceRequest){
        Optional<Book> optionalBook = bookRepository.findById(serviceRequest.getBookId());
        Optional<Service> optionalService = serviceRepository.findById(serviceRequest.getServiceId());
        Book book = optionalBook.get();
        Service service = optionalService.get();

        List<Service> services = book.getServices();

        services.add(service);
        bookRepository.save(book);

        return ResponseEntity.ok().body("You have arranged service!");
    }
}
