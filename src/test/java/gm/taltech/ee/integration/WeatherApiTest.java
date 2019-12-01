package gm.taltech.ee.integration;

import com.sun.jersey.api.client.ClientResponse;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.helpers.Helper;
import gm.taltech.ee.weatherwise.WeatherWise;
import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.payload.dto.ForecastDto;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;
import gm.taltech.ee.weatherwise.payload.response.WeatherForecastResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class WeatherApiTest {

    private WeatherWise weatherWise;
    private WeatherApi weatherApi;
    private Helper helper;


    @Before
    public void setUp() {
        weatherApi = new WeatherApi();
        weatherWise = new WeatherWise(weatherApi);
        helper = new Helper();
    }

    @Test
    public void should_return_current_weather_for_given_city() {
        String city = "Tallinn";
        String units = String.valueOf((Object) null);

        CurrentWeatherResponse currentWeatherData = weatherApi.getCurrentWeatherDataForCity(city, units);

        long responseUnixTime = currentWeatherData.getDt();
        long currentUnixTime = System.currentTimeMillis() / 1000L;

        long roundedResponseUnixTime = helper.round_unix_timestamp_to_nearest_hour(responseUnixTime);
        long roundedCurrentUnixTime = helper.round_unix_timestamp_to_nearest_hour(currentUnixTime);

        assertEquals(roundedResponseUnixTime, roundedCurrentUnixTime);
    }

    @Test
    public void get_current_weather_returns_200() throws IOException {
        String city = "Helsinki";
        String units = String.valueOf((Object) null);

        ClientResponse response = weatherApi.getCurrentWeatherClientResponse(city, units);
        assertThat(response.getStatus(), is(OK.value()));
    }

    @Test
    public void get_weather_forecast_returns_200() throws IOException {
        String city = "London";
        String units = String.valueOf((Object) null);

        ClientResponse response = weatherApi.getWeatherForecastClientResponse(city, units);
        assertThat(response.getStatus(), is(OK.value()));
    }

    @Test
    public void should_return_5_days_forecast_data_for_city() throws IOException {
        String city = "Barcelona";
        String units = String.valueOf((Object) null);

        int hours_in_day = 24;
        int forecasts_in_every_3_hours = 3;
        int forecasts_per_day = hours_in_day / forecasts_in_every_3_hours;
        int all_forecasts_combined = forecasts_per_day * 5;

        WeatherForecastResponse weatherForecastData = weatherApi.getWeatherForecastDataForCity(city, units);
        assertEquals(weatherForecastData.getList().size(), all_forecasts_combined);
    }

    @Test
    public void should_return_404_when_city_unknown() {
        String city = "chjv";
        String units = String.valueOf((Object) null);

        ClientResponse response = weatherApi.getWeatherForecastClientResponse(city, units);
        assertThat(response.getStatus(), is(NOT_FOUND.value()));
    }

    @Test
    public void should_return_message_city_not_found_when_city_unknown() {
        String city = "trgy";
        String units = String.valueOf((Object) null);
        String city_not_found_message = "city not found";

        CurrentWeatherResponse currentWeatherData = weatherApi.getCurrentWeatherDataForCity(city, units);
        assertEquals(currentWeatherData.getMessage(), city_not_found_message);
    }

    @Test
    public void should_return_forecast_that_is_not_older_than_3hours() {
        //     tagastatakse mitte vanem kui 3h vana ilmaennustus
        throw new NotImplementedException("Test not implemented!");
    }

    @Test
    public void should_return_correct_coordinates_for_the_city() {
        //    tagastatakse õige linna koordinaadid
        throw new NotImplementedException("Test not implemented!");
    }

    @Test
    public void should_return_coordinates_in_correct_form() {
        //     Koordinaadid kujul lat,lon, nt "59.44,24.75"
        throw new NotImplementedException("Test not implemented!");
    }

    @Test
    public void should_return_forecast_with_temperature_data() throws IOException {
        // forecast-i response-is on olemas temperatuuri (või niiskuse, õhurõhu) andmed
        String city = "Rome";
        String units = String.valueOf((Object) null);

        WeatherForecastResponse weatherForecastData = weatherApi.getWeatherForecastDataForCity(city, units);
        List<ForecastDto> forecast = weatherForecastData.getList();

        System.out.println(weatherForecastData.getList());
        throw new NotImplementedException("Test not implemented!");
    }
}
