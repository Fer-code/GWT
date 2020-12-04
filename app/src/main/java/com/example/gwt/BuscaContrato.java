package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BuscaContrato extends AppCompatActivity {

    DBHelper db = new DBHelper(this);
    ListView listViewBusca;
    List<Contrato> contratos;
    EditText editBusca;


    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_contrato);

        listViewBusca = findViewById(R.id.listBusca);
        editBusca = findViewById(R.id.editBusca);

        getSupportActionBar().hide();
        DBHelper db = new DBHelper(this);
        //Buscar();

    }

    public void Buscar(View v){

        String nome = editBusca.getText().toString();

        contratos = db.buscaContrato(nome);

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(BuscaContrato.this, android.R.layout.simple_list_item_1, arrayList);

        listViewBusca.setAdapter(adapter);

        for(Contrato c  : contratos){

            arrayList.add(c.getCodCon() + " - " + c.getNomeCon() + " - Entrega: " + c.getDFCon() );
            adapter.notifyDataSetChanged();
        }
    }
}