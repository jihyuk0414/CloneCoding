package com.example.springsecuritypractice.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    private String username;
    private String password;

}
