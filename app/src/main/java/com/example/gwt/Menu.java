package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_COD = "CODU" ;
     int cg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        if(extras!= null) {

            String cod = extras.getString(MainActivity.EXTRA_MESSAGE_COD);

            cg = Integer.parseInt(cod);

        }

    }
    public void Sair(View s){
        Intent intent = new Intent(Menu.this, MainActivity.class);
        startActivity(intent);
    }
    public void Perfil(View s){
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id", cg);

        Intent intent = new Intent(Menu.this, PerfilSocio.class);
        intent.putExtras(dataBundle);
        startActivity(intent);

    }

    public void CadastrarClientes(View s){
        Intent intent = new Intent(Menu.this, CadCliente.class);
        startActivity(intent);
    }

    public void CadastrarContratos(View s){
        Intent intent = new Intent(Menu.this, CadContrato.class);
        startActivity(intent);
    }

    public void VisContratos(View s){
        Intent intent = new Intent(Menu.this, VisContrato.class);
        startActivity(intent);
    }


}