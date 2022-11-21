package com.amjed.texteditor.services.users.implementation;

public class UserAlreadyExistException extends Exception{
    private static final long serialVersionUID = 1997753363232807009L;

    public UserAlreadyExistException()
    {

    }

    public UserAlreadyExistException(String message)
    {
        super(message);
    }

    public UserAlreadyExistException(Throwable cause)
    {
        super(cause);
    }

    public UserAlreadyExistException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserAlreadyExistException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
