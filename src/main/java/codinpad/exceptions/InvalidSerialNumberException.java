package codinpad.exceptions;

public class InvalidSerialNumberException extends Exception{

    public InvalidSerialNumberException(){
        super("Invalid serial number");
    }

    public InvalidSerialNumberException(String message){
        super(message);
    }

}
