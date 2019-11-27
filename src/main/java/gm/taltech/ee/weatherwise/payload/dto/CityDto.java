package gm.taltech.ee.weatherwise.payload.dto;

import lombok.Data;

@Data
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

