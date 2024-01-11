package com.example.springsecuritypractice.user;

public class AlreadyRegisteredUserException extends RuntimeException {

    public AlreadyRegisteredUserException(String message) {
        super(message);
    } //왜그런지 메세지 리턴

    public AlreadyRegisteredUserException() {
        super("이미등록된유저입니다") ;
        //사실 이것 만을 잡기 위한 exception이라봐도 무방.
    }
}
