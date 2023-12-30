package com.example.DevleoperMake.repository;

import com.example.DevleoperMake.Entity.Developer;
import com.example.DevleoperMake.Entity.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface Developerrepository
        extends JpaRepository<Developer,Long> {
    Optional<Developer> findByMemberId(String memberId) ;

    List<Developer> findDevelopersByStatusEquals(StatusCode status) ;
}
