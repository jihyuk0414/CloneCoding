package com.example.weatherapi.domain.Controller;

import com.example.weatherapi.domain.Dto.ResponseVo;
import com.example.weatherapi.domain.Dto.WeatherRequestVo;
import com.example.weatherapi.domain.Dto.WeatherResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InitController {


    @GetMapping("/hello")
    public ResponseEntity<ResponseVo> initapi(@RequestParam(value="input") String input)
    {
        ResponseVo responseVo = new ResponseVo() ;
        responseVo.setCode("00");
        responseVo.setMessage("잘왔습니다"+ input);
        return ResponseEntity.ok(responseVo) ;
    }

}
