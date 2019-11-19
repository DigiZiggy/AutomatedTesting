package gm.taltech.ee.examples.mock;

import gm.taltech.ee.weatherwise.WeatherWise;
import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.api.response.CurrentWeatherData;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherApiMockTest {

    /**
     * Compared to stub test, now we are focusing on HOW WeatherApi is called and not the actual data it provides.
     * In other words now we are interested in testing the interactions (or integration) between two classes.
     */

    @Mock
    WeatherApi weatherApiMock;

    private WeatherWise weatherWise;

    @Before
    public void setUp() {
        weatherWise = new WeatherWise(weatherApiMock);
    }

    // Example of mocking 1: verifying that no interaction took place
    @Test
    public void should_not_call_api_when_city_is_empty() throws CurrentWeatherDataMissingException {
        try {
            weatherWise.getWeatherReportForCity(null);
        } catch (CityIsEmptyException e) {
            // ignored
        }

        verifyZeroInteractions(weatherApiMock);
    }

    // Example of mocking 2: verifying exactly one interaction took place
    @Test
    public void should_call_api_when_city_name_is_provided() throws CurrentWeatherDataMissingException {
        String city = "Tallinn";
        when(weatherApiMock.getCurrentWeatherDataForCity(anyString()))
                .thenReturn(mock(CurrentWeatherData.class));

        try {
            weatherWise.getWeatherReportForCity(city);
        } catch (CityIsEmptyException e) {
            // ignored
        }

        verify(weatherApiMock).getCurrentWeatherDataForCity(city);
    }
}
