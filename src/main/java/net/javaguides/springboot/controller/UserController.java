package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.payload.request.LoginRequest;
import net.javaguides.springboot.payload.request.SignupRequest;
import net.javaguides.springboot.payload.response.JwtResponse;
import net.javaguides.springboot.payload.response.MessageResponse;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;
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
        User newUser = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        userRepository.save(newUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(
                newUser.getId(),
                newUser.getEmail(),
                jwt
        ));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest){
        if(!userRepository.existsByEmail(loginRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect login or password"));
        }
        User user = userRepository.findByEmail(loginRequest.getEmail());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            return ResponseEntity.ok(new JwtResponse(
                    user.getId(),
                    user.getEmail(),
                    jwt
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect login or password"));
        }
    }
}
