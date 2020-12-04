package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CadContrato extends AppCompatActivity {

    private final String CON_NOME = "com.example.gwt.NOMECO";
    private final String CON_DI = "com.example.gwt.DICO";
    private final String CON_DF = "com.example.gwt.DFCO";
    private final String CON_CLI = "com.example.gwt.CONCLI";
    private final String CON_VALOR = "com.example.gwt.VALORCO";
    private final String CON_DESC = "com.example.gwt.DESCCO";

    int id_To_Update = 0;
    CheckBox Banco, mobile, infra, sistema, site;
    Button btnSalvar, btnExcluir;
    EditText nome, DataI, DataF, CodCli, valor, desc;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_contrato);

        getSupportActionBar().hide();

        Banco = findViewById(R.id.ckBanco);
        mobile = findViewById(R.id.ckMobile);
        infra = findViewById(R.id.ckInfra);
        sistema = findViewById(R.id.ckSistema);
        site = findViewById(R.id.ckSite);

        btnSalvar = findViewById(R.id.btnSalvarContrato);
        btnExcluir = findViewById(R.id.btnExcluirContrato);

        nome = findViewById(R.id.editNomeCont);
        DataI = findViewById(R.id.editDICont);
        DataF = findViewById(R.id.editDFCont);
        CodCli = findViewById(R.id.editCliCont);
        valor = findViewById(R.id.editValorCont);
        desc = findViewById(R.id.editDescCont);


    }

    public void teste (View teste) {


        String gostos = "";

        if (Banco.isChecked()) {
            gostos = "Banco de dados,";
        }
        if (mobile.isChecked()) {
            gostos += " Mobile,";
        }
        if (infra.isChecked()) {
            gostos += " Infra,";
        }
        if (sistema.isChecked()) {
            gostos += " Sistema,";
        }
        if (site.isChecked()) {
            gostos += " Site,";
        }

        String nomeC = nome.getText().toString();
        String DataInicio = DataI.getText().toString();
        String DataFinal = DataF.getText().toString();
        String CodCliC = CodCli.getText().toString();
        String valorC = valor.getText().toString();
        String descC = desc.getText().toString();

        if (gostos.isEmpty() || nomeC.isEmpty() || DataInicio.isEmpty() ||
                DataFinal.isEmpty() || CodCliC.isEmpty() || valorC.isEmpty() || descC.isEmpty()) {
            Toast.makeText(this, "Insira os dados corretamente", Toast.LENGTH_SHORT).show();
        }
        else{
            db.addContrato(new Contrato(nomeC, descC, Double.parseDouble(valorC), DataInicio, DataFinal, gostos, Integer.parseInt(CodCliC)));
            Toast.makeText(CadContrato.this, "Tudo certo", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, VisContrato.class);
            startActivity(intent);
            finish();
        }
    }





    //~~~~~~~~~~~~~~~~~~~SAVEINSTANCE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    @Override
    public void onSaveInstanceState(Bundle saveInstance) {
        super.onSaveInstanceState(saveInstance);
        saveInstance.putString(CON_NOME, nome.getText().toString());
        saveInstance.putString(CON_DI, DataI.getText().toString());
        saveInstance.putString(CON_DF, DataF.getText().toString());
        saveInstance.putString(CON_CLI, CodCli.getText().toString());
        saveInstance.putString(CON_VALOR, valor.getText().toString());
        saveInstance.putString(CON_DESC, desc.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        super.onRestoreInstanceState(savedInstance);
        String CONnomeRecuperado = savedInstance.getString(CON_NOME);
        String CONDIRecuperado = savedInstance.getString(CON_DI);
        String CONDFRecuperado = savedInstance.getString(CON_DF);
        String CONCLIRecuperado = savedInstance.getString(CON_CLI);
        String CONVRecuperado = savedInstance.getString(CON_VALOR);
        String CONDESCRecuperado = savedInstance.getString(CON_DESC);


        nome.setText(CONnomeRecuperado);
        DataI.setText(CONDIRecuperado);
        DataF.setText(CONDFRecuperado);
        CodCli.setText(CONCLIRecuperado);
        valor.setText(CONVRecuperado);
        desc.setText(CONDESCRecuperado);
    }



}

