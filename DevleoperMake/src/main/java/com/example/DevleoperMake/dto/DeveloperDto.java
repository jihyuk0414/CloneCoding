package com.example.DevleoperMake.dto;

import com.example.DevleoperMake.Entity.Developer;
import com.example.DevleoperMake.Entity.DeveloperSkill;
import com.example.DevleoperMake.Entity.Developerlevel;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDto {
    private Developerlevel developerlevel;
    private DeveloperSkill developerSkill ;
    private String memberid ;

    public static DeveloperDto fromEntity(Developer developer)
    {
        return DeveloperDto.builder()
                .developerlevel(developer.getDeveloperlevel())
                .developerSkill(developer.getDeveloperskill())
                .memberid(developer.getMemberId())
                .build() ;
    }
}
