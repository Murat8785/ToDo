package com.MuratApps.todo_Management.Service;


import com.MuratApps.todo_Management.Dto.LoginDto;
import com.MuratApps.todo_Management.Dto.RegisterDto;
import com.MuratApps.todo_Management.Entity.Role;
import com.MuratApps.todo_Management.Entity.User;
import com.MuratApps.todo_Management.Exception.ToDoApiException;
import com.MuratApps.todo_Management.Repository.RoleRepository;
import com.MuratApps.todo_Management.Repository.ToDoRepository;
import com.MuratApps.todo_Management.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthServiceImp implements AuthService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;



    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByName(registerDto.getName())){
            throw  new ToDoApiException(HttpStatus.BAD_REQUEST,"böyle bir kullanıcı zaten var");
        }
     if (userRepository.existsByEmail(registerDto.getEmail())){
         throw new ToDoApiException(HttpStatus.BAD_REQUEST,"bu email zaten kullanılıyor");
     }
        User user=new User();
     user.setName(registerDto.getName());
     user.setEmail(registerDto.getEmail());
     user.setLastName(registerDto.getLastName());
    user.setPassword(registerDto.getPassword());

        Set<Role>  roles=new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "Kullanıcı başarıyla Oluşturuldu";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getNameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Başarıyla Giriş Yaptınız";
    }
}
































