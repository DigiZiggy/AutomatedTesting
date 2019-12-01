package gm.taltech.ee.weatherwise;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.Date;

@Data
@Getter
@Setter
@JsonAutoDetect
public class Forecast {
    private String date;
    private CurrentWeatherReport weather;
}
