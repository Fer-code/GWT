package com.example.gwt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gwt";
    public static final int DATABASE_VERSION = 1;

    //TABELA SÓCIO
    public static final String USUARIO_TABLE_NAME = "TBUsuario";
    public static final String USUARIO_COLUMN_ID = "idUsuario";
    public static final String USUARIO_COLUMN_NAME = "nomeUsuario";
    public static final String USUARIO_COLUMN_EMAIL = "emailUsuario";
    public static final String USUARIO_COLUMN_TEL = "telUsuario";
    public static final String USUARIO_COLUMN_SENHA = "senhaUsuario";

    //TABELA CLIENTE
    public static final String CLIENTE_TABLE_NAME = "TBcliente";
    public static final String CLIENTE_COLUMN_ID = "idCli";
    public static final String CLIENTE_COLUMN_NAME = "nomeCli";
    public static final String CLIENTE_COLUMN_EMAIL = "emailCli";
    public static final String CLIENTE_COLUMN_TEL = "telCli";
    public static final String CLIENTE_COLUMN_END = "endCli";
    public static final String CLIENTE_COLUMN_CPF = "cpfCli";

    //TABELA CONTRATO
    public static final String CONTRATO_TABLE_NAME = "TBcontrato";
    public static final String CONTRATO_COLUMN_ID = "idContrato";
    public static final String CONTRATO_COLUMN_NOME = "nomeContrato";
    public static final String CONTRATO_COLUMN_DESC = "descContrato";
    public static final String CONTRATO_COLUMN_VALOR = "valorContrato";
    public static final String CONTRATO_COLUMN_DI = "DIContrato";
    public static final String CONTRATO_COLUMN_DF = "DFContrato";
    public static final String CONTRATO_COLUMN_SERVICO = "ServContrato";
    public static final String CONTRATO_COLUMN_CLIENTE= "FKidCli";

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_USUARIO = "CREATE TABLE TBUsuario ( idUsuario INTEGER PRIMARY KEY, nomeUsuario  TEXT, " +
                "emailUsuario  TEXT,  telUsuario TEXT, senhaUsuario TEXT); ";



        String QUERY_CLIENTE = "CREATE TABLE TBcliente ( idCli INTEGER PRIMARY KEY, nomeCli TEXT, " +
               " emailCli  TEXT, telCli TEXT, endCli TEXT, cpfCli INTEGER);";



        String QUERY_CONTRATO = "CREATE TABLE " + CONTRATO_TABLE_NAME + "( " +
                CONTRATO_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                CONTRATO_COLUMN_NOME + " TEXT, " +
                CONTRATO_COLUMN_DESC + " TEXT, " +
                CONTRATO_COLUMN_VALOR + " DOUBLE, " +
                CONTRATO_COLUMN_DI + " TEXT, " +
                CONTRATO_COLUMN_DF + " TEXT, " +
                CONTRATO_COLUMN_SERVICO  + " TEXT, "+
                CONTRATO_COLUMN_CLIENTE +" INTEGER, " +
                " foreign key (" + CONTRATO_COLUMN_CLIENTE +" ) references "  + CLIENTE_TABLE_NAME + "(" + CLIENTE_COLUMN_ID + "));";

        db.execSQL(QUERY_USUARIO);
        db.execSQL(QUERY_CLIENTE);
        db.execSQL(QUERY_CONTRATO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTE_TABLE_NAME + ";" );
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLE_NAME + ";" );
        db.execSQL("DROP TABLE IF EXISTS " + CONTRATO_TABLE_NAME + ";" );
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    void addUsuario (Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USUARIO_COLUMN_NAME, usuario.getNome());
        values.put(USUARIO_COLUMN_EMAIL, usuario.getEmail());
        values.put(USUARIO_COLUMN_TEL, usuario.getTelefone());
        values.put(USUARIO_COLUMN_SENHA, usuario.getSenha());

        db.insert(USUARIO_TABLE_NAME, null, values);
        db.close();
    }

    public boolean autenticaUsuario(Usuario usuario){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql_busca_usuario =
                "SELECT * FROM " + USUARIO_TABLE_NAME + " WHERE " + USUARIO_COLUMN_NAME + " = " + "'" + usuario.getNome() + "'";
        Cursor c = db.rawQuery(sql_busca_usuario, null);
        while(c.moveToNext()){
            if(usuario.getNome().equals(c.getString(c.getColumnIndex(USUARIO_COLUMN_NAME)))){
                if(usuario.getSenha().equals(c.getString(c.getColumnIndex(USUARIO_COLUMN_SENHA)))){
                    return true;
                }

            }
        }
        db.close();
        c.close();

        return false;
    }

    public  boolean Validacaoemail(String string){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT ? FROM TBUsuario WHERE emailUsuario =?", new String[]{string,string});
        if(c.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public  boolean ValidacaoNome(String string){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT ? FROM TBUsuario WHERE nomeUsuario =?", new String[]{string,string});
        if(c.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Cursor getD(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from TBUsuario where idUsuario="+id+"", null );
        return res;
    }

    //CLIENTE

    void addCliente (Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CLIENTE_COLUMN_NAME, cliente.getNome());
        values.put(CLIENTE_COLUMN_EMAIL, cliente.getEmail());
        values.put(CLIENTE_COLUMN_TEL, cliente.getTelefone());
        values.put(CLIENTE_COLUMN_END, cliente.getEnd());
        values.put(CLIENTE_COLUMN_CPF, cliente.getCpf());

        db.insert(CLIENTE_TABLE_NAME, null, values);
        db.close();
    }

    void apagarCliente (Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(CLIENTE_TABLE_NAME, CLIENTE_COLUMN_ID + " = ?" , new String[]{String.valueOf(cliente.getCodigo())} );
        db.close();

    }

    Cliente selecionarCliente(int codigo){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CLIENTE_TABLE_NAME, new String[]{CLIENTE_COLUMN_ID,
                        CLIENTE_COLUMN_NAME, CLIENTE_COLUMN_EMAIL, CLIENTE_COLUMN_TEL, 
                CLIENTE_COLUMN_END, CLIENTE_COLUMN_CPF}, CLIENTE_COLUMN_ID + " = ?",
                new String[] {String.valueOf(codigo)}, null, null, null, null);


        if(cursor != null){
            cursor.moveToFirst();
        }

        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5));

        return cliente;
    }

    void atualizaCliente (Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(CLIENTE_COLUMN_NAME, cliente.getNome());
        values.put(CLIENTE_COLUMN_EMAIL, cliente.getEmail());
        values.put(CLIENTE_COLUMN_TEL, cliente.getTelefone());
        values.put(CLIENTE_COLUMN_END, cliente.getEnd());
        values.put(CLIENTE_COLUMN_CPF, cliente.getCpf());

        db.update(CLIENTE_TABLE_NAME, values, CLIENTE_COLUMN_ID + " = ?",
                new String[] {String.valueOf(cliente.getCodigo())});
        db.close();
    }

    public List<Cliente> listaTodosClientes (){
        List<Cliente> listaClientes    = new ArrayList<Cliente>();
        String query = "SELECT * FROM " + CLIENTE_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Cliente cliente = new Cliente();
                cliente.setCodigo(Integer.parseInt(c.getString(0)));
                cliente.setNome(c.getString(1));
                cliente.setTelefone(c.getString(2));
                cliente.setEmail(c.getString(3));
                cliente.setEnd(c.getString(4));
                cliente.setCpf(c.getString(5));

                listaClientes.add(cliente);
            }while(c.moveToNext());
        }
        return  listaClientes;
    }

    //CONTRATO


    void addContrato (Contrato contrato){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CONTRATO_COLUMN_NOME, contrato.getNomeCon());
        values.put(CONTRATO_COLUMN_DESC ,  contrato.getDescCon());
        values.put(CONTRATO_COLUMN_VALOR, contrato.getValorCon());
        values.put(CONTRATO_COLUMN_DI, contrato.getDICon());
        values.put(CONTRATO_COLUMN_DF, contrato.getDFCon());
        values.put(CONTRATO_COLUMN_SERVICO, contrato.getServCon());
        values.put(CONTRATO_COLUMN_CLIENTE, contrato.getFkCli());

        db.insert(CONTRATO_TABLE_NAME, null, values);
        db.close();
    }

    void atualizaContrato (Contrato contrato){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CONTRATO_COLUMN_NOME, contrato.getNomeCon());
        values.put(CONTRATO_COLUMN_DESC ,  contrato.getDescCon());
        values.put(CONTRATO_COLUMN_VALOR, contrato.getValorCon());
        values.put(CONTRATO_COLUMN_DI, contrato.getDICon());
        values.put(CONTRATO_COLUMN_DF, contrato.getDFCon());
        values.put(CONTRATO_COLUMN_SERVICO, contrato.getServCon());
        values.put(CONTRATO_COLUMN_CLIENTE, contrato.getFkCli());

        db.update(CONTRATO_TABLE_NAME, values, CONTRATO_COLUMN_ID + " = ?",
                new String[] {String.valueOf(contrato.getCodCon())});
        db.close();
    }

    public Integer apagarContato (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CONTRATO_TABLE_NAME,
                "idContrato = ? ",
                new String[] { Integer.toString(id) });
    }

    Contrato selecionarContrato(int codigo){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CONTRATO_TABLE_NAME, new String[]{CONTRATO_COLUMN_ID,
                        CONTRATO_COLUMN_NOME, CONTRATO_COLUMN_DESC, CONTRATO_COLUMN_VALOR,
                        CONTRATO_COLUMN_DI, CONTRATO_COLUMN_DF, CONTRATO_COLUMN_SERVICO,  CONTRATO_COLUMN_CLIENTE }, CONTRATO_COLUMN_ID + " = ?",
                new String[] {String.valueOf(codigo)}, null, null, null, null);


        if(cursor != null){
            cursor.moveToFirst();
        }

        Contrato contrato = new Contrato(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Double.parseDouble(cursor.getString(3)),
                cursor.getString(4), cursor.getString(5) , cursor.getString(6),
                Integer.parseInt(cursor.getString(8)));

        return contrato;
    }

    public List<Contrato> listaTodosContratos (){
        List<Contrato> listaContrato    = new ArrayList<Contrato>();
        String query = "SELECT * FROM " + CONTRATO_TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Contrato contrato = new Contrato();
                contrato.setCodCon(Integer.parseInt(c.getString(0)));
                contrato.setNomeCon(c.getString(1));
                contrato.setDescCon(c.getString(2));
                contrato.setValorCon(Double.parseDouble(c.getString(3)));
                contrato.setDICon(c.getString(4));
                contrato.setDFCon(c.getString(5));
                contrato.setServCon(c.getString(6));
                contrato.setFkCli(Integer.parseInt(c.getString(7)));

                listaContrato.add(contrato);
            }while(c.moveToNext());
        }
        return  listaContrato;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from TBcontrato where idContrato="+id+"", null );
        return res;
    }

    public int pegaCod (String nome){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USUARIO_TABLE_NAME, new String[]{USUARIO_COLUMN_ID},
                USUARIO_COLUMN_NAME + " = ?" ,
                new String[] {String.valueOf(nome)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        int cod = cursor.getInt(0);

        cursor.close();

        return cod;

    }

    public List buscaContrato(String nome){
            String sql_busca_contratos = "SELECT * FROM TBcontrato WHERE nomeContrato = " + "'" + nome + "'";
            SQLiteDatabase db = getReadableDatabase();

            Cursor c = db.rawQuery(sql_busca_contratos, null);

            List<Contrato> contratos = new ArrayList<>();

            while(c.moveToNext()){

                    Contrato contrato = new Contrato();
                    contrato.setNomeCon(c.getString(c.getColumnIndex("nomeContrato")));
                    contrato.setCodCon(c.getInt(c.getColumnIndex("idContrato")));
                    contrato.setDFCon(c.getString(c.getColumnIndex("DFContrato")));

                contratos.add(contrato);
            }
            db.close();
            c.close();

        return contratos;
    }

}
