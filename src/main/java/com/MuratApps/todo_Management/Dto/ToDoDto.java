package com.MuratApps.todo_Management.Dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ToDoDto {
   private Long id;
   private String title;
   private  String description;
   private  boolean completed;

}
