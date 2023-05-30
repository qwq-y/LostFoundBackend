package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<String> createUser(
        @RequestParam Long studentId,
        @RequestParam String name,
        @RequestParam String password,
        @RequestParam String type
    ) {
        return userService.createUser(studentId, name, password, type);
    }

    @PutMapping("/updatePasswordByStudentId")
    public ResponseEntity<String> updatePasswordByStudentId(
        @RequestParam("studentId") Long studentId,
        @RequestParam("passwordOld") String passwordOld,
        @RequestParam("passwordOld") String passwordNew
    ) {
        return userService.updatePasswordByStudentId(studentId, passwordOld,passwordNew);
    }

}
