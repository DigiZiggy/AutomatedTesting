package gm.taltech.ee.mock;

import com.sun.jersey.api.client.ClientResponse;
import gm.taltech.ee.weatherwise.WeatherWise;
import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
import gm.taltech.ee.weatherwise.payload.dto.CoordinatesDto;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

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
            weatherWise.getWeatherReportForCityInCertainUnits(null, null);
        } catch (CityIsEmptyException | IOException e) {
            // ignored
        }

        verifyZeroInteractions(weatherApiMock);
    }

    // Example of mocking 2: verifying exactly one interaction took place
    @Test
    public void should_call_api_when_city_name_is_provided() throws CurrentWeatherDataMissingException {
        String city = "Tallinn";
        String units = String.valueOf((Object) null);

        when(weatherApiMock.getCurrentWeatherDataForCity(anyString(), anyString()))
                .thenReturn(mock(CurrentWeatherResponse.class));

        try {
            weatherWise.getWeatherReportForCityInCertainUnits(city, units);
        } catch (CityIsEmptyException | IOException e) {
            // ignored
        }
        verify(weatherApiMock).getCurrentWeatherDataForCity(city, units);
    }
}
