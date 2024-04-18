package userdetails.exceptions;

public class GmailNotProvided extends RuntimeException{
    public GmailNotProvided(){
        super();
    }
    public GmailNotProvided(String msg){
        super(msg);
    }  
}
