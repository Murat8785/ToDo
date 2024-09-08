package com.MuratApps.todo_Management.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public class ToDoApiException extends RuntimeException {

    private HttpStatus status;
    private String message;





}



