package wonders.authservice.exception;

public class AccountAlreadyExistException extends Exception{
    public AccountAlreadyExistException(String msg){
        super(msg);
    }
}
