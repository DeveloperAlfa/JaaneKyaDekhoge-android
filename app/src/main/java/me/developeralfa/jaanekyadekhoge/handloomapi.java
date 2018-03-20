package me.developeralfa.jaanekyadekhoge;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by devalfa on 20/3/18.
 */

public interface handloomapi {
    @GET("movies.json")
    Call<ArrayList<ImadResult>> fetch();
}
