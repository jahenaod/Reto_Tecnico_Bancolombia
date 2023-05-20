package co.com.bancolombia.consumer.exception;

import java.io.IOException;

public class CountryNotFoundException extends IOException{
    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
