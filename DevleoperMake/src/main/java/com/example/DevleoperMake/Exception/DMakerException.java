package com.example.DevleoperMake.Exception;

import lombok.Getter;

@Getter
public class DMakerException extends RuntimeException{
    //내가만든 customexception!
    private DMakerErrorCode dMakerErrorCode;
    private String Detailmessage ;

    public DMakerException(DMakerErrorCode errorCode)
    {
        super(errorCode.getMessage()) ;
        this.dMakerErrorCode = errorCode ;
        this.Detailmessage = errorCode.getMessage();
    }

    public DMakerException(DMakerErrorCode errorCode, String detailMessage)
    {
        this.dMakerErrorCode = errorCode ;
        this.Detailmessage = detailMessage;
    }


}
