package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class frun1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frun1);
    }

    public void next(View view) {
        startActivity(new Intent(this,frun2.class));
        finish();
    }
}
