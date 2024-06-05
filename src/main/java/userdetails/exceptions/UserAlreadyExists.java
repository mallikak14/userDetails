package userdetails.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(){
        super();
    }
    public UserAlreadyExists(String msg){
        super(msg);
    }

}
