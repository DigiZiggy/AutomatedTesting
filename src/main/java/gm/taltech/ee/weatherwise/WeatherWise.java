package gm.taltech.ee.weatherwise;

import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.api.response.CurrentWeatherData;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;

public class WeatherWise {

    private WeatherApi weatherApi;

    public WeatherWise(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherReport getWeatherReportForCity(String city) throws CityIsEmptyException, CurrentWeatherDataMissingException {
        WeatherReport weatherReport = new WeatherReport();
        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();

        if (isCityMissing(city)) {
            throw new CityIsEmptyException("City is empty");
        } else {
            CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherDataForCity(city);
            if (currentWeatherData != null) {
                weatherReportDetails.setCity(currentWeatherData.getName());
                weatherReport.setWeatherReportDetails(weatherReportDetails);
                return weatherReport;
            } else {
                throw new CurrentWeatherDataMissingException("Current weather data missing");
            }
        }
    }

    private boolean isCityMissing(String city) {
        return city == null || city.isEmpty();
    }
}
