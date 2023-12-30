package com.example.DevleoperMake.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RetiredDeveloper {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    protected Long id;


    @Enumerated(EnumType.STRING)
    private Developerlevel developerlevel ;

    @Enumerated(EnumType.STRING)
    private DeveloperSkill developerskill ;

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode ;

    private int experientYears;
    private String memberId; //특정 회사 id

    private String name;
    private int age;

    @CreatedDate
    private LocalDateTime createdAt ;

    @LastModifiedDate
    private LocalDateTime updateAt;


}


