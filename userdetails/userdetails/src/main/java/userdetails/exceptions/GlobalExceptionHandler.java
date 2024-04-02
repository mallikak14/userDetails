package userdetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex){
        ErrorResponse er=new ErrorResponse();
        er.setDate(new java.util.Date());
        er.setErrorCode("userId Not Found");
        er.setMsg(ex.getMessage());
        er.setStatus(HttpStatus.NOT_FOUND);
        return er;
    }
}