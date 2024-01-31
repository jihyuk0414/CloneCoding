package com.example.weatherapi.domain.Controller;

import com.example.weatherapi.domain.Dto.WeatherRequestVo;
import com.example.weatherapi.domain.Repository.WeatherRequestRepository;
import com.example.weatherapi.domain.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final WeatherService weatherService ;

    @GetMapping("/order")
    public ResponseEntity<List<WeatherRequestVo>> GetOrderByDate(
            @RequestParam(value = "date") String date
    )
    {
        return ResponseEntity.ok(weatherService.findAllBySdate(date)) ;

    }

}
