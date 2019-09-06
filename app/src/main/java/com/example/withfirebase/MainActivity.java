package com.example.withfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    //Datos del usuario
    List<String> lDataUser = new ArrayList<>();

    private TextView sText;
    private FusedLocationProviderClient fusedLocationClient;
    DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRootChild = mDatabaseReference.child("Name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lDataUser.add("C00237");    //Hardcodear Usuario

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);            //para obtener la ubicacion
        onGetRealLocation();

        sText = (TextView) findViewById(R.id.txtTexto);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mRootChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String texto = dataSnapshot.getValue().toString();
                sText.setText(texto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onGetRealLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
        fusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null){
                    Map<String,Object> latlang = new HashMap<>();
                    latlang.put("usuario", lDataUser);
                    latlang.put("latitud", location.getLatitude());
                    latlang.put("longitud", location.getLongitude());

                    mDatabaseReference.child("Ubicacion").push().setValue(latlang);
                }else Toast.makeText(MainActivity.this, "- Active su ubicacion.\n- Permita a la aplicacion tener acceso a su ubicacion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void NextPageCRUD(View view) {
        Intent i = new Intent(this, CRUD.class);
        startActivity(i);
    }

    public void NextPageMaps(View view) {
        Intent i = new Intent(this, LocalitationRealTime.class);
        startActivity(i);
    }

    public void NextPageAppDemo(View view) {
        Intent i = new Intent(this, Productos.class);
        startActivity(i);
    }
}
