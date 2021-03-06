package com.example.gwt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        getSupportActionBar().hide();

    }

    public void verMapa(View mapa)
    {
            Uri location= Uri.parse("geo:0,0?q=R.+dos+Pinheiros,+1000+-+Pinheiros,+São+Paulo+-+SP,+05422-001");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
            startActivity(mapIntent );
    }
    public void enviarEmail(View view) throws UnsupportedEncodingException {

        String uriText =
                "mailto:widetechglobal@gmail.com" +
                        "?subject=" + URLEncoder.encode("Assunto", "utf-8") +
                        "&body=" + URLEncoder.encode("Desenvolva", "utf-8");
        Uri uri = Uri.parse(uriText);
        Intent it = new Intent(Intent.ACTION_SENDTO);
        it.setData(uri);
        startActivity(Intent.createChooser(it, "Email"));
    }

    public void fazerChamada(View view)
    {
        Uri uri = Uri.parse("tel:1145866383");
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);

    }
}