package me.developeralfa.jaanekyadekhoge;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by devalfa on 20/3/18.
 */

public interface Visioner {
    @Headers({"Authorization: Key a592a6b638be4e4e81c9b705acfc01ec","Content-Type: application/json"})
    @POST("eee28c313d69466f836ab83287a54ed9/outputs")
    Call<FooResponse> postJson(@Body ImagePOJO body);

}
