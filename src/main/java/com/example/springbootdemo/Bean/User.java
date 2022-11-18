package com.example.springbootdemo.Bean;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String name;
    private String password;
}
