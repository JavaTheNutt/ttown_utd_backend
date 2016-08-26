package ie.wit.exceptions;

/**
* This class represents the exception that will be thrown if the password a user provides does not match the expected.
*
* @author Joe Wemyss
*/
public class PasswordMismatchException extends RuntimeException{
    /**
    * Default constructor with a predefined message.
    */
    public PasswordMismatchException(){
        super("The passwords do not match");
    }
}
