package com.MuratApps.todo_Management.Service;


import com.MuratApps.todo_Management.Dto.LoginDto;
import com.MuratApps.todo_Management.Dto.RegisterDto;


public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
