package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AtividadeDiaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_diaria);

        getSupportActionBar().hide();
    }
}