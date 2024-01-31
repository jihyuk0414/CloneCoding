package com.example.weatherapi.domain.Dto;

import jakarta.persistence.*;
import lombok.*;

@Table(name="request_order")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRequestVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @Column(name = "api_key")
    String apiKey;

    String type;
    String sdate;

    @Column(name = "std_hour")
    String stdHour;

    String ordertime;
}