package com.rms.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.rms.controller.RateController;
import com.rms.model.ErrorMessage;
import com.rms.utils.CommonValues;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(NotFoundException ex, WebRequest request) {
	  logger.error("NotFoundException : {}", ex);
	  int status = Response.Status.NOT_FOUND.getStatusCode();
    ErrorMessage message = new ErrorMessage(status,ex.getCode(),ex.getMessages());
    return message;
  }
  
  @ExceptionHandler(Exception.class)
  public ErrorMessage handle(Exception ex, 
              HttpServletRequest request, HttpServletResponse response) {
	  logger.error("Exception : {}", ex);
//	  CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
	  int status =  Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	  List<String> erroMessages = new ArrayList<>(); 
	  erroMessages.add(CommonValues.INTERNAL_SERVER_ERROR);
	  ErrorMessage message = new ErrorMessage(status,status,erroMessages);
      return message;
  }
  
  @ExceptionHandler(DataValidationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage resourceDataValidationExceptio(DataValidationException ex, WebRequest request) {
	  logger.error("DataValidationException : {}", ex);
	  int status = Response.Status.BAD_REQUEST.getStatusCode();
    ErrorMessage message = new ErrorMessage(status,ex.getCode(),ex.getMessages());
    return message;
  }
}
