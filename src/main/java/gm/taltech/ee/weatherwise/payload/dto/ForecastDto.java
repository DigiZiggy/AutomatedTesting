package gm.taltech.ee.weatherwise.payload.dto;

import lombok.Data;

import java.util.List;

@Data
public class ForecastDto {
    private long dt;
    private MainDto main;
    private List<WeatherDto> weather;
    private CloudsDto clouds;
    private WindDto wind;
    private SysDto sys;
    private String dt_txt;

}
