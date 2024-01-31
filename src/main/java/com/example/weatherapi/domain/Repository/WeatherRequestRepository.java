package com.example.weatherapi.domain.Repository;

import com.example.weatherapi.domain.Dto.WeatherRequestVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRequestRepository extends JpaRepository<WeatherRequestVo, Long> {

    List<WeatherRequestVo> findAllBySdate (String date) ;
}
