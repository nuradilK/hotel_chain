package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.payload.request.LoginRequest;
import net.javaguides.springboot.payload.request.SignupRequest;
import net.javaguides.springboot.payload.response.JwtResponse;
import net.javaguides.springboot.payload.response.MessageResponse;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//  get all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@Valid @RequestBody SignupRequest signUpRequest){
        List<User> users = this.getAllUsers();

        for(User user: users){
            if(user.getEmail().equals(signUpRequest.getEmail())){
                return ResponseEntity.badRequest().body(new MessageResponse("A user with this email is registered!"));
            }
            if(user.getUsername().equals(signUpRequest.getUsername())){
                return ResponseEntity.badRequest().body(new MessageResponse("A user with this username is registered"));
            }
        }

        userRepository.save(new User(signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getEmail()));

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest){
        if(!userRepository.existsByUsername(loginRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect login or password"));
        }

        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        if(!user.get().getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect login or password"));
        }

        return ResponseEntity.ok(new JwtResponse(
                user.get().getId(),
                user.get().getUsername(),
                user.get().getEmail()));
    }
}
