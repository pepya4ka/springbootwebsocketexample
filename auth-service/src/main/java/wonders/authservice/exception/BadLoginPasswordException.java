package wonders.authservice.exception;

public class BadLoginPasswordException extends Exception {
    public BadLoginPasswordException(String msg){
        super(msg);
    }
}
