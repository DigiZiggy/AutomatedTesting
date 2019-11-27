package gm.taltech.ee.weatherwise;

import lombok.Data;

@Data
public class CurrentWeatherReport {
    private Integer temperature;
    private Integer humidity;
    private Integer pressure;

}
