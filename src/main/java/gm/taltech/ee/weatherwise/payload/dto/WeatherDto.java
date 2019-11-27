package gm.taltech.ee.weatherwise.payload.dto;

import lombok.Data;

@Data
public class WeatherDto {
    private Integer id;
    private String main;
    private String description;
    private String icon;
}
