package com.example.withfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.withfirebase.Model.B_Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrudDelete extends AppCompatActivity {

    EditText txtCodigo;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDataBaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_delete);

        txtCodigo = (EditText)findViewById(R.id.txtCodigo);
        onStartDataBase();
    }

    public void onDelete(View view){
        String sCodigo = txtCodigo.getText().toString();

        B_Usuario usu = new B_Usuario();
        usu.setFbCodigo(sCodigo);

        mDataBaseReference.child("Usuario").child(usu.getFbCodigo()).removeValue();
        Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
        onLimpiar();
    }
    public void onStartDataBase(){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDataBaseReference = mFirebaseDatabase.getReference();
    }

    public void onLimpiar(){
        txtCodigo.setText("");
    }
}
