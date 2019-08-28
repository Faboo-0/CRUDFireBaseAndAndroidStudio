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

import java.util.UUID;

public class CrudCreate extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtCorreo, txtCodigo;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDataBaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_create);

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtCodigo = (EditText)findViewById(R.id.txtCodigo);

        onStartDataBase();
    }

    public void onCreate(View view){

        String sNombre = txtNombre.getText().toString();
        String sApellido = txtApellido.getText().toString();
        String sCorreo = txtCorreo.getText().toString();
        String sCodigo = txtCodigo.getText().toString();

        if(sNombre.isEmpty() || sApellido.isEmpty() || sCorreo.isEmpty() || sCodigo.isEmpty()){
            Toast.makeText(this, "Debe llenar todos los campo", Toast.LENGTH_SHORT).show();
        }else{
            B_Usuario usu = new B_Usuario();
            //usu.setFbId(UUID.randomUUID().toString());
            usu.setFbNombre(sNombre);
            usu.setFbApellidos(sApellido);
            usu.setFbCorreo(sCorreo);
            usu.setFbCodigo(sCodigo);

            mDataBaseReference.child("Usuario").child(usu.getFbCodigo()).setValue(usu);
            Toast.makeText(this, "Usuario creado correctamente ...", Toast.LENGTH_SHORT).show();
            onLimpiar();
        }
    }

    public void onStartDataBase(){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDataBaseReference = mFirebaseDatabase.getReference();
    }

    public void onLimpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtCodigo.setText("");
    }
}
