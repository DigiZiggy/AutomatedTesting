package gm.taltech.ee.weatherwise;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@JsonAutoDetect
public class ForecastReport {
    private List<Forecast> forecasts;

}
