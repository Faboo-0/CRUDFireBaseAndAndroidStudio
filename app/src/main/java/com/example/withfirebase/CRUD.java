package com.example.withfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CRUD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
    }

    public void onCreate (View view){
        Intent i = new Intent(this, CrudCreate.class);
        startActivity(i);
    }

    public void onUpdate(View view){
        Toast.makeText(this,"Esta funcionalidad no esta implementada", Toast.LENGTH_SHORT).show();
    }

    public void onDelete(View view){
        Intent i = new Intent(this, CrudDelete.class);
        startActivity(i);
    }

    public void onListar(View view){
        Intent i = new Intent(this, CrudListar.class);
        startActivity(i);
    }
}
