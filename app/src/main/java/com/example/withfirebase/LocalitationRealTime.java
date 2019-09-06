package com.example.withfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.example.withfirebase.Model.B_Maps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LocalitationRealTime extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabaseReference;

    private ArrayList<Marker> alRealTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> alAllRealTimeMarkers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localitation_real_time);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Point point = new Point();
        //point.x = -12.109435;
        //point.y = -76.972485;

        double x = -12.109435;
        double y = -76.972485;

        double x2 = -12.109964;
        double y2 = -76.97316;

        int height = 50;
        int width = 50;

        LatLng latlang = new LatLng(x,y);
        LatLng latlang2 = new LatLng(x2,y2);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.person);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        mMap.addMarker(new MarkerOptions().position(latlang).title("Oscar Ortiz").draggable(true).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        //mMap.addPolyline(new PolylineOptions().add(latlang,latlang2).width(3).color(Color.RED).geodesic(true));


        //Point2D.Double point = new Point2D.Double(x, y);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlang,15f));

        mDatabaseReference.child("Ubicacion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(Marker marker:alRealTimeMarkers){
                    marker.remove();
                }

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    B_Maps np = snapshot.getValue(B_Maps.class);
                    Double latitud  = np.getLatitud();
                    Double longitud = np.getLongitud();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud, longitud));
                    alAllRealTimeMarkers.add(mMap.addMarker(markerOptions));
                }

                alRealTimeMarkers.clear();
                alRealTimeMarkers.addAll(alAllRealTimeMarkers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
