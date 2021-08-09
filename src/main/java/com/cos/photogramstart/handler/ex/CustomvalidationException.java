package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomvalidationException extends RuntimeException {

    //객체를 구분 할떄
    private static final long serialVersionUID = 1L;


    private Map<String, String> errorMap;

    public CustomvalidationException(String message, Map<String, String> errorMap){
        super(message); // 부모한테 던져줌 , ??? 공부해야함
        this.errorMap = errorMap;
    }


    public Map<String, String> getErrorMap(){
        return errorMap;
    }
}
