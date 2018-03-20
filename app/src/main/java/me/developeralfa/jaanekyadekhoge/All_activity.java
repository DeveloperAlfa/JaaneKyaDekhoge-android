package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class All_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_activity);
    }

    public void aaspas(View view) {
        startActivity(new Intent(this,Camera.class));
    }

    public void Handloom(View view) {
        startActivity(new Intent(this,Handloom.class));
    }

    public void Planner(View view) {
        startActivity(new Intent(this,ItineraryForm.class));
    }
}
