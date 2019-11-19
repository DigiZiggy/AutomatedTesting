package gm.taltech.ee.weatherwise.exception;

public class CurrentWeatherDataMissingException extends Exception {
    public CurrentWeatherDataMissingException(String errorMessage) {
        super(errorMessage);
    }
}
