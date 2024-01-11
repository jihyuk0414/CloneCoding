package com.example.springsecuritypractice.notice;

public class NoticeNotFoundException extends RuntimeException{
    public NoticeNotFoundException(String message) {
        super(message);
    }

    public NoticeNotFoundException() {
        super("해당 공지사항은 없습니다") ;
    }
}
