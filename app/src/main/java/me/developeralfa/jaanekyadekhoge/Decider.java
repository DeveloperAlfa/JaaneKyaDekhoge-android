package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Decider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences firstRun = getSharedPreferences("run",MODE_PRIVATE);
        if(firstRun.contains("itHasRun"))
        {
            checkLocation();
        }
        else
        {
            SharedPreferences.Editor editor = firstRun.edit();
            editor.putString("itHasRun","yes");
            editor.commit();
            startActivity(new Intent(this,frun1.class));
        }
    }

    private void checkLocation() {
        Intent intent = new Intent(this,LocationCheck.class);
        intent.putExtra("loc",1);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
        {
            if(resultCode==1)
            {
                if(data.getIntExtra("isRajasthan",0)==1) startActivity(new Intent(this,Camera.class));
                else
                {
                    Toast.makeText(this,"Not in Rajasthan",Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
