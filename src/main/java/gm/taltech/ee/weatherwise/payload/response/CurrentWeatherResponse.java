package gm.taltech.ee.weatherwise.payload.response;

import gm.taltech.ee.weatherwise.payload.dto.*;
import lombok.Data;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@Data
@Getter
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherResponse {
    private CoordinatesDto coord;
    private List<WeatherDto> weather;
    private String base;
    private MainDto main;
    private long visibility;
    private WindDto wind;
    private CloudsDto clouds;
    private long dt;
    private SysDto sys;
    private String timezone;
    private String id;
    private String name;
    private Integer cod;
    private String message;
}
