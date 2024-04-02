package userdetails.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ErrorResponse {
    String msg;
    String errorCode;
    Date date;
    HttpStatus status;
}
