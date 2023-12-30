package com.example.DevleoperMake.Service;

import com.example.DevleoperMake.Entity.Developer;
import com.example.DevleoperMake.Entity.DeveloperSkill;
import com.example.DevleoperMake.Entity.Developerlevel;
import com.example.DevleoperMake.Entity.StatusCode;
import com.example.DevleoperMake.Exception.DMakerErrorCode;
import com.example.DevleoperMake.Exception.DMakerException;
import com.example.DevleoperMake.dto.CreateDeveloper;
import com.example.DevleoperMake.dto.DeveloperDetail;
import com.example.DevleoperMake.dto.DeveloperDto;
import com.example.DevleoperMake.repository.Developerrepository;
import com.example.DevleoperMake.repository.RetiredDeveloperrepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DMakeServiceTest {
    @Mock
    private Developerrepository developerrepository ;

    @Mock
    private RetiredDeveloperrepository retiredDeveloperrepository;

    @InjectMocks
    private DMakeService dMakeService;

    private final Developer defaultDeveloper = Developer.builder()
            .developerskill(DeveloperSkill.Full)
            .developerlevel(Developerlevel.Junior)
            .memberId("defaultdeveloper")
            .age(32)
            .name("Defaultdeveloper")
            .status(StatusCode.EMPLOYEED)
            .build();


    @Test
    public void testSomething()
    {
        given(developerrepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                        .developerlevel(Developerlevel.Senior)
                        .developerskill(DeveloperSkill.Full)
                        .experientYears(13)
                        .name("달인입니다")
                        .age(32)
                        .build())) ;

        DeveloperDetail developerDetail = dMakeService.getDeveloperDetail("memberId") ;


        assertEquals(Developerlevel.Senior, developerDetail.getDeveloperlevel());


    }

    @Test
    void createDeveloperTest_success ()
    {
        //given
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerlevel(Developerlevel.Senior)
                .developerskill(DeveloperSkill.Stupid)
                .experientYears(12)
                .memberId("forthecreatetest")
                .age(32)
                .name("testman")
                .statusCode(StatusCode.EMPLOYEED)
                .build();

        given(developerrepository.findByMemberId(anyString()))
                .willReturn(Optional.empty()) ;
        ArgumentCaptor<Developer> captor =
                ArgumentCaptor.forClass(Developer.class) ;

        //when

        CreateDeveloper.Response response = dMakeService.createDeveloper(request);
        //현재 findbymemberid불가능. 이를 mocking 필요

        //then (무엇을 검증할것)
        verify(developerrepository,times(1))
                .save(captor.capture()) ;
        Developer savedDeveloper = captor.getValue() ;
        assertEquals("forthecreatetest", savedDeveloper.getMemberId() );

    }


    @Test
    void CreateDuplicate()
    {
        //given
        given(developerrepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultDeveloper)) ;

        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerlevel(Developerlevel.Senior)
                .developerskill(DeveloperSkill.Stupid)
                .experientYears(12)
                .memberId("forthecreatetest")
                .age(32)
                .name("testman")
                .statusCode(StatusCode.EMPLOYEED)
                .build();

        //when
        DMakerException dMakerException =
                assertThrows(DMakerException.class,
                        () -> dMakeService.createDeveloper(request) ) ;

        assertEquals(DMakerErrorCode.DUPLICATED_MEMBER_ID, dMakerException.getDMakerErrorCode());

    }


}