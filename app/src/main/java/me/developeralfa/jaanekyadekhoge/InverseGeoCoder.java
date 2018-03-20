package me.developeralfa.jaanekyadekhoge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by devalfa on 20/3/18.
 */

public interface InverseGeoCoder {
    @GET("geocode/json")
    Call<InverseGeocode> GeocodeReceiver(@Query("latlng") String text,@Query("key") String key);
}
