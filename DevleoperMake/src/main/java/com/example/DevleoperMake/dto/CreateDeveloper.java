package com.example.DevleoperMake.dto;

import com.example.DevleoperMake.Entity.Developer;
import com.example.DevleoperMake.Entity.DeveloperSkill;
import com.example.DevleoperMake.Entity.Developerlevel;
import com.example.DevleoperMake.Entity.StatusCode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class CreateDeveloper {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    //요청.
    public static class Request {
        @NotNull
        private Developerlevel developerlevel;

        @NotNull
        private DeveloperSkill developerskill ;

        @NotNull
        private StatusCode statusCode;

        @NotNull
        @Min(0)
        @Max(20)
        private int experientYears;

        @NotNull
        @Size(min =3, max= 50 ,message = "50is max min is 3 ")
        private String memberId; //특정 회사 id

        @NotNull
        @Size(min =3, max= 20 ,message = "20is max min is 3 ")
        private String name;

        @Min(18)
        private int age;


    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    //요청.
    public static class Response {
        private Developerlevel developerlevel;

        private DeveloperSkill developerskill ;

        private int experientYears;

        private String memberId; //특정 회사 id

        public static Response fromEntity(Developer developer)
        {
            return Response.builder()
                    .developerlevel(developer.getDeveloperlevel())
                    .developerskill(developer.getDeveloperskill())
                    .experientYears(developer.getExperientYears())
                    .memberId(developer.getMemberId())
                    .build() ;
        }



    }
}
