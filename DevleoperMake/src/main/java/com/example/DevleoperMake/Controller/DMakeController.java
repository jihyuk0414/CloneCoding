package com.example.DevleoperMake.Controller;


import com.example.DevleoperMake.Exception.DMakerException;
import com.example.DevleoperMake.Service.DMakeService;
import com.example.DevleoperMake.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DMakeController {
    private final DMakeService dmakeservice;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers ()
    {
        log.info("GET ALL") ;
        return dmakeservice.getAllEmployeedDevelopers() ;
        //있는 것 처럼.
    }

    @GetMapping("/developers/{memberId}")
    public DeveloperDetail getDeveloperDetail (
            @PathVariable final String memberId
    )
    {
        log.info("GET One Detail") ;
        return dmakeservice.getDeveloperDetail(memberId) ;
        //있는 것 처럼.
    }

    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request

    ) {
        log.info("reqeust = {}", request);

        return dmakeservice.createDeveloper(request) ;

    }

    @PutMapping("/developers/{memberId}")
    public DeveloperDetail editDeveloper(
            @PathVariable final String memberId,
            @Valid @RequestBody EditDeveloper.Request request
            )
    {
        log.info("GET One Detail") ;
            return dmakeservice.editDeveloper(memberId, request) ;
        //있는 것 처럼.

    }

    @DeleteMapping("/developers/{memberId}")
    public DeveloperDetail deleteDeveloper(
            @PathVariable String memberId
    )
    {
        return dmakeservice.deleteDeveloper(memberId) ;
    }


}
