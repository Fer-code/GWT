package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadCliente extends AppCompatActivity {

    private final String CLI_NOME = "com.example.gwt.NOMECI";
    private final String CLI_EMAIL = "com.example.gwt.EMAILCI";
    private final String CLI_TEL = "com.example.gwt.TELCI";
    private final String CLI_END = "com.example.gwt.ENDCI";
    private final String CLI_CPF = "com.example.gwt.CPFCI";

    EditText editCodigo, editNome, editTelefone, editEmail, editEnd, editCPF;
    Button btnLimpar, btnSalvar, btnExcluir;
    ListView listViewClientes;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);

        getSupportActionBar().hide();

        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editNome = (EditText) findViewById(R.id.editNome);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editEnd = (EditText) findViewById(R.id.editEnd);
        editCPF = (EditText) findViewById(R.id.editCPF);

        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        listViewClientes = (ListView) findViewById(R.id.ListViewClientes);

        ListarClientes();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LimpaCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCodigo.getText().toString();
                String nome = editNome.getText().toString();
                String telefone = editTelefone.getText().toString();
                String email = editEmail.getText().toString();
                String end = editEnd.getText().toString();
                String cpf = editCPF.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || end.isEmpty() || cpf.isEmpty()) {
                    editNome.setError("Campo obrigatorio");
                    editEmail.setError("Campo obrigatorio");
                    editEnd.setError("Campo obrigatorio");
                    editTelefone.setError("Campo obrigatorio");
                    editCPF.setError("Campo obrigatorio");
                }
                else if (codigo.isEmpty()) {
                    //insert
                    db.addCliente(new Cliente(nome, email, telefone, end, Integer.parseInt(cpf)));
                    Toast.makeText(CadCliente.this, "Cliente adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    ListarClientes();
                    LimpaCampos();

                } else {
                    db.atualizaCliente(new Cliente(Integer.parseInt(codigo), nome, email, telefone, end, Integer.parseInt(cpf)));

                    Toast.makeText(CadCliente.this, "Cliente atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    ListarClientes();
                    LimpaCampos();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCodigo.getText().toString();

                if (codigo.isEmpty()) {
                    Toast.makeText(CadCliente.this, "Nenhum cliente selecionado", Toast.LENGTH_SHORT).show();
                } else {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(Integer.parseInt(codigo));
                    db.apagarCliente(cliente);

                    Toast.makeText(CadCliente.this, "Cliente excluido com sucesso", Toast.LENGTH_SHORT).show();
                    ListarClientes();
                    LimpaCampos();
                }
            }
        });


        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) listViewClientes.getItemAtPosition(position);

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));
                Cliente cliente = db.selecionarCliente(Integer.parseInt(codigo));

                editCodigo.setText(String.valueOf(cliente.getCodigo()));
                editNome.setText(cliente.getNome());
                editTelefone.setText(cliente.getTelefone());
                editEmail.setText(cliente.getEmail());
                editEnd.setText(cliente.getEnd());
                editCPF.setText(String.valueOf(cliente.getCpf()));

            }
        });
    }
        void LimpaCampos () {
            editCodigo.setText("");
            editNome.setText("");
            editTelefone.setText("");
            editEmail.setText("");
            editEnd.setText("");
            editCPF.setText("");

            editNome.requestFocus();
        }

        public void ListarClientes () {
            List<Cliente> clientes = db.listaTodosClientes();

            arrayList = new ArrayList<String>();

            adapter = new ArrayAdapter<String>(CadCliente.this, android.R.layout.simple_list_item_1, arrayList);

            listViewClientes.setAdapter(adapter);

            for (Cliente c : clientes) {
                //Log.d("Lista", "\nID: " + c.getCodigo() + " Nome: " + c.getNome());

                arrayList.add(c.getCodigo() +  "-" + c.getNome());
                adapter.notifyDataSetChanged();
            }


        }


        //SAVEINSTANCE

    @Override
    public void onSaveInstanceState(Bundle saveInstance) {
        super.onSaveInstanceState(saveInstance);
        saveInstance.putString(CLI_NOME, editNome.getText().toString());
        saveInstance.putString(CLI_EMAIL, editEmail.getText().toString());
        saveInstance.putString(CLI_TEL, editTelefone.getText().toString());
        saveInstance.putString(CLI_END, editEnd.getText().toString());
        saveInstance.putString(CLI_CPF, editCPF.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        super.onRestoreInstanceState(savedInstance);
        String clinomeRecuperado = savedInstance.getString(CLI_NOME);
        String cliemailRecuperado = savedInstance.getString(CLI_EMAIL);
        String clitellRecuperado = savedInstance.getString(CLI_TEL);
        String cliendRecuperado = savedInstance.getString(CLI_END);
        String clicpfRecuperado = savedInstance.getString(CLI_CPF);

        editNome.setText(clinomeRecuperado);
        editEmail.setText(cliemailRecuperado);
        editTelefone.setText(clitellRecuperado);
        editEnd.setText(cliendRecuperado);
        editCPF.setText(clicpfRecuperado);
    }
}
