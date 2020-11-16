package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.model.Service;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.request.ServiceRequest;
import net.javaguides.springboot.repository.BookRepository;
import net.javaguides.springboot.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceRequest serviceRequest){
        Service service = new Service(serviceRequest.getName(), serviceRequest.getPrice());

        serviceRepository.save(service);
        return ResponseEntity.ok().body("You have created a service!");
    }

    @PostMapping("/use")
    public ResponseEntity<?> useService(@Valid @RequestBody ServiceRequest serviceRequest){
        Optional<Book> optionalBook = bookRepository.findById(serviceRequest.getBookId());
        Optional<Service> optionalService = serviceRepository.findById(serviceRequest.getServiceId());
        Book book = optionalBook.get();
        Service service = optionalService.get();
        book.setBill(book.getBill() + service.getPrice());

        List<Service> services = book.getServices();

        services.add(service);
        bookRepository.save(book);

        return ResponseEntity.ok().body("You have arranged a service!");
    }

    @GetMapping("/get")
    public List<Service> getServices(@Valid @RequestBody ServiceRequest serviceRequest){
        return serviceRepository.findAll();
    }
}
