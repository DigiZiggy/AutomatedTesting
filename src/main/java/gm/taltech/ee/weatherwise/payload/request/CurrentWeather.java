package gm.taltech.ee.weatherwise.payload.request;

import gm.taltech.ee.weatherwise.payload.dto.*;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {

}

