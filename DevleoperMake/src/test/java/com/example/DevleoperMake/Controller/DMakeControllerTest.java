package com.example.DevleoperMake.Controller;

import com.example.DevleoperMake.Entity.DeveloperSkill;
import com.example.DevleoperMake.Entity.Developerlevel;
import com.example.DevleoperMake.Service.DMakeService;
import com.example.DevleoperMake.dto.DeveloperDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DMakeController.class)
class DMakeControllerTest {

    @Autowired
    private MockMvc mockmvc ;

    @MockBean
    private DMakeService dMakeService ;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);
    @Test
    void getAlldevelopers() throws Exception{
        DeveloperDto JUNIORDto = DeveloperDto.builder()
                .developerSkill(DeveloperSkill.Front)
                .developerlevel(Developerlevel.Junior)
                .memberid("memberid2").build() ;
        DeveloperDto SENIORDto = DeveloperDto.builder()
                .developerSkill(DeveloperSkill.Front)
                .developerlevel(Developerlevel.Senior)
                .memberid("memberid3").build() ;

        given(dMakeService.getAllEmployeedDevelopers())
                .willReturn(Arrays.asList(JUNIORDto,SENIORDto)) ;

        mockmvc.perform(get("/developers").contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.[0].developerSkill",
                                is(DeveloperSkill.Front.name()))
                ) ;


    }

}