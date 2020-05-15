package sk.fri.uniza.api;

import retrofit2.Call;
import retrofit2.http.*;
import sk.fri.uniza.model.Location;
import sk.fri.uniza.model.Token;
import sk.fri.uniza.model.WeatherData;

import java.util.List;
import java.util.Map;


public interface WeatherStationService {

    @GET("/weather/{station}/current")
    Call<Map<String, String>> getCurrentWeatherAsMap(
            @Path("station") String station);


    @GET("/weather/{station}/current")
    Call<Map<String, String>> getCurrentWeatherAsMap(
            @Path("station") String station,
            @Query("fields") List<String> fields);


    @GET("/weather/locations")
    Call<List<Location>> getStationLocations();


    @GET("/weather/{station}/current")
    Call<WeatherData> getCurrentWeather(@Path("station") String station);

    @GET("/weather/{station}/current")
    Call<WeatherData> getCurrentWeather(@Path("station") String station,
                                        @Query("fields") List<String> fields);


    // ... getHistoryWeather(station, from, to);
    @GET("/weather/{station}/history")
    Call<List<WeatherData>> getHistoryWeather(@Path("station") String station,
                                        @Query("from") String from,
                                        @Query("to") String to);

    // ... getHistoryWeather(  station, from, to, fields);
    @GET("/weather/{station}/history")
    Call<List<WeatherData>> getHistoryWeather(@Path("station") String station,
                                        @Query("from") String from,
                                        @Query("to") String to,
                                        @Query("fields") List<String> fields);

    // ... getToken(authorization, claims);
    @POST("/apikey/createjwt")
    Call<Token> getToken(@Header("authorization") String authorization,
                         @Query("claims") List<String> claims);

    // ... getStationLocationsAuth(authorization);
    @GET("/weatherAuth/locations")
    Call<List<Location>> getStationLocations(@Header("authorization") String authorization);

    // ... getCurrentWeatherAuth(authorization, station);
    @GET("/weatherAuth/{station}/current")
    Call<WeatherData> getCurrentWeatherAuth(@Header("authorization") String authorization,
                                            @Path("station") String station);

    // ... getCurrentWeatherAuth(authorization, station, fields);
    @GET("/weatherAuth/{station}/current")
    Call<WeatherData> getCurrentWeatherAuth(@Header("authorization") String authorization,
                                            @Path("station") String station,
                                            @Query("fields") List<String> fields);

    // ... getHistoryWeatherAuth(authorization, station, from, to);
    @GET("/weatherAuth/{station}/history")
    Call<List<WeatherData>> getHistoryWeatherAuth(@Header("authorization") String authorization,
                                                  @Path("station") String station,
                                                  @Query("from") String from,
                                                  @Query("to") String to);

    // ... getHistoryWeatherAuth(authorization, station, from, to, fields);
    @GET("/weatherAuth/{station}/history")
    Call<List<WeatherData>> getHistoryWeatherAuth(@Header("authorization") String authorization,
                                                  @Path("station") String station,
                                                  @Query("from") String from,
                                                  @Query("to") String to,
                                                  @Query("fields") List<String> fields);
}