package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.UserCategory;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.request.UserCategoryRequest;
import net.javaguides.springboot.repository.UserCategoryRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/userCategory")
public class UserCategoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createUserCategory(@Valid @RequestBody UserCategoryRequest userCategoryRequest){
        UserCategory userCategory = new UserCategory(userCategoryRequest.getName(), userCategoryRequest.getCoefficient());
        userCategoryRepository.save(userCategory);
        return ResponseEntity.ok().body("You have created a user category!");
    }
}
