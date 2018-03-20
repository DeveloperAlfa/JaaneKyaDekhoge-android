package me.developeralfa.jaanekyadekhoge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Handloom extends AppCompatActivity {
    ArrayList<ImadResult> imadResult = new ArrayList<>();
    handloomadapter handloomadapter1;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handloom);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://digisthan-developeralfa.c9users.io/").build();
        handloomapi Handloomapi = retrofit.create(handloomapi.class);
        listView = findViewById(R.id.list);

        handloomadapter1 = new handloomadapter(this,imadResult);
        listView.setAdapter(handloomadapter1);
        Call<ArrayList<ImadResult>> handloomapiCall =  Handloomapi.fetch();
        handloomapiCall.enqueue(new Callback<ArrayList<ImadResult>>() {
            @Override
            public void onResponse(Call<ArrayList<ImadResult>> call, Response<ArrayList<ImadResult>> response) {
                ArrayList<ImadResult> imadResults = response.body();
                Log.d("abc",imadResults.size()+"");
                imadResult.addAll(imadResults);
                handloomadapter1.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<ImadResult>> call, Throwable t) {

            }
        });

    }
}
