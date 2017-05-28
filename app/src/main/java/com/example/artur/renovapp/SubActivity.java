package com.example.artur.renovapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by artur on 14/05/2017.
 * UNUFOR - Universidade de Fortaleza   CCT - Centro de Ciências Tecnológicas
 * Engenharia Eletrônica                Per:    2017.1
 * T923    Estrutura de Dados           Prof:   Gilson Pereira do Carmo Filho
 * Aluno:  Artur Alves Torres de Araujo Mat:    1610674
 */

public class SubActivity extends Activity {
    private StringBuilder text = new StringBuilder();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        ImageView recycle = (ImageView) findViewById(R.id.final_image);
        recycle.setImageDrawable(getResources().getDrawable(R.drawable.recycle_image_view));

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("rejeitos.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
            // Capture the layout's TextView and set the string as its text
            TextView output = (TextView) findViewById(R.id.disclaimer_view);
            output.setText(text);

        }

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

    }
}
