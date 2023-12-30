package com.example.DevleoperMake.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum DeveloperSkill {

    Front("프론트") ,
    Back("백") ,
    Full ("풀스택"),
    Stupid ("바보 ") ;

    private final String description ;

}
