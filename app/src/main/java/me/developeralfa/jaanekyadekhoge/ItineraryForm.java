package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ItineraryForm extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_form);
        editText = findViewById(R.id.plan);
    }

    public void submit(View view) {
        Intent intent = new Intent(this,Tags.class);
        intent.putExtra("text",editText.getText().toString());
        startActivity(intent);

    }
}
