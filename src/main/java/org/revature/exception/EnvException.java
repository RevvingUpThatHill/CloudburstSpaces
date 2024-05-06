package org.revature.exception;

/**
 * Exception for missing environment variables.
 */
public class EnvException extends Exception{
    public EnvException(String message){
        super(message);
    }
    public EnvException(String message, Throwable cause){
        super(message, cause);
    }
}
