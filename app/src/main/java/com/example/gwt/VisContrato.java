package com.example.gwt;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VisContrato extends AppCompatActivity {

    ListView listViewContratos;
    List<Contrato> contratos;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    boolean position;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_contrato);

        listViewContratos = (ListView) findViewById(R.id.listViewContrato);

        ListarContrato();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(VisContrato.this,CadContrato.class);
                startActivity(it);
            }
        });

        listViewContratos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                
                Contrato c = new Contrato();

                int id_To_Search = contratos.get(position).codCon;


                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), AEContrato.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
                finish();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ListarContrato();
    }


    public void ListarContrato(){
        contratos = db.listaTodosContratos();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(VisContrato.this, android.R.layout.simple_list_item_1, arrayList);

        listViewContratos.setAdapter(adapter);

        for(Contrato c  : contratos){

         arrayList.add(c.getCodCon() + " - " + c.getNomeCon() + " - Entrega: " + c.getDFCon() );
            adapter.notifyDataSetChanged();
        }


    }


}