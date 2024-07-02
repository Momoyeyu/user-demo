package com.example.demo.controller;

import com.example.demo.entity.Response;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public Response login(@RequestBody User user) {
        UserDTO userDTO = userService.login(user);
        if (userDTO == null)
            return Response.fail("wrong username or password!");
        return Response.success(userDTO);
    }

    @PostMapping("/api/register")
    public Response register(@RequestBody User user) {
        UserDTO userDTO = userService.register(user);
        if (userDTO == null)
            return Response.fail("username or email has been used!");
        return Response.success(userDTO);
    }

    @GetMapping("/user/{id}")
    public Response getUserById(@PathVariable long id) {
        return Response.success(userService.getUserById(id));
    }

    @PostMapping("/user")
    public Response addNewUser(@RequestBody UserDTO userDTO) {
        // TODO：校验
        return Response.success(userService.addNewUser(userDTO));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/user/{id}")
    public Response updateUserById(@PathVariable long id, @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String email)  {
        return Response.success(userService.updateUserById(id, name, email));
    }

}
