package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomvalidationApiException;
import com.cos.photogramstart.handler.ex.CustomvalidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice // 컨트롤러에서 발생하는 exception을 다 낚아챔
public class ControllerExceptionHandler {

    //스크립트 반환
    @ExceptionHandler(CustomvalidationException.class)
    public String vaildationException(CustomvalidationException e){
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script가 좋음
        // 2. Ajax통신 - CMRespDto
        // 3. Android 통신 - CMRespDto
        if (e.getErrorMap() == null){
            return Script.back(e.getMessage());

        }else{
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e) {

        return Script.back(e.getMessage());

    }



    @ExceptionHandler(CustomvalidationApiException.class)
    public ResponseEntity<?> vaildationApiException(CustomvalidationApiException e){

        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e){

        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
