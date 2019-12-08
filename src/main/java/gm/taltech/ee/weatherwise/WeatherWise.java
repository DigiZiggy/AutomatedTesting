package gm.taltech.ee.weatherwise;

import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
import gm.taltech.ee.weatherwise.payload.dto.ForecastDto;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;
import gm.taltech.ee.weatherwise.payload.response.WeatherForecastResponse;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherWise {

    private WeatherApi weatherApi;

    public WeatherWise(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public List<String> readFromFile() throws IOException {
        return FileUtils.readLines(new File("../course-project/src/main/java/gm/taltech/ee/" +
                "weatherwise/files/listOfCities.txt"), "utf-8");
    }

    public WeatherReport getWeatherReportForCityInUnits(String city, String units)
            throws CityIsEmptyException, CurrentWeatherDataMissingException, IOException {
        WeatherReport weatherReport = new WeatherReport();
        ForecastReport forecastReport = new ForecastReport();
        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport();

        if (isCityMissing(city)) {
            throw new CityIsEmptyException("City is empty");
        } else {
            CurrentWeatherResponse currentWeatherData = weatherApi.getCurrentWeatherDataForCity(city, units);
            WeatherForecastResponse weatherForecastData = weatherApi.getWeatherForecastDataForCity(city, units);

            if (currentWeatherData != null) {
                if (currentWeatherData.getCoord() != null) {
                    setWeatherReportCoordinates(weatherReportDetails, currentWeatherData);
                }

                if (currentWeatherData.getMain() != null) {
                    setCurrentWeatherReportData(currentWeatherReport, currentWeatherData);
                }

                weatherReportDetails.setCity(currentWeatherData.getName());
                setWeatherReportDetailsTemperatureUnits(weatherReportDetails, units);

            } else {
                throw new CurrentWeatherDataMissingException("Current weather data missing");
            }

            if (weatherForecastData != null) {
                setForecastsToForecastReport(weatherForecastData, forecastReport);
            }

            setWeatherReport(currentWeatherReport, weatherReport, weatherReportDetails, forecastReport);
            return weatherReport;
        }
    }

    public void setWeatherReport(
            CurrentWeatherReport currentWeatherReport, WeatherReport weatherReport,
            WeatherReportDetails weatherReportDetails, ForecastReport forecastReport) {
        weatherReport.setWeatherReportDetails(weatherReportDetails);
        weatherReport.setCurrentWeatherReport(currentWeatherReport);
        weatherReport.setForecastReport(forecastReport);
    }

    public void setCurrentWeatherReportData(
            CurrentWeatherReport currentWeatherReport, CurrentWeatherResponse currentWeatherData) {
        currentWeatherReport.setTemperature(currentWeatherData.getMain().getTemp());
        currentWeatherReport.setPressure(currentWeatherData.getMain().getPressure());
        currentWeatherReport.setHumidity(currentWeatherData.getMain().getHumidity());
    }

    public void setWeatherReportCoordinates(
            WeatherReportDetails weatherReportDetails, CurrentWeatherResponse currentWeatherData) {
        String coordinates = currentWeatherData.getCoord().getLat() + "," +
                currentWeatherData.getCoord().getLon();
        weatherReportDetails.setCoordinates(coordinates);
    }

    public void setWeatherReportDetailsTemperatureUnits(
            WeatherReportDetails weatherReportDetails, String units) {
        switch (units) {
            case "null":
                weatherReportDetails.setTemperatureUnit("Kelvin");
                break;
            case "imperial":
                weatherReportDetails.setTemperatureUnit("Fahrenheit");
                break;
            case "metric":
                weatherReportDetails.setTemperatureUnit("Celsius");
                break;
        }
    }

    public void setForecastsToForecastReport(
            WeatherForecastResponse weatherForecastData, ForecastReport forecastReport) {
        List<Forecast> forecasts = new ArrayList<>();
        for (ForecastDto forecastDto : weatherForecastData.getList()) {
            Forecast forecast = new Forecast();
            forecast.setDate(forecastDto.getDt_txt().substring(0, 10));
            CurrentWeatherReport forecastWeatherReport = new CurrentWeatherReport();
            forecastWeatherReport.setHumidity(forecastDto.getMain().getHumidity());
            forecastWeatherReport.setPressure(forecastDto.getMain().getPressure());
            forecastWeatherReport.setTemperature(forecastDto.getMain().getTemp());
            forecast.setWeather(forecastWeatherReport);
            forecasts.add(forecast);
        }
        forecastReport.setForecasts(forecasts);
    }

    public void saveWeatherReportIntoJsonFile(WeatherReport weatherReport, String city) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("../course-project/src/main/java/gm/taltech/ee/weatherwise/files/" +
                    city + ".json"), weatherReport);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCityMissing(String city) {
        return city == null || city.isEmpty();
    }
}
