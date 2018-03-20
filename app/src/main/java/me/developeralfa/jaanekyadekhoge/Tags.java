package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tags extends AppCompatActivity {
    ArrayList<String> words = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        listView = findViewById(R.id.list);


            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.aylien.com/api/v1/classify/").addConverterFactory(GsonConverterFactory.create()).build();
            Sentimental sentimental = retrofit.create(Sentimental.class);
            String text = getIntent().getStringExtra("text");
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
        listView.setAdapter(arrayAdapter);
            Call<res> resCall = sentimental.ReceiveResponse("tweet", text, "en");
            resCall.enqueue(new Callback<res>() {
                @Override
                public void onResponse(Call<res> call, Response<res> response) {
                    res Res = response.body();

                    ArrayList<cat_ob> cat_obs= Res.categories;

                    for(int i=0;i<cat_obs.size();i++)
                    {
                        words.add(cat_obs.get(i).label);
                    }
                    arrayAdapter.notifyDataSetChanged();



                }

                @Override
                public void onFailure(Call<res> call, Throwable t) {
                    //TODO

                }
            });

    }
}
