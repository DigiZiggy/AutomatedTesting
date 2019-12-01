package gm.taltech.ee.unit;

import com.sun.jersey.api.client.ClientResponse;
import gm.taltech.ee.weatherwise.WeatherReport;
import gm.taltech.ee.weatherwise.WeatherWise;
import gm.taltech.ee.weatherwise.api.WeatherApi;
import gm.taltech.ee.weatherwise.exception.CityIsEmptyException;
import gm.taltech.ee.weatherwise.exception.CurrentWeatherDataMissingException;
import gm.taltech.ee.weatherwise.helpers.Helper;
import gm.taltech.ee.weatherwise.payload.response.CurrentWeatherResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

public class WeatherWiseUnitTest {

    private WeatherWise weatherWise;
    private WeatherApi weatherApi;
    private Helper helper;


    @Before
    public void setUp() {
        weatherApi = new WeatherApi();
        weatherWise = new WeatherWise(weatherApi);
        helper = new Helper();
    }

    @Test
    public void read_from_file_should_return_list_of_cities() throws IOException {

        List<String> expectedOutput = new ArrayList<String>(Arrays.asList("Moscow", "London", "Saint Petersburg",
                "Berlin", "Madrid", "Kyiv", "Rome", "Paris", "Bucharest", "Minsk", "Budapest", "Hamburg", "Warsaw",
                "Vienna", "Barcelona", "Stockholm", "Kharkiv", "Novosibirsk", "Yekaterinburg", "Nizhniy Novgorod",
                "Belgrade", "Munich", "Milan", "Prague", "Copenhagen", "Sofia", "Samara", "Omsk", "Kazan",
                "Rostov-na-Donu", "Chelyabinsk", "Ufa", "Dnipro", "Donetsk", "Dublin", "Brussels", "Volgograd",
                "Odessa", "Birmingham", "Perm", "Koeln", "Naples", "Krasnoyarsk", "Turin", "Liverpool", "Saratov",
                "Voronezh", "Valencia", "Zaporizhia", "Marseille", "Lodz", "Krakow", "Riga", "Amsterdam", "Nottingham",
                "Lviv", "Sevilla", "Tol'yatti", "Zagreb", "Sarajevo", "Sheffield", "Zaragoza", "Athens", "Kryvyi Rih",
                "Frankfurt am Main", "Krasnodar", "Palermo", "Ulyanovsk", "Chisinau", "Wroclaw", "Izhevsk", "Bristol",
                "Yaroslavl", "Barnaul", "Rotterdam", "Essen", "Glasgow", "Stuttgart", "Dortmund", "Vladivostok",
                "Irkutsk", "Genoa", "Oslo", "Khabarovsk", "Khabarovsk Vtoroy", "Duesseldorf", "Goeteborg", "Poznan",
                "Malaga", "Helsinki", "Orenburg", "Bremen", "Vilnius", "Novokuznetsk", "Ryazan", "Tyumen", "Lisbon",
                "Lipetsk", "Hannover", "Penza", "Mykolayiv", "Naberezhnyye Chelny", "Leicester", "Leipzig",
                "Kalininskiy", "Duisburg", "Astrakhan", "Nuernberg", "Makhachkala", "Dresden", "Tomsk", "Mariupol",
                "Gomel", "Kemerovo", "Skopje", "The Hague", "Lyon", "Tula", "Edinburgh", "Gdansk", "Antwerpen", "Kirov",
                "Leeds", "Luhansk", "Cardiff", "Cheboksary", "Murcia", "Kaliningrad", "Toulouse", "Bryansk",
                "Bratislava", "Ivanovo", "Sevastopol", "Magnitogorsk", "Wandsbek", "Kursk", "Szczecin", "Palma", "Tver",
                "Khmelnytskyi", "Manchester", "Tallinn", "Bochum", "Las Palmas de Gran Canaria", "Nizhny Tagil",
                "Bochum-Hordel", "Makiyivka", "Tirana", "Kaunas"));

        List<String> actualOutput = weatherWise.readFromFile();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void should_convert_and_write_weatherReport_into_json() throws CurrentWeatherDataMissingException, CityIsEmptyException, IOException {
        String city = "Berlin";
        String units = String.valueOf((Object) null);

        WeatherReport weatherReport = weatherWise.getWeatherReportForCityInCertainUnits(city, units);

        weatherWise.saveWeatherReportIntoJsonFile(weatherReport);
        throw new NotImplementedException("Test not implemented!");

    }
}
