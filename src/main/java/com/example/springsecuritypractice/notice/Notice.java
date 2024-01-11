package com.example.springsecuritypractice.notice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String title ;

    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime createdAt ;

    @LastModifiedDate
    private LocalDateTime updatedAt ;

    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
