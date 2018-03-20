package me.developeralfa.jaanekyadekhoge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by devalfa on 20/3/18.
 */

public interface MapsApi {
    @GET("directions/json")
    Call<MapCore> GetLocation(@Query("origin") String address,@Query("destination") String Jaipur,@Query("departure_time") String time,@Query("traffic_model") String best_guess,@Query("key") String key);
}
