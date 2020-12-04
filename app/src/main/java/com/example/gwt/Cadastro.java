package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    private final String CADASTRO_NOME = "com.example.gwt.NOMEC";
    private final String CADASTRO_EMAIL = "com.example.gwt.EMAILC";
    private final String CADASTRO_TEL = "com.example.gwt.TELC";
    private final String CADASTRO_SENHA = "com.example.gwt.SENHAC";
    private final String CADASTRO_CSENHA = "com.example.gwt.CSENHAC";

    EditText nome;
    EditText email;
    EditText tel;
    EditText senha;
    EditText confSenha;
    Button confi;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().hide();

        nome = findViewById(R.id.NomeCad);
        email = findViewById(R.id.emailCad);
        tel = findViewById(R.id.TelCad);
        senha = findViewById(R.id.SenhaCad);
        confSenha = findViewById(R.id.ConfSenhaCad);
        confi = findViewById(R.id.btnconf);

        confi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome1 = nome.getText().toString();
                String telefone = tel.getText().toString();
                String email1 = email.getText().toString();
                String senha1 = senha.getText().toString();
                String consenha1 = confSenha.getText().toString();

                if(senha1.isEmpty() || nome1.isEmpty() ||  email1.isEmpty() || telefone.isEmpty() || consenha1.isEmpty() ){
                    Toast.makeText(Cadastro.this, "Insira os dados corretamente", Toast.LENGTH_SHORT).show();
                }
                else if  (!senha1.equals(consenha1)){
                    Toast.makeText(Cadastro.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();
                }
                else if (db.Validacaoemail(email1)) {
                    Toast.makeText(Cadastro.this, "Email já utilizado", Toast.LENGTH_SHORT).show();

                }
                else if (db.ValidacaoNome(nome1)) {
                    Toast.makeText(Cadastro.this, "Nome já utilizado", Toast.LENGTH_SHORT).show();

                }
                else {
                    db.addUsuario(new Usuario(nome1, email1,telefone, senha1));
                    Toast.makeText(Cadastro.this, "adicionado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Cadastro.this, MainActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        });
    }

    public void Conf(View c){
        Intent intent = new Intent(Cadastro.this, MainActivity.class);
        startActivity(intent);

    }


    //~~~~~~~~~~~~~~~~~~~~SAVEINSTANCE~~~~~~~~~~~~~~~~~~


    @Override
    public void onSaveInstanceState(Bundle saveInstance) {
        super.onSaveInstanceState(saveInstance);
        saveInstance.putString(CADASTRO_NOME, nome.getText().toString());
        saveInstance.putString(CADASTRO_EMAIL, email.getText().toString());
        saveInstance.putString(CADASTRO_TEL, tel.getText().toString());
        saveInstance.putString(CADASTRO_SENHA, senha.getText().toString());
        saveInstance.putString(CADASTRO_CSENHA, confSenha.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        super.onRestoreInstanceState(savedInstance);
        String nomeRecuperado = savedInstance.getString(CADASTRO_NOME);
        String emailRecuperado = savedInstance.getString(CADASTRO_EMAIL);
        String tellRecuperado = savedInstance.getString(CADASTRO_TEL);
        String senhaRecuperado = savedInstance.getString(CADASTRO_SENHA);
        String csenhaRecuperado = savedInstance.getString(CADASTRO_CSENHA);

        nome.setText(nomeRecuperado);
        email.setText(emailRecuperado);
        tel.setText(tellRecuperado);
        senha.setText(senhaRecuperado);
        confSenha.setText(csenhaRecuperado);
    }

}