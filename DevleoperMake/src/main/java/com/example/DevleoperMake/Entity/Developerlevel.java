package com.example.DevleoperMake.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum Developerlevel {
    NEW("이제시작"),
    Junior("주니어 개발자"),
    Senior("중급 개발자"),
    Pro("프로개발자") ;



    private final String description;


}
