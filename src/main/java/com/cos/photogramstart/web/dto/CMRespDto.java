package com.cos.photogramstart.web.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CMRespDto<T>  { // 공통 DTO

    private int code; // 1(성공), 2(실패)
    private String message;
    private T data;


//    private String message;
//    private Map<String, String> errorMap;

}
