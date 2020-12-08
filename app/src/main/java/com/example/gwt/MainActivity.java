package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_COD = "CODU";

    EditText nome, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
    }

    public void Conf(View c) {
         nome = findViewById(R.id.idNomeUsuario);
         senha = findViewById(R.id.idSenhaUsuario);

        String senha1 = senha.getText().toString();
        String nome1 = nome.getText().toString();

        if (senha1.isEmpty() || nome1.isEmpty()) {
            Toast.makeText(this, "Insira os dados corretamente", Toast.LENGTH_SHORT).show();
        } else {
            autenticaUsuario(nome1, senha1);
        }
    }


    private void autenticaUsuario (String nome, String senha){

        EditText nomes = findViewById(R.id.idNomeUsuario);
        EditText senhas = findViewById(R.id.idSenhaUsuario);

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        DBHelper db = new DBHelper(this);
        boolean resultado = db.autenticaUsuario(usuario);
        if(resultado == true){
            int codigo =  db.pegaCod(nome);
            String codi =  String.valueOf(codigo);

            Intent intent = new Intent(MainActivity.this, Menu.class);
            intent.putExtra(EXTRA_MESSAGE_COD, codi);

            startActivity(intent);
            finish();
        }else{
            nomes.setText("");
            senhas.setText("");
            nomes.requestFocus();
            Toast.makeText(this, "Usuario ou senha inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Cad(View ca){
        Intent intent = new Intent(MainActivity.this, Cadastro.class);
        startActivity(intent);
    }
}