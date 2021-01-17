package com.example.taptitan2projettp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseHelper mDatabaseHelper;
    private static String DB_FULL_PATH = "/data/data/YOUR_PACKAGE/databases/";

    public static Editable Retournom;
    public static String nom;
    Intent Jeux;
    TextView TapTitre;
    //String nom;
    Button valide;
    String NomJeux;
    EditText lieuxnom;
    public static int test ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnGuild = (Button) findViewById(R.id.buttonGuild);
        Jeux = new Intent(getApplicationContext(),LesMecaniques.class);
        TapTitre =findViewById(R.id.LeNom);
        mDatabaseHelper = new DatabaseHelper(this);
        valide = findViewById(R.id.button2);
        lieuxnom = findViewById(R.id.editTextTextPersonName);


        test = 0;
        TapTitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Jeux);
            }
        });

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
    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }
    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        int userID = sessionManagement.getSession();

        if(userID != -1){
            //user id logged in and so move to mainActivity
            moveToMainActivity();
        }
        else{
            //do nothing
        }
    }

    public void login(View view) {
        // 1.log in to app and save session of user
        // 2. move to mainActivity

        //1. login and save session
        User user = new User(lieuxnom.getText().toString());
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        sessionManagement.saveSession(user);

        //2. step
        moveToMainActivity();
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(MainActivity.this, LesMecaniques.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private static boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            checkDB = SQLiteDatabase.openDatabase(DB_FULL_PATH,null,SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        }catch(SQLException e){

        }
        return checkDB != null;
    }
    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
        test = 1;
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}