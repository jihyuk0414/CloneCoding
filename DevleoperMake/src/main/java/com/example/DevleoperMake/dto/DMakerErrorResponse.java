package com.example.DevleoperMake.dto;


import com.example.DevleoperMake.Exception.DMakerErrorCode;
import com.example.DevleoperMake.Exception.DMakerException;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DMakerErrorResponse {
    private DMakerErrorCode errorCode ;
    private String errorMessage ;
}
