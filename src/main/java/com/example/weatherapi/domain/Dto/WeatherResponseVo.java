package com.example.weatherapi.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseVo {

    int count ;
    ArrayList<WeatherDetailVo> list;
}
