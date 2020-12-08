package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class BuscaContrato extends AppCompatActivity {

    private final String BUSCA_NOME = "com.example.gwt.BUSCA";

    DBHelper db = new DBHelper(this);
    ListView listViewBusca;
    List<Contrato> contratos;
    EditText editBusca;
    TextView text_empty;

    InputMethodManager imm;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_contrato);

        listViewBusca = findViewById(R.id.listBusca);
        editBusca = findViewById(R.id.editBusca);
        text_empty = findViewById(R.id.textViewNoData);

        imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        getSupportActionBar().hide();
        DBHelper db = new DBHelper(this);

        listViewBusca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    public void Buscar(View v){

        String nome = editBusca.getText().toString();

        contratos = db.buscaContrato(nome);

        arrayList = new ArrayList<String>();

        if (contratos.isEmpty()) {
            text_empty.setVisibility(View.VISIBLE);


        } else {
            text_empty.setVisibility(View.GONE);


            adapter = new ArrayAdapter<String>(BuscaContrato.this, android.R.layout.simple_list_item_1, arrayList);

        listViewBusca.setAdapter(adapter);

        for(Contrato c  : contratos) {

            arrayList.add(c.getCodCon() + " - " + c.getNomeCon() + " - Entrega: " + c.getDFCon());
            adapter.notifyDataSetChanged();
        }
        }
        escodeTeclado();
    }

    void escodeTeclado(){
        imm.hideSoftInputFromWindow(editBusca.getWindowToken(),0);
    }

    //~~~~~~~~~~~~~~~~~~~SAVEINSTANCE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public void onSaveInstanceState(Bundle saveInstance) {
        super.onSaveInstanceState(saveInstance);
        saveInstance.putString(BUSCA_NOME, editBusca.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        super.onRestoreInstanceState(savedInstance);
        String BUSCAnomeRecuperado = savedInstance.getString(BUSCA_NOME);


        editBusca.setText(BUSCAnomeRecuperado);
    }
}