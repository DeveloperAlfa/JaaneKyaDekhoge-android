package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class frun2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rajhack);
    }

    public void next(View view) {
        startActivity(new Intent(this,frun3.class));
        finish();
    }
}
