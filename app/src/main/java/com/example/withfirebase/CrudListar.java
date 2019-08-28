package com.example.withfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.withfirebase.Model.B_Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CrudListar extends AppCompatActivity {

    ListView lvListar;
    private List<B_Usuario> listUsuario = new ArrayList<B_Usuario>();
    ArrayAdapter<B_Usuario> arrayAdapter;

    DatabaseReference mDatabaseReference =  FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRootChild = mDatabaseReference.child("Usuario");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_listar);

        lvListar = (ListView)findViewById(R.id.lvList);
        onListar();
    }

    public void onListar(){
        mRootChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUsuario.clear();
                for(DataSnapshot objDataSnapshot: dataSnapshot.getChildren()){
                    B_Usuario usu = objDataSnapshot.getValue(B_Usuario.class);
                    listUsuario.add(usu);

                    arrayAdapter = new ArrayAdapter<B_Usuario>(CrudListar.this ,android.R.layout.simple_list_item_1, listUsuario);

                    lvListar.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
