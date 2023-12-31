package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> getUsers(){

        List<UserDTO> userDTOList = userService.listAllUsers();
        return ResponseEntity.ok( new ResponseWrapper("Users are successfully retrieved", userDTOList, HttpStatus.OK));
    }

    @GetMapping("/{username}")
    @RolesAllowed({"Admin"})
    public ResponseEntity<ResponseWrapper> getUsersByUserName(@PathVariable("username") String userName){

        UserDTO userDTO = userService.findByUserName(userName);
        return ResponseEntity.ok(new ResponseWrapper("User is successfully retrieved", userDTO, HttpStatus.OK));
    }

    @PostMapping
    @RolesAllowed({"Admin"})
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){

        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("User is successfully created", HttpStatus.CREATED));
    }
    @RolesAllowed({"Admin"})
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user){
        userService.update(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("User is successfully updated", HttpStatus.OK));

    }
    @RolesAllowed({"Admin"})
    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String userName){
        userService.delete(userName);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("User is successfully deleted", HttpStatus.OK));

    }
}
