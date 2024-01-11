package com.example.springsecuritypractice.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("해당 유저를 찾을수 없습니다") ;
    }
}
