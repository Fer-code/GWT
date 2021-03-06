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

public class AEContrato extends AppCompatActivity {

    int id_To_Update = 0;
    Contrato contrato;
    Button btnAtualizar, btnExcluir;
    EditText nome, DataI, DataF, CodCli, valor, desc, serv;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_e_contrato);

        getSupportActionBar().hide();

        btnAtualizar = findViewById(R.id.btnSalvarContrato);
        btnExcluir = findViewById(R.id.btnExcluirContrato);

        nome = findViewById(R.id.editNomeCont);
        DataI = findViewById(R.id.editDICont);
        DataF = findViewById(R.id.editDFCont);
        CodCli = findViewById(R.id.editCliCont);
        valor = findViewById(R.id.editValorCont);
        serv = findViewById(R.id.editServCont);
        desc = findViewById(R.id.editDescCont);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            contrato = new Contrato();

            if (Value > 0) {

                Cursor rs = db.getData(Value);
                id_To_Update = Value;
                if  (rs.moveToFirst()) {
                    contrato.setNomeCon(rs.getString(rs.getColumnIndex(db.CONTRATO_COLUMN_NOME)));
                    contrato.setDICon(rs.getString(rs.getColumnIndex(db.CONTRATO_COLUMN_DI)));
                    contrato.setDFCon(rs.getString(rs.getColumnIndex(db.CONTRATO_COLUMN_DF)));
                    contrato.setFkCli(rs.getInt(rs.getColumnIndex(db.CONTRATO_COLUMN_CLIENTE)));
                    contrato.setValorCon(rs.getDouble(rs.getColumnIndex(db.CONTRATO_COLUMN_VALOR)));
                    contrato.setServCon(rs.getString(rs.getColumnIndex(db.CONTRATO_COLUMN_SERVICO)));
                    contrato.setDescCon(rs.getString(rs.getColumnIndex(db.CONTRATO_COLUMN_DESC)));
                }

            }
            else{
                Toast.makeText(AEContrato.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
            }

            nome.setText(contrato.getNomeCon());

            DataI.setText(contrato.getDICon());

            DataF.setText(contrato.getDFCon());

            CodCli.setText(Integer.toString(contrato.getFkCli()));

            valor.setText(Double.toString(contrato.getValorCon()));

            serv.setText(contrato.getServCon());

            desc.setText(contrato.getDescCon());

        }

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.apagarContato(id_To_Update);

                Toast.makeText(AEContrato.this, "Contrato excluido com sucesso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),VisContrato.class);
                startActivity(intent);

                finish();
            }
        });
    }

    public void atualiza(View a) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){

                try {
                    db.atualizaContrato(new Contrato(id_To_Update,
                            nome.getText().toString(),
                            desc.getText().toString(),
                            Double.parseDouble(valor.getText().toString()),
                            DataI.getText().toString(),
                            DataF.getText().toString(),
                            serv.getText().toString(),
                            Integer.parseInt(CodCli.getText().toString())));

                    Toast.makeText(getApplicationContext(), "Atualizado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), VisContrato.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Código Cliente inválido", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}