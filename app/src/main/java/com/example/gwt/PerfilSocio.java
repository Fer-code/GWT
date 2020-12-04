package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilSocio extends AppCompatActivity {
    String cp;
    TextView nomePerfil, emailPerfil, telPerfil;
    Usuario usuario;
    int id_To_Update = 0;

    DBHelper db = new DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_socio);

        getSupportActionBar().hide();



        nomePerfil = findViewById(R.id.nomePerfil);
        emailPerfil = findViewById(R.id.emailPerfil);
        telPerfil = findViewById(R.id.telPerfil);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            usuario = new Usuario();

            if (Value > 0) {

                Cursor rs = db.getD(Value);
                id_To_Update = Value;
                if (rs.moveToFirst()) {
                    usuario.setNome(rs.getString(rs.getColumnIndex(db.USUARIO_COLUMN_NAME)));
                    usuario.setEmail(rs.getString(rs.getColumnIndex(db.USUARIO_COLUMN_EMAIL)));
                    usuario.setTelefone(rs.getString(rs.getColumnIndex(db.USUARIO_COLUMN_TEL)));

                }

            } else {
                Toast.makeText(PerfilSocio.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
            }


            nomePerfil.setText(String.valueOf(usuario.getNome()));
            emailPerfil.setText(String.valueOf(usuario.getEmail()));
            telPerfil.setText(String.valueOf(usuario.getTelefone()));

        }
    }
    public void Info(View s){
        Intent intent = new Intent(PerfilSocio.this, Sobre.class);
        startActivity(intent);
    }
}