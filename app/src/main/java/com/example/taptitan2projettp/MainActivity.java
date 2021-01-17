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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseHelper mDatabaseHelper;

    public static Editable Retournom;
    public static String nom;
    Intent Jeux;
    TextView TapTitre;
    //String nom;
    Button valide,btnGuild;
    String NomJeux;
    EditText lieuxnom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnGuild = (Button) findViewById(R.id.buttonGuild);
        Jeux = new Intent(getApplicationContext(),LesMecaniques.class);
        TapTitre =findViewById(R.id.LeNom);
        mDatabaseHelper = new DatabaseHelper(this);

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
                String newEntry = lieuxnom.getText().toString();
                if (lieuxnom.length() != 0) {
                    AddData(newEntry);
                    lieuxnom.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
                startActivity(Jeux);
            }
        });




    }
    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}