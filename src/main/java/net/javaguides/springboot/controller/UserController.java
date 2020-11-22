package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Hotel;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.UserCategory;
import net.javaguides.springboot.payload.request.LoginRequest;
import net.javaguides.springboot.payload.request.NotificationRequest;
import net.javaguides.springboot.payload.request.SignupRequest;
import net.javaguides.springboot.payload.request.UserCategoryRequest;
import net.javaguides.springboot.payload.response.JwtResponse;
import net.javaguides.springboot.payload.response.MessageResponse;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserCategoryRepository;
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
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/auth/signup")
    public ResponseEntity<?> singup(@Valid @RequestBody SignupRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("A user with this email is registered!"));
        }

        Role role;
        if (signUpRequest.getRole() == null) {
            role = roleRepository.findByName("User");
        } else {
            role = roleRepository.findByName(signUpRequest.getRole());
        }
        User newUser = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), role);
        userRepository.save(newUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(
                newUser.getId(),
                newUser.getEmail(),
                jwt,
                role
        ));
    }

    @PostMapping("/auth/add/userCategory")
    public ResponseEntity<?> createCategory(@Valid @RequestBody UserCategoryRequest userCategoryRequest){
        Optional<User> userOptional = userRepository.findById(userCategoryRequest.getUserId());
        User user = userOptional.get();
        Optional<UserCategory> userCategoryOptional = userCategoryRepository.findById(userCategoryRequest.getUserCategoryId());
        UserCategory userCategory = userCategoryOptional.get();

        List<User> users = userCategory.getUsers();
        users.add(user);
        user.setUserCategory(userCategory);
        userRepository.save(user);
        userCategoryRepository.save(userCategory);

        return ResponseEntity.ok().body("You have added a user to a category!");
    }

    @PostMapping("/auth/signin")
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
                    jwt,
                    user.getRole()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Incorrect login or password"));
        }
    }

    @PostMapping("/notification")
    public ResponseEntity<?> updateNotification(@Valid @RequestBody NotificationRequest notificationRequest) {
        if (notificationRequest.getUserId() == null) {
            List<User> users = userRepository.findAll();
            for (User user: users) {
                user.setNotification(true);
                userRepository.save(user);
            }
        } else {
            Optional<User> optionalUser = userRepository.findById(notificationRequest.getUserId());
            User user = optionalUser.get();
            user.setNotification(false);
            userRepository.save(user);
        }

        return ResponseEntity.ok().body("You have changed notification field!");
    }
}
