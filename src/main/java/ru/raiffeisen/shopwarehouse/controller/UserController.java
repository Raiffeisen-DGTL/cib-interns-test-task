package ru.raiffeisen.shopwarehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.shopwarehouse.entity.User;
import ru.raiffeisen.shopwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return userService.create(user) ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/user/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") long id) {
        User user = userService.get(id);
        return  !user.equals(null) ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/users/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> user = userService.getAll();
        if(!user.isEmpty() && !user.equals(null)) {
           return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/users/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.update(user, user.getId()) ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/users/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") long id) {
        return userService.delete(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
