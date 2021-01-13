package com.example.taptitan2projettp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static Editable Retournom;
    public static String nom;
    Intent Jeux;
    TextView TapTitre;
    //String nom;
    Button valide;
    String NomJeux;
    EditText lieuxnom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Jeux = new Intent(getApplicationContext(),LesMecaniques.class);
        TapTitre =findViewById(R.id.LeNom);

        TapTitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Jeux);
            }
        });
        valide = findViewById(R.id.button2);
        lieuxnom = findViewById(R.id.editTextTextPersonName);
        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retournom = lieuxnom.getText();
                nom = String.valueOf(Retournom);
                startActivity(Jeux);
            }
        });

    }

}