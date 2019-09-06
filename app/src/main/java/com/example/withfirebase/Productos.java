package com.example.withfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.withfirebase.Model.B_Producto;
import com.example.withfirebase.Model.B_Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Productos extends AppCompatActivity {

    DatabaseReference mDataBaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRootChild = mDataBaseReference.child("Producto");
    private List<B_Producto> listProducto = new ArrayList<B_Producto>();
    ArrayAdapter<B_Producto> arrayAdapter;

    ListView lvProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        lvProductos = (ListView)findViewById(R.id.lvProductos);
        onListarProducto();


        //Click en List View
        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Productos.this, "Ubicacion de destino", Toast.LENGTH_SHORT).show();
                onLocalizacion();
            }
        });
    }

    public void onListarProducto (){
        mRootChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listProducto.clear();
                for(DataSnapshot objDataSnapshot: dataSnapshot.getChildren()){
                    B_Producto usu = objDataSnapshot.getValue(B_Producto.class);
                    listProducto.add(usu);

                    arrayAdapter = new ArrayAdapter<B_Producto>(Productos.this ,android.R.layout.simple_list_item_1, listProducto);

                    lvProductos.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onLocalizacion(){
        Intent i = new Intent(this,LocalitationRealTime.class);
        startActivity(i);
    }
}
