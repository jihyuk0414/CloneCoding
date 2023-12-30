package com.example.DevleoperMake.repository;

import com.example.DevleoperMake.Entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetiredDeveloperrepository
        extends JpaRepository<RetiredDeveloper, Long> {
}
