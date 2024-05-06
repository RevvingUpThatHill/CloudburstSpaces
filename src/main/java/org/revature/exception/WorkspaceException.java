package org.revature.exception;

/**
 * Exception related to a failure on workspace operations
 */
public class WorkspaceException extends Exception{
    public WorkspaceException(String msg){
        super(msg);
    }
    public WorkspaceException(String msg, Throwable cause){
        super(msg, cause);
    }
}
