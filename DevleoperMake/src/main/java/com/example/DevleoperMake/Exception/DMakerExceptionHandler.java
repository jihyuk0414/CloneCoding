package com.example.DevleoperMake.Exception;

import com.example.DevleoperMake.dto.DMakerErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DMakerExceptionHandler {

    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse handleException(
            DMakerException e,
            HttpServletRequest request)
    {
        log.error("errorCode : {}, url: {}, message :{}",
                e.getDMakerErrorCode(),
                request.getRequestURI(),
                e.getDetailmessage()) ;

        return DMakerErrorResponse.builder()
                .errorCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailmessage())
                .build();

    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            //httprequest method 지원 불가 exception
            MethodArgumentNotValidException.class
            //valid시 발생하는 exception
    })
    public DMakerErrorResponse handleBadRequest(
            DMakerException e , HttpServletRequest request
    )
    {

        log.error("url: {}, message :{}",
                request.getRequestURI(),
                e.getMessage()) ;

        return DMakerErrorResponse.builder()
                .errorCode(DMakerErrorCode.INVALID_REQUEST)
                .errorMessage(DMakerErrorCode.INVALID_REQUEST.getMessage())
                .build();


    }

    @ExceptionHandler(Exception.class)
    public DMakerErrorResponse handleException(
            Exception e , HttpServletRequest request
    )
    {

        log.error("url: {}, message :{}",
                request.getRequestURI(),
                e.getMessage()) ;

        return DMakerErrorResponse.builder()
                .errorCode(DMakerErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(DMakerErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();


    }

}
