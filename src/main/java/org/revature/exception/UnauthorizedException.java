package org.revature.exception;

/**
 * Exception for auth failures.
 */
public class UnauthorizedException extends Exception{
    public UnauthorizedException(String message){
        super(message);
    }
    public UnauthorizedException(String message, Throwable cause){
        super(message, cause);
    }
}
