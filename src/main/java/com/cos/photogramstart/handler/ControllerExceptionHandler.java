package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomvalidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 컨트롤러에서 발생하는 exception을 다 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomvalidationException.class)
    public String vaildationException(CustomvalidationException e){
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script가 좋음
        // 2. Ajax통신 - CMRespDto
        // 3. Android 통신 - CMRespDto

        return Script.back(e.getErrorMap().toString());
    }
}
