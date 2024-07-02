package com.example.demo.controller;

import com.example.demo.Response;
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
    public Response<UserDTO> login(@RequestBody UserDTO userDTO) {
        userDTO = userService.login(userDTO);
        return Response.newSuccess(userDTO);
    }

    @PostMapping("/api/register")
    public Response<UserDTO> register(@RequestBody UserDTO userDTO) {

    }

    @GetMapping("/user/{id}")
    public Response<UserDTO> getUserById(@PathVariable long id) {
        return Response.newSuccess(userService.getUserById(id));
    }

    @PostMapping("/user")
    public Response<Long> addNewUser(@RequestBody UserDTO userDTO) {
        // TODO：校验
        return Response.newSuccess(userService.addNewUser(userDTO));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/user/{id}")
    public Response<UserDTO> updateUserById(@PathVariable long id, @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String email)  {
        return Response.newSuccess(userService.updateUserById(id, name, email));
    }

}
