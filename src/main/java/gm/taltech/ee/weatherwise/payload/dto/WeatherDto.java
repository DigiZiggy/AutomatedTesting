package gm.taltech.ee.weatherwise.payload.dto;

import lombok.Data;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@Getter
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    private Integer id;
    private String main;
    private String description;
    private String icon;
}
