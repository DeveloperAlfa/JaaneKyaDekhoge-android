package me.developeralfa.jaanekyadekhoge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.aylien.com/api/v1/classify/").addConverterFactory(GsonConverterFactory.create()).build();
        Sentimental sentimental = retrofit.create(Sentimental.class);
        String text = "chandigarh";
        Call<res> resCall = sentimental.ReceiveResponse("tweet", text, "en");
        resCall.enqueue(new Callback<res>() {
            @Override
            public void onResponse(Call<res> call, Response<res> response) {
                res Res = response.body();

                String l = Res.categories.get(0).label;
                //Toast.makeText(MainActivity.this,l,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<res> call, Throwable t) {
                //TODO

            }
        });
        Retrofit retrofit1 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://maps.googleapis.com/maps/api/").build();
        MapsApi mapsApi = retrofit1.create(MapsApi.class);
        Call<MapCore> mapCoreCall = mapsApi.GetLocation(text,Constants.Jaipur,"1541202457",Constants.best_guess,Constants.MapKey);
        mapCoreCall.enqueue(new Callback<MapCore>() {
            @Override
            public void onResponse(Call<MapCore> call, Response<MapCore> response) {
                MapCore mapCore = response.body();
                String duration = mapCore.routes.get(0).legs.get(0).duration.text;
                Toast.makeText(MainActivity.this,duration,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MapCore> call, Throwable t) {

            }
        });
    }
}