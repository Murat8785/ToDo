package com.MuratApps.todo_Management.Controller;


import com.MuratApps.todo_Management.Dto.LoginDto;
import com.MuratApps.todo_Management.Dto.RegisterDto;
import com.MuratApps.todo_Management.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auths")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;


    @PostMapping("/register")
    ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto){

        authService.register(registerDto);

        return new ResponseEntity<>(registerDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto){

        authService.login(loginDto);


        return new ResponseEntity<>(loginDto,HttpStatus.CREATED);
    }






}
