package sk.fri.uniza;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.fri.uniza.api.WeatherStationService;
import sk.fri.uniza.model.WeatherData;

import java.io.IOException;
import java.util.List;

public class IotNode {
    private final Retrofit retrofit;
    private final WeatherStationService weatherStationService;

    public IotNode() {

        retrofit = new Retrofit.Builder()
                // Url adresa kde je umietnená WeatherStation služba
                .baseUrl("http://ip172-18-0-52-bquijvlim9m000bos9sg-9000.direct.labs.play-with-docker.com/")
                // Na konvertovanie JSON objektu na java POJO použijeme
                // Jackson knižnicu
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        // Vytvorenie inštancie komunikačného rozhrania
        weatherStationService = retrofit.create(WeatherStationService.class);

    }

    public WeatherStationService getWeatherStationService() {
        return weatherStationService;
    }

    public double getAverageTemperature(String station, String from, String to) {
        double a = 0;
        int counter = 0;
                Call<List<WeatherData>> allTemperatures = weatherStationService.getHistoryWeather(station, from, to, List.of("airTemperature"));
        try {
            // Odoslanie požiadavky na server pomocou REST rozhranie
            Response<List<WeatherData>> response = allTemperatures.execute();

            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme inštancie triedy WeatherData
                List<WeatherData> body = response.body();
                System.out.println("Historia pocasia bez fieldov:");
                System.out.println(body);


                for (int i = 0; i < body.size(); i++) {
                    a = a + Double.parseDouble(String.valueOf(body.get(i).getAirTemperature()));
                    counter = i;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        double priemer = a/counter;
        return priemer;
    }
}