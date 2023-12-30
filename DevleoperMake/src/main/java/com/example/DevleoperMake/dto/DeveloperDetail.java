package com.example.DevleoperMake.dto;

import com.example.DevleoperMake.Entity.Developer;
import com.example.DevleoperMake.Entity.DeveloperSkill;
import com.example.DevleoperMake.Entity.Developerlevel;
import com.example.DevleoperMake.Entity.StatusCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDetail {


    private Developerlevel developerlevel ;

    private DeveloperSkill developerskill ;

    private StatusCode statuscode;

    private int experientYears;
    private String memberId; //특정 회사 id

    private String name;
    private int age;


    public static DeveloperDetail fromEntity(Developer developer)
    {
        return DeveloperDetail.builder()
                .developerlevel(developer.getDeveloperlevel())
                .developerskill(developer.getDeveloperskill())
                .experientYears(developer.getExperientYears())
                .statuscode(developer.getStatus())
                .memberId(developer.getMemberId())
                .age(developer.getAge())
                .name(developer.getName())
                .build() ;
    }


}
