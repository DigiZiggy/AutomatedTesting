package gm.taltech.ee.weatherwise.exception;

public class CityNotFoundException extends Exception{

    public CityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
