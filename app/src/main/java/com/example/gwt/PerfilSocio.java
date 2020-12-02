package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PerfilSocio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_socio);

        getSupportActionBar().hide();
    }
    public void Info(View s){
        Intent intent = new Intent(PerfilSocio.this, Sobre.class);
        startActivity(intent);
    }
}