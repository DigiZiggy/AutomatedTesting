package gm.taltech.ee.integration;

import com.sun.jersey.api.client.ClientResponse;
import gm.taltech.ee.weatherwise.WeatherReport;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
import gm.taltech.ee.weatherwise.helpers.Helper;
import gm.taltech.ee.weatherwise.WeatherWise;
import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.payload.dto.CoordinatesDto;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;
import gm.taltech.ee.weatherwise.payload.response.WeatherForecastResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class WeatherApiTest {

    private WeatherWise weatherWise;
    private WeatherApi weatherApi;
    private Helper helper;
    private ObjectMapper mapper;


    @Before
    public void setUp() {
        weatherApi = new WeatherApi();
        weatherWise = new WeatherWise(weatherApi);
        helper = new Helper();
        mapper = new ObjectMapper();
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
    public void get_current_weather_returns_200() {
        String city = "Helsinki";
        String units = String.valueOf((Object) null);

        ClientResponse response = weatherApi.getCurrentWeatherClientResponse(city, units);
        assertThat(response.getStatus(), is(OK.value()));
    }

    @Test
    public void get_weather_forecast_returns_200() {
        String city = "London";
        String units = String.valueOf((Object) null);

        ClientResponse response = weatherApi.getWeatherForecastClientResponse(city, units);
        assertThat(response.getStatus(), is(OK.value()));
    }

    @Test
    public void should_return_5_days_forecast_data_for_city() {
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
    public void should_return_correct_coordinates_for_the_city() {
        String city = "Berlin";
        String units = String.valueOf((Object) null);
        CoordinatesDto coordinates = new CoordinatesDto();

        Double lat = 52.52;
        Double lon = 13.39;
        coordinates.setLat(lat);
        coordinates.setLon(lon);

        CurrentWeatherResponse currentWeatherData = weatherApi.getCurrentWeatherDataForCity(city, units);
        assertThat(currentWeatherData.getCoord(), is(coordinates));
    }

    @Test
    public void should_return_coordinates_in_correct_form()
            throws CurrentWeatherDataMissingException, CityIsEmptyException, IOException {
        String city = "Paris";
        String units = String.valueOf((Object) null);
        String coordinates = "48.86,2.35";

        WeatherReport weatherReport = weatherWise.getWeatherReportForCityInUnits(city, units);

        assertEquals(weatherReport.getWeatherReportDetails().getCoordinates() ,coordinates);
    }

    @Test
    public void should_save_weatherReport_to_json_for_given_city()
            throws CurrentWeatherDataMissingException, CityIsEmptyException, IOException {
        String city = "Vienna";
        String units = String.valueOf((Object) null);

        WeatherReport actualWeatherReport = weatherWise.getWeatherReportForCityInUnits(city, units);
        weatherWise.saveWeatherReportIntoJsonFile(actualWeatherReport, city);

        WeatherReport savedWeatherReport = read_json_from_file(city);
        assertEquals(savedWeatherReport, actualWeatherReport);
    }

    @Test
    public void should_return_current_weather_with_temperature_data() {
        String city = "Rome";
        String units = String.valueOf((Object) null);

        CurrentWeatherResponse currentWeatherData = weatherApi.getCurrentWeatherDataForCity(city, units);

        assertThat(currentWeatherData.getMain().getTemp(), is(IsNull.notNullValue()));
    }

    private WeatherReport read_json_from_file(String city) throws IOException {
        return mapper.readValue(new File(
                        "../course-project/src/main/java/gm/taltech/ee/weatherwise/files/" + city + ".json"),
                WeatherReport.class);
    }
}
