package gm.taltech.ee.weatherwise;

import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;

public class WeatherWise {

    private WeatherApi weatherApi;

    public WeatherWise(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherReport getWeatherReportForCityInCertainUnits(String city, String units) throws CityIsEmptyException, CurrentWeatherDataMissingException {
        WeatherReport weatherReport = new WeatherReport();
        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();

        if (isCityMissing(city)) {
            throw new CityIsEmptyException("City is empty");
        } else {
            CurrentWeatherResponse currentWeatherData = weatherApi.getCurrentWeatherDataForCity(city, units);

            if (currentWeatherData != null) {
                weatherReportDetails.setCity(currentWeatherData.getName());
                weatherReportDetails.setTemperatureUnit(units);
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
