package me.developeralfa.jaanekyadekhoge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Camera extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        dispatchTakePictureIntent();
        mImageView = findViewById(R.id.mImageView);

    }



        private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String upload = BitMapToString(imageBitmap);
            mImageView.setImageBitmap(imageBitmap);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            ArrayList<Input> inputs = new ArrayList<>();
            inputs.add(new Input(new Data(new ImageP(encoded))));
            ImagePOJO imagePOJO = new ImagePOJO(inputs);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.clarifai.com/v2/models/").addConverterFactory(GsonConverterFactory.create()).build();
            Visioner visioner = retrofit.create(Visioner.class);
            Call<FooResponse> fooResponseCall = visioner.postJson(imagePOJO);
            fooResponseCall.enqueue(new Callback<FooResponse>() {
                @Override
                public void onResponse(Call<FooResponse> call, Response<FooResponse> response) {
                    FooResponse fooResponse = response.body();
                    ArrayList<Concept> concept = fooResponse.outputs.get(0).data.concepts;
                    ArrayList<String> words = new ArrayList<>();
                    for(int i =0;i<concept.size();i++)
                    {
                        words.add(concept.get(i).name);
                    }
                    Intent intent = new Intent(Camera.this,CameraResult.class);
                    intent.putExtra("words",words);
                    startActivity(intent);
                    finish();

                }

                @Override
                public void onFailure(Call<FooResponse> call, Throwable t) {
                    Toast.makeText(Camera.this,t.getMessage(),Toast.LENGTH_SHORT);

                }
            });
        }
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    }

