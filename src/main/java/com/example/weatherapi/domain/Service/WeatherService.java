package com.example.weatherapi.domain.Service;

import com.example.weatherapi.domain.Dto.WeatherRequestVo;
import com.example.weatherapi.domain.Dto.WeatherResponseVo;
import com.example.weatherapi.domain.Repository.WeatherRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRequestRepository weatherRequestRepository;

    @Transactional
    public WeatherResponseVo getweatherinfolist(WeatherRequestVo weatherRequestVo)
    {
        weatherRequestRepository.save(weatherRequestVo) ;

        WebClient webClient = WebClient.builder()
                .baseUrl("https://data.ex.co.kr/openapi/restinfo/")
                .build();

        String apiUrl = "/restWeatherList?key={apikey}&type={type}&sdate={sdate}&stdHour={stdHour}" ;

        WeatherResponseVo weatherResponse = webClient.get()
                .uri(apiUrl,weatherRequestVo.getApiKey(),weatherRequestVo.getType()
                        ,weatherRequestVo.getSdate()
                        ,weatherRequestVo.getStdHour())
                .retrieve()
                .bodyToMono(WeatherResponseVo.class)
                .block() ;

        return weatherResponse ;

    }


    @Transactional
    public List<WeatherRequestVo> findAllBySdate (String order)
    {
        List<WeatherRequestVo> weatherRequestVoList = weatherRequestRepository.findAllBySdate(order) ;
        return weatherRequestVoList ;
    }




}
