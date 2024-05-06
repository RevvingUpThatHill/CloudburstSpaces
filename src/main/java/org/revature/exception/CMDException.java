package org.revature.exception;

/**
 * Exception for when an issue has occurred during the attempt to run a command on the
 * computer's terminal.
 */
public class CMDException extends Exception{
    public CMDException(String msg){
        super(msg);
    }
    public CMDException(String msg, Throwable cause){
        super(msg, cause);
    }
}
