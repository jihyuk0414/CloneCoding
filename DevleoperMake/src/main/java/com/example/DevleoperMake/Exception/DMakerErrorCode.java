package com.example.DevleoperMake.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.client.HttpServerErrorException;

@Getter
@AllArgsConstructor
public enum DMakerErrorCode {

    NO_DEVELOPER ("그런개발자없음"),
    DUPLICATED_MEMBER_ID("개발자ID가중복"),
    LEVEL_EXPERIENCE_YEAR_NOT_MATCHED("개발자 레벨이랑 연차가 안맞아요"),

    INTERNAL_SERVER_ERROR("진짜 어쩔수없는 서버에러에요"),
    INVALID_REQUEST("잘못된 기타 요청") ;



    private final String message;
}
