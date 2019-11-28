package gm.taltech.ee.weatherwise.payload.dto;

import lombok.Data;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@Getter
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDto {
    private String id;
    private String name;
    private CoordinatesDto coord;
    private String country;
    private String population;
    private String timezone;
    private long sunrise;
    private long sunset;

}

