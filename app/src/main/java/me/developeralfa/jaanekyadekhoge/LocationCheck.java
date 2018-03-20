package me.developeralfa.jaanekyadekhoge;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationCheck extends AppCompatActivity implements LocationListener {
    Location location;
    int flag =0;
    int isRajsthan=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
////        String text="Gamma";
////        Retrofit retrofit1 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
////                .baseUrl("https://maps.googleapis.com/maps/api/").build();
////        MapsApi mapsApi = retrofit1.create(MapsApi.class);
////        Call<MapCore> mapCoreCall = mapsApi.GetLocation(text, Constants.jaipur, "1541202457", Constants.best_guess, Constants.MapKey);
////        mapCoreCall.enqueue(new Callback<MapCore>() {
////            @Override
////            public void onResponse(Call<MapCore> call, Response<MapCore> response) {
////                MapCore mapCore = response.body();
////                String duration = mapCore.routes.get(0).legs.get(0).duration.text;
////                //Toast.makeText(LocationCheck.this, duration, Toast.LENGTH_SHORT).show();
////            }
////
////            @Override
////            public void onFailure(Call<MapCore> call, Throwable t) {
////
////            }
////        });
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        if(flag!=1) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://maps.googleapis.com/maps/api/").build();
            InverseGeoCoder inverseGeoCoder = retrofit.create(InverseGeoCoder.class);
            Call<InverseGeocode> inverseGeocodeCall =  inverseGeoCoder.GeocodeReceiver(location.getLatitude()+","+location.getLongitude(),"AIzaSyBpF9m7FyvWNRebmI4PaJUq8zLwBoKt_ts");
            inverseGeocodeCall.enqueue(new Callback<InverseGeocode>() {
                @Override
                public void onResponse(Call<InverseGeocode> call, Response<InverseGeocode> response) {
                    InverseGeocode inverseGeocode = response.body();
                    for(int i=0;i<inverseGeocode.results.get(0).address_components.size();i++)
                    {
                        if(inverseGeocode.results.get(0).address_components.get(i).long_name.matches("Rajasthan"))
                        {
                            isRajsthan = 1;


                            Log.d("raj","yes");

                            break;
                        }

                    }
                    Intent intent = new Intent();
                    intent.putExtra("isRajasthan",isRajsthan);
                    setResult(1,intent);
                    finish();
                }

                @Override
                public void onFailure(Call<InverseGeocode> call, Throwable t) {

                }
            });
        }
        if(flag==0) flag = 1;

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}