package userdetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException usernotfoundexception){
        ErrorResponse errorResponse = new ErrorResponse("User ID not found",
         "user not found", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(value=HttpStatus.CONFLICT)
    public ResponseEntity<?> handleUserAlreadyPresentException(UserAlreadyExists useralreadypresentexception){
        ErrorResponse errorResponse=new ErrorResponse("user with name is already present ",
        "user already present",HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(GmailNotProvided.class)
    @ResponseStatus(value=HttpStatus.CONFLICT)
    public ResponseEntity<?> handleGmailNotProvidedException(GmailNotProvided gmailnotprovidedException){
        ErrorResponse errorResponse=new ErrorResponse("gmail not provided",
        "associated gmail not provided",HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}