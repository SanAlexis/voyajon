/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller.exceptions;


import com.nyx.voyajon.exception.BusinessException;
import com.nyx.voyajon.utils.StringUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author tchipi
 */
@ControllerAdvice(basePackages = "com.nyx")
public class CustomHandlerExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class, BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(Exception ex, WebRequest request) {
        Logger.getLogger(CustomHandlerExceptionResolver.class.getName()).log(Level.SEVERE, null, ex);
        // prepare responseEntity
        if (ex instanceof BusinessException) {
            return new ResponseEntity<>((ex).getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(ex.getMessage()!=null?ex.getMessage():StringUtils.getStackTrace(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
