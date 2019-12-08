package gm.taltech.ee.weatherwise.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import gm.taltech.ee.weatherwise.payload.dto.ForecastDto;
import gm.taltech.ee.weatherwise.payload.dto.WeatherDto;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;
import gm.taltech.ee.weatherwise.payload.response.WeatherForecastResponse;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionLikeType;

import java.io.IOException;
import java.util.List;

import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.OK;

public class WeatherApi {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5";
    private static final String APPID = "ddb8ed2b392782fc5629698ab22da76c";
    private static Client client = getConfiguredClient();

    public WeatherApi() {
    }

    public CurrentWeatherResponse getCurrentWeatherDataForCity(String cityName, String units) {
        return getCurrentWeatherClientResponse(cityName, units).getEntity(CurrentWeatherResponse.class);
    }

    public WeatherForecastResponse getWeatherForecastDataForCity(String cityName, String units) {
        return getWeatherForecastClientResponse(cityName, units).getEntity(WeatherForecastResponse.class);
    }

    public ClientResponse getCurrentWeatherClientResponse(String cityName, String units) {
        String resourceUrl = BASE_URL + "/weather";
        return client.resource(resourceUrl)
                .queryParam("q", cityName)
                .queryParam("units", units)
                .queryParam("APPID", APPID)
                .get(ClientResponse.class);
    }

    public ClientResponse getWeatherForecastClientResponse(String cityName, String units) {
        String resourceUrl = BASE_URL + "/forecast";
        return client.resource(resourceUrl)
                .queryParam("q", cityName)
                .queryParam("units", units)
                .queryParam("APPID", APPID)
                .get(ClientResponse.class);
    }

    private static Client getConfiguredClient() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(FEATURE_POJO_MAPPING, TRUE);
        return create(config);
    }
}
