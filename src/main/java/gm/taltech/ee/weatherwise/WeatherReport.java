package gm.taltech.ee.weatherwise;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WeatherReport {
    private WeatherReportDetails weatherReportDetails;
    private CurrentWeatherReport currentWeatherReport;
    private ForecastReport forecastReport;

}
