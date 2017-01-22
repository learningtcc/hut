package com.hut.web.controller;

import com.hut.common.messages.Msg;
import com.hut.common.messages.MsgException;
import com.hut.common.messages.UnallowedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Jared on 2016/12/26.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UnallowedException.class)
    public ResponseEntity<Msg> unath() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(Msg.build(Msg.UNAUTH,"you can not access this resource"),headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MsgException.class)
    public ResponseEntity<Msg> msgException(MsgException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(Msg.build(e.getCode(),e.getMessage()),headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Msg> exception(Exception e) {

        e.printStackTrace();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(Msg.build(Msg.UNKNOW,"you can not access this resource"),headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
