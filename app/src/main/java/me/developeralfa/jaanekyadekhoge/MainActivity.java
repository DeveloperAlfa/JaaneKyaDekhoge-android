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
        Sentimental sentimental  = retrofit.create(Sentimental.class);
        String text="Spiritual completeness in the Indian temples";
        Call<res> resCall = sentimental.ReceiveResponse("tweet",text,"en");
        resCall.enqueue(new Callback<res>() {
            @Override
            public void onResponse(Call<res> call, Response<res> response) {
                res Res = response.body();

                String l = Res.categories.get(0).label;
                Toast.makeText(MainActivity.this,l,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<res> call, Throwable t) {
                //TODO

            }
        });
    }
}
