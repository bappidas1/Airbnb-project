package com.airbnb.controller;

import com.airbnb.dto.AppUserDto;
import com.airbnb.dto.JWTToken;
import com.airbnb.dto.LoginDto;
import com.airbnb.dto.UserDto;
import com.airbnb.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/auth")
public class AuthController {
    private AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody AppUserDto appUserDto,
            BindingResult result

    ) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }
        appUserDto.setRole("ROLE_USER");
        AppUserDto savedUserDetails = appUserService.createUserDetails(appUserDto);
        return new ResponseEntity<>(savedUserDetails, HttpStatus.CREATED);
    }


    @PostMapping("/createPropertyOwner")
    public ResponseEntity<AppUserDto> createPropertyOwner(@RequestBody AppUserDto appUserDto) {
        appUserDto.setRole("ROLE_OWNER");
        AppUserDto savedUserDetails = appUserService.createPropertyOwnerDetails(appUserDto);
        return new ResponseEntity<>(savedUserDetails, HttpStatus.CREATED);
    }


    @PostMapping("/createPropertyManager")
    public ResponseEntity<AppUserDto> createPropertyManager(@RequestBody AppUserDto appUserDto) {
        appUserDto.setRole("ROLE_MANAGER");
        AppUserDto savedUserDetails = appUserService.createPropertyManagerDetails(appUserDto);
        return new ResponseEntity<>(savedUserDetails, HttpStatus.CREATED);
    }


    @PostMapping("/logIn")

    public ResponseEntity<?> signIn(@RequestBody LoginDto loginDto) {
        String token = appUserService.verifyLogIn(loginDto);
        JWTToken jwtToken = new JWTToken();

        if (token != null) {
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Username/password", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam long userId) {
        appUserService.deleteUserDetails(userId);
        return new ResponseEntity<>("User is Deleted" + userId, HttpStatus.NO_CONTENT);
    }
//    public ResponseEntity<AppUserDto> updateUser(@RequestBody AppUserDto appUserDto, @RequestParam long userId){
//        appUserService.updateUserDetails(appUserDto, userId);
//    }

    @GetMapping
    public ResponseEntity<UserDto<AppUserDto>> getAllUser(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "name", required = false) String sortDir) {
        UserDto<AppUserDto> allUser = appUserService.getAllUser(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<AppUserDto> getUserById(@RequestParam long id) {
        AppUserDto userById = appUserService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AppUserDto> updateUser(
            @RequestBody AppUserDto appUserDto, @RequestParam long id) {
        AppUserDto updatedUserDetails = appUserService.updateUser(appUserDto, id);
        return new ResponseEntity<>(updatedUserDetails,HttpStatus.OK);
    }



}
