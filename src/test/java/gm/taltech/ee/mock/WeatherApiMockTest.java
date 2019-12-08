package gm.taltech.ee.mock;

import gm.taltech.ee.weatherwise.WeatherWise;
import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
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

    @Mock
    WeatherApi weatherApiMock;

    private WeatherWise weatherWise;

    @Before
    public void setUp() {
        weatherWise = new WeatherWise(weatherApiMock);
    }

    @Test
    public void should_not_call_api_when_city_is_empty() throws CurrentWeatherDataMissingException {
        try {
            weatherWise.getWeatherReportForCityInUnits(null, null);
        } catch (CityIsEmptyException | IOException e) {
            // ignored
        }

        verifyZeroInteractions(weatherApiMock);
    }

    @Test
    public void should_call_api_when_city_name_is_provided() throws CurrentWeatherDataMissingException {
        String city = "Tallinn";
        String units = String.valueOf((Object) null);

        when(weatherApiMock.getCurrentWeatherDataForCity(anyString(), anyString()))
                .thenReturn(mock(CurrentWeatherResponse.class));

        try {
            weatherWise.getWeatherReportForCityInUnits(city, units);
        } catch (CityIsEmptyException | IOException e) {
            // ignored
        }
        verify(weatherApiMock).getCurrentWeatherDataForCity(city, units);
    }
}
