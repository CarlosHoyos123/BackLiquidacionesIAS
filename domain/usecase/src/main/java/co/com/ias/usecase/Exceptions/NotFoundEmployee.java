package co.com.ias.usecase.Exceptions;

public class NotFoundEmployee extends Exception{
    public NotFoundEmployee(String message) {
        super(message);
    }
}
