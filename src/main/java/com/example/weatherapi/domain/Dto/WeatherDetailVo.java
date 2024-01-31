package com.example.weatherapi.domain.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDetailVo {

    String unitCode ;
    String unitName ;
    String routeName;

    String xValue ;
    String yValue;
    String cloudValue ;
    String snowValue ;
}
