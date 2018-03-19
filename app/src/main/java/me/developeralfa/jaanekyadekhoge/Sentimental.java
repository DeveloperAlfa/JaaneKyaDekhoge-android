package me.developeralfa.jaanekyadekhoge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by devalfa on 19/3/18.
 */

public interface Sentimental {
    @Headers({"X-AYLIEN-TextAPI-Application-Key: 3b7758c19df7ea0d71e196a99d2c986f","X-AYLIEN-TextAPI-Application-ID: 3138670b"})
    @GET("iab-qag")
    Call<res> ReceiveResponse(@Query("mode") String mode,@Query("text") String text,@Query("language") String language);

}
