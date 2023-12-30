package com.example.DevleoperMake.Service;


import ch.qos.logback.core.spi.ErrorCodes;
import com.example.DevleoperMake.Entity.*;
import com.example.DevleoperMake.Exception.DMakerErrorCode;
import com.example.DevleoperMake.Exception.DMakerException;
import com.example.DevleoperMake.dto.CreateDeveloper;
import com.example.DevleoperMake.dto.DeveloperDetail;
import com.example.DevleoperMake.dto.DeveloperDto;
import com.example.DevleoperMake.dto.EditDeveloper;
import com.example.DevleoperMake.repository.Developerrepository;
import com.example.DevleoperMake.repository.RetiredDeveloperrepository;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.DevleoperMake.Exception.DMakerErrorCode.*;

@Service
@RequiredArgsConstructor
public class DMakeService {
    private final Developerrepository developerrepository;

    private final RetiredDeveloperrepository retireddeveloperrepository ;



    @Transactional
    public DeveloperDetail deleteDeveloper(String memberId){
        Developer developer =  developerrepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER)) ;
        developer.setStatus(StatusCode.RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();

        retireddeveloperrepository.save(retiredDeveloper) ;
        return DeveloperDetail.fromEntity(developer) ;
    }


    @Transactional
    public DeveloperDetail editDeveloper(String memberId, EditDeveloper.Request request){

        Developer developer = developerrepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER) ) ;
        //따로 뺀 이유 . memberid로 developer가 있어야만 한다.x`

        developer.setDeveloperlevel(request.getDeveloperlevel());
        developer.setDeveloperskill(request.getDeveloperskill());
        developer.setExperientYears(request.getExperientYears());


        return DeveloperDetail.fromEntity(developer) ;
    }



    private static void validateDeveloperlevel(Developerlevel developerlevel, int experientYears) {
        if (developerlevel == Developerlevel.Senior && experientYears <10)
        {
            throw new DMakerException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
        }
        if (developerlevel == Developerlevel.Junior &&
                (experientYears <4 || experientYears >10))
        {
            throw new DMakerException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED) ;
        }

        if (developerlevel == Developerlevel.Junior && experientYears >4)
        {
            throw new DMakerException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED) ;
        }
    }

    @Transactional(readOnly = true)
    public List<DeveloperDto> getAllEmployeedDevelopers() {
        return developerrepository.findDevelopersByStatusEquals(StatusCode.EMPLOYEED)
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Transactional
    public CreateDeveloper.Response createDeveloper(@NonNull CreateDeveloper.Request request)
    {
        validateCreateDeveloperRequest(request) ;

        Developer developer = Developer.builder()
                .developerlevel(request.getDeveloperlevel())
                .developerskill(request.getDeveloperskill())
                .experientYears(request.getExperientYears())
                .status(request.getStatusCode())
                .memberId(request.getMemberId())
                .name(request.getName())
                .age(request.getAge())
                .build();

        //이부분이, request에 따라서 변경되어야 겠지.


        developerrepository.save(developer) ;
        return CreateDeveloper.Response.fromEntity(developer) ;

    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        //비지니스 validation
        // 여러가지 예시가 가능
        //시니어가 10년미만인 정책

        validateDeveloperlevel(request.getDeveloperlevel(), request.getExperientYears());


        developerrepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID) ;
                }));






    }

    public DeveloperDetail getDeveloperDetail(String memberId) {

        return developerrepository.findByMemberId(memberId)
                .map(DeveloperDetail::fromEntity)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));


    }
}
