package com.example.weatherapi.domain.Controller;

import com.example.weatherapi.domain.Dto.ResponseVo;
import com.example.weatherapi.domain.Dto.WeatherRequestVo;
import com.example.weatherapi.domain.Dto.WeatherResponseVo;
import com.example.weatherapi.domain.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService ;

    @GetMapping("/openapitest")
    public ResponseEntity<ResponseVo> OpenapiTest(@RequestParam(value = "date") String date) throws Exception {

        WeatherRequestVo weatherRequestVo = new WeatherRequestVo() ;
        weatherRequestVo.setApiKey("test") ;
        weatherRequestVo.setType("json");
        weatherRequestVo.setSdate(date);

        LocalTime currenttime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH") ;
        weatherRequestVo.setStdHour(currenttime.format(formatter));

        LocalDateTime currentdatetime = LocalDateTime.now() ;
        DateTimeFormatter formatterDatetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        weatherRequestVo.setOrdertime(currentdatetime.format(formatterDatetime));

        WeatherResponseVo weatherResponseVo = weatherService.getweatherinfolist(weatherRequestVo) ;
        log.info("response" + weatherResponseVo.toString()) ;

        ResponseVo responseVo = new ResponseVo() ;

        if (weatherResponseVo.getList().isEmpty()) {
            throw new Exception();
        } else {
            responseVo.setCode("ok");
            responseVo.setMessage(weatherResponseVo.getList().toString());
        }

        return ResponseEntity.ok(responseVo) ;



    }
}
