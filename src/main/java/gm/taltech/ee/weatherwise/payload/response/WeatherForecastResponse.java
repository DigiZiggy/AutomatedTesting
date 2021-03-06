package gm.taltech.ee.weatherwise.payload.response;

import gm.taltech.ee.weatherwise.payload.dto.CityDto;
import gm.taltech.ee.weatherwise.payload.dto.ForecastDto;
import lombok.Data;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Data
@Getter
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastResponse {
    private Integer cod;
    private String message;
    private Integer cnt;

    @Getter
    private List<ForecastDto> list;
    private CityDto city;

}

