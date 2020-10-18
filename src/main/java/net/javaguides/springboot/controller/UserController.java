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

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/auth/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

////  get all users
//    @GetMapping("/")
//    public List<User> getAllUsers(){
//        return userRepository.findAll();
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@Valid @RequestBody SignupRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("A user with this email is registered!"));
        }
        User newUser = new User(signUpRequest.getEmail(), signUpRequest.getPassword());
        userRepository.save(newUser);
        return ResponseEntity.ok(new JwtResponse(
                newUser.getId(),
                newUser.getEmail(),
                "jsonwebtoken"
        ));

    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest){
        if(!userRepository.existsByEmail(loginRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect login or password"));
        }

        User user = userRepository.findByEmail(loginRequest.getEmail());

        if(!user.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect login or password"));
        }

        return ResponseEntity.ok(new JwtResponse(
                user.getId(),
                user.getEmail(),
                "jsonwebtoken"
                ));
    }
}
