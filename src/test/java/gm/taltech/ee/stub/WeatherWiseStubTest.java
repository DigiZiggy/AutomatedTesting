package gm.taltech.ee.stub;

import gm.taltech.ee.weatherwise.WeatherReport;
import gm.taltech.ee.weatherwise.WeatherWise;
import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class WeatherWiseStubTest {

    @Mock
    WeatherApi weatherApiMock;

    private WeatherWise weatherWise;

    @Before
    public void setUp() {
        weatherWise = new WeatherWise(weatherApiMock);
    }

    @Test
    public void should_return_weather_report_for_given_city() throws CityIsEmptyException, CurrentWeatherDataMissingException, IOException {
        String city = "Tallinn";

        CurrentWeatherResponse currentWeatherData = new CurrentWeatherResponse();

        currentWeatherData.setName(city);

        // When we ask the api for current weather data using some string then always return the same data
        // In other words we forget about the logic of WeatherApi and focus on WeatherWise and what it should do
        // with the data the WeatherApi provides.
        Mockito.when(weatherApiMock.getCurrentWeatherDataForCity(anyString(), anyString())).thenReturn(currentWeatherData);

        WeatherReport weatherReport = weatherWise.getWeatherReportForCityInCertainUnits(city, String.valueOf((Object) null));

        System.out.println(weatherReport.toString());
        assertEquals(weatherReport.getWeatherReportDetails().getCity(), city);
    }

    @Test
    public void should_return_fahrenheit_when_fahrenheit_asked() throws CityIsEmptyException, CurrentWeatherDataMissingException, IOException {
        String city = "Stockholm";
        String units = "imperial";

        CurrentWeatherResponse currentWeatherData = new CurrentWeatherResponse();

        currentWeatherData.setName(city);

        Mockito.when(weatherApiMock.getCurrentWeatherDataForCity(anyString(), anyString())).thenReturn(currentWeatherData);

        WeatherReport weatherReport = weatherWise.getWeatherReportForCityInCertainUnits(city, units);

        assertEquals(weatherReport.getWeatherReportDetails().getTemperatureUnit(), "Fahrenheit");
    }

    @Test
    public void should_return_celsius_when_celsius_asked() throws CityIsEmptyException, CurrentWeatherDataMissingException, IOException {
        String city = "Tallinn";
        String units = "metric";

        CurrentWeatherResponse currentWeatherData = new CurrentWeatherResponse();

        currentWeatherData.setName(city);

        Mockito.when(weatherApiMock.getCurrentWeatherDataForCity(anyString(), anyString())).thenReturn(currentWeatherData);

        WeatherReport weatherReport = weatherWise.getWeatherReportForCityInCertainUnits(city, units);

        assertEquals(weatherReport.getWeatherReportDetails().getTemperatureUnit(), "Celsius");
    }

    @Test
    public void should_return_kelvin_by_default() throws CityIsEmptyException, CurrentWeatherDataMissingException, IOException {
        String city = "Helsinki";
        String units = String.valueOf((Object) null);

        CurrentWeatherResponse currentWeatherData = new CurrentWeatherResponse();

        currentWeatherData.setName(city);

        Mockito.when(weatherApiMock.getCurrentWeatherDataForCity(anyString(), anyString())).thenReturn(currentWeatherData);

        WeatherReport weatherReport = weatherWise.getWeatherReportForCityInCertainUnits(city, units);

        assertEquals(weatherReport.getWeatherReportDetails().getTemperatureUnit(), "Kelvin");
    }
}
