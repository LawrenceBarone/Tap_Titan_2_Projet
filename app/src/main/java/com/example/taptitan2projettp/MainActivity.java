package com.example.taptitan2projettp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView tap;
    TextView info,Gold;
    RelativeLayout test;
    ProgressBar pv;
    float Thomas;
    int compteur,vie,Maxvie,puissance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tap = findViewById(R.id.image);
        info = findViewById(R.id.Textvieuw);
        Gold = findViewById(R.id.argent);
        test = findViewById(R.id.linear);
        pv = findViewById(R.id.progressBar);
        compteur = 0;
        Maxvie = 100;
        vie = Maxvie;
        pv.setMax(Maxvie);
        pv.setProgress(vie);
        puissance = 1;




        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vie >=1)
                {
                    vie= vie-puissance;
                    pv.setProgress(vie);

                }
                else
                {
                    Maxvie = Maxvie+50;
                    vie = Maxvie;
                    pv.setMax(Maxvie);
                    pv.setProgress(vie);
                    puissance = puissance+10;
                    compteur = compteur+600;
                }
                info.setText("Vie: " + String.valueOf(vie));
                Gold.setText("Gold: " + String.valueOf(compteur));
            }
        });



    }

}