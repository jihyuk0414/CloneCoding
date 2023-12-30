package com.example.DevleoperMake.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    RETIRED("은 퇴"),
    EMPLOYEED("일 중") ;

    private final String description ;
}
