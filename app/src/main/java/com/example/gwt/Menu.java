package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();

        String cod = extras.getString(MainActivity.EXTRA_MESSAGE_COD);

        final String cg = String.valueOf(cod);
        Toast.makeText(this, cg, Toast.LENGTH_SHORT).show();

    }
    public void Sair(View s){
        Intent intent = new Intent(Menu.this, MainActivity.class);
        startActivity(intent);
    }
    public void Perfil(View s){
        Intent intent = new Intent(Menu.this, PerfilSocio.class);
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

    public void AtividadeDiaria(View s){
        Intent intent = new Intent(Menu.this, AtividadeDiaria.class);
        startActivity(intent);
    }
}