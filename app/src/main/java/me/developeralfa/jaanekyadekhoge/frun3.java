package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class frun3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frun3);
    }

    public void next(View view) {
        startActivity(new Intent(this,Decider.class));
        finish();
    }
}
