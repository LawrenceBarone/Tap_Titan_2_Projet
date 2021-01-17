package com.example.taptitan2projettp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LesMecaniques extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    MediaPlayer mediaPlayer;
    private static int puissance,PuissancRecu;
    public static int compteur;
    ImageView tap,Amelioration;
    TextView info,Gold,Force,nom,ScorePerso,HightScore;
    RelativeLayout test, PartiJeux;
    LinearLayout parti_amelioration,parti_Guild;
    ProgressBar pv;
    boolean Thomas;
    int Maxvie,vie,MonsteActuelle = 0,Score = 0,UpVie=50,ScoreMonde,FondAcutelle = 1,ChangerFond=10,Recompence = 600;//,puissance,compteur
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Mecanique adapter;
    Button ouvrir_fermer,guild;
    boolean on,on_Guild;
    public String ListNomArme[]=
            {
              "epee en bois", "epee en pierre","epee en fer","epee en or","epee en diamant"
            };
    public String ListPuissanceArme[]=
            {
                    "10", "30","60","90","150"
            };
    public String ListPrixArme[]=
            {
                    "1000", "3000","6000","9000","15000"
            };
    public int ImageArme[]=
            {
                    R.drawable.epeebois, R.drawable.sword_stone,R.drawable.epeefer,R.drawable.epeeor,R.drawable.unnamed
            };
    public  int MonstreImage[]=
            {
                    R.drawable.monstre_a,R.drawable.monstre_b,R.drawable.monstre_c,R.drawable.monstre_d,
                    R.drawable.monstre_e,R.drawable.monstre_f

            };
    public int Fond[]=
            {
                    R.drawable.b,R.drawable.b,R.drawable.fond1,R.drawable.fond2,R.drawable.fond4,R.drawable.fond3
            };

    public static void changer_puissance(int test) {
        PuissancRecu = test;
    }
    public static int changer_argent(int rest) {

        compteur = compteur - rest;

        return compteur;
    }
    String nomperso;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_les_mecaniques);

        View myView =  this.findViewById(R.id.Background);

        mDatabaseHelper = new DatabaseHelper(this);
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.musique);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        nomperso = MainActivity.nom;
        nom = findViewById(R.id.nom);
        nom.setText(nomperso);
        tap = findViewById(R.id.image);
        Amelioration = findViewById(R.id.imageArme);
        info = findViewById(R.id.Textvieuw);
        Gold = findViewById(R.id.argent);
        Force = findViewById(R.id.ForcePerso);
        test = findViewById(R.id.linear);
        pv = findViewById(R.id.progressBar);
        ouvrir_fermer = findViewById(R.id.button);
        parti_amelioration = (LinearLayout) findViewById(R.id.linearRecycle);
        ScorePerso = findViewById(R.id.Classement_perso);
        HightScore = findViewById(R.id.Classement);
        PartiJeux = findViewById(R.id.Background);
        guild = findViewById(R.id.buttonGuild);
        parti_Guild = findViewById(R.id.Guild);
        tap.setImageResource(MonstreImage[MonsteActuelle]);
        compteur = 900;
        Maxvie = 100;
        vie = Maxvie;
        pv.setMax(Maxvie);
        pv.setProgress(vie);
        puissance = 1;
        on = false;
        on_Guild = false;
        Force.setText("Force: " + String.valueOf(puissance));
        ScoreMonde = 0;

        Handler mHandler = new Handler();

        guild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LesMecaniques.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (vie-puissance >=0)
                {
                    //vie= vie-puissance;
                    pv.setProgress(vie);

                }
                else
                {
                    Maxvie = Maxvie+UpVie;
                    vie = Maxvie;
                    pv.setMax(Maxvie);
                    pv.setProgress(vie);
                    //puissance = puissance+10;
                    compteur = compteur+Recompence;
                    //Toast.makeText(LesMecaniques.this,"Montre Vaincu", Toast.LENGTH_SHORT).show();
                    tuer();
                    Score++;
                    if(Score %10 == 0)
                    {
                        UpVie = UpVie*10;
                        Recompence = Recompence*3;
                    }
                    if(Score >= ScoreMonde)
                    {
                        ScoreMonde = Score;
                    }
                    //Log.i("Monster","Score Monde: "+ String.valueOf(ScoreMonde));
                }
                guild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LesMecaniques.this, ListDataActivity.class);
                        startActivity(intent);
                    }
                });
                payer();
                mDatabaseHelper.addInfo(puissance,Score,compteur);
                info.setText("Vie: " + String.valueOf(vie));
                Gold.setText("Gold: " + String.valueOf(compteur));
                Force.setText("Force: " + String.valueOf(puissance));
                ScorePerso.setText("Classement perso: " + String.valueOf(Score));
                HightScore.setText("Classement: " + String.valueOf(ScoreMonde));
                if(Score %10 == 0)
                {
                    tap.setImageResource(R.drawable.a);
                }
                else
                {
                    tap.setImageResource(MonstreImage[MonsteActuelle]);
                }
                ChangerFond();
                myView.setBackgroundResource(Fond[FondAcutelle]);
                mHandler.postDelayed(this,1);
            }
        });
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vie= vie-puissance;
                mHandler.postDelayed(this,1000);
            }
        });


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    vie= vie-puissance;
                    info.setText("Vie: " + String.valueOf(vie));
            }
        });


        // Recycler
        recyclerView = (RecyclerView) findViewById(R.id.Recycler);
        Log.i("test","debut");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i("test","debut1");
        adapter = new Mecanique(this,ListNomArme,ListPuissanceArme,ListPrixArme,ImageArme,puissance,compteur);
        Log.i("test","debut2");
        recyclerView.setHasFixedSize(true); // Je m'assure que chaque ligne de ma liste a la même taille (exemple une image plus grande que l'autre ... AIe !)
        Log.i("test","debut4");
        recyclerView.setAdapter(adapter);
        Log.i("test","fin");
        //----------------
        final RelativeLayout.LayoutParams layoutparamsbas = (RelativeLayout.LayoutParams)parti_amelioration.getLayoutParams();
        final RelativeLayout.LayoutParams layoutparamsbutton = (RelativeLayout.LayoutParams)ouvrir_fermer.getLayoutParams();
        final RelativeLayout.LayoutParams layoutparamshaut = (RelativeLayout.LayoutParams)test.getLayoutParams();
        ouvrir_fermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(on)
                {

                    //Toast.makeText(LesMecaniques.this,"Amelioration", Toast.LENGTH_SHORT).show();
                    on = false;
                    layoutparamsbas.setMargins(0,5000,0,0);
                    layoutparamsbutton.setMargins(550,1000,0,0);
                    layoutparamshaut.height = 1100;
                }
                else
                {
                    //Toast.makeText(LesMecaniques.this,"Marche pas", Toast.LENGTH_SHORT).show();
                    on = true;
                    layoutparamsbas.setMargins(0,600,0,0);
                    layoutparamsbutton.setMargins(550,500,0,0);
                    layoutparamshaut.height = 600;
                }


                parti_amelioration.setLayoutParams(layoutparamsbas);
                ouvrir_fermer.setLayoutParams(layoutparamsbutton);
                test.setLayoutParams(layoutparamshaut);

            }
        });
        //final RelativeLayout.LayoutParams layoutparamsGuidl = (RelativeLayout.LayoutParams)parti_Guild.getLayoutParams();
        //final RelativeLayout.LayoutParams layoutparamsbuttonGuidl = (RelativeLayout.LayoutParams)guild.getLayoutParams();
        //guild.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
//
        //        if(on_Guild)
        //        {
//
        //            //Toast.makeText(LesMecaniques.this,"Amelioration", Toast.LENGTH_SHORT).show();
        //            on_Guild = false;
        //            layoutparamsGuidl.setMargins(0,0,0,0);
        //            layoutparamsbuttonGuidl.setMargins(550,0,0,0);
        //            layoutparamsGuidl.height = 1000;
        //            layoutparamshaut.setMargins(0,1100,0,0);
        //        }
        //        else
        //        {
        //            //Toast.makeText(LesMecaniques.this,"Marche pas", Toast.LENGTH_SHORT).show();
        //            on_Guild = true;
        //            layoutparamsGuidl.setMargins(0,5000,0,0);
        //            layoutparamsbuttonGuidl.setMargins(0,1000,0,0);
        //            layoutparamshaut.setMargins(0,0,0,0);
        //            //layoutparamsGuidl.height = 600;
        //        }
//
//
        //        parti_Guild.setLayoutParams(layoutparamsGuidl);
        //        guild.setLayoutParams(layoutparamsbuttonGuidl);
        //        test.setLayoutParams(layoutparamshaut);
//
//
        //    }
        //});


    }

    void tuer ()
    {

        if(MonsteActuelle+1 == MonstreImage.length)
        {
            MonsteActuelle = 0;
            //Log.i("La", "Recommence" ) ;
        }
        else
        {
            MonsteActuelle++;
            //Log.i("La", String.valueOf(MonstreImage.length)) ;
        }


    }
    void ChangerFond ()
    {

        if (Score == ChangerFond)
        {
            if (FondAcutelle + 1 == Fond.length)
            {
                FondAcutelle = 0;
                //Log.i("La", "Recommence" ) ;
            }
            else
            {
                FondAcutelle++;
                ChangerFond = ChangerFond +10;
                //Log.i("La", String.valueOf(MonstreImage.length)) ;
            }
        }


    }

    void payer()
    {
        //Log.i("Argent","1");
        //Log.i("Argent",String.valueOf(PuissancRecu));
        switch (PuissancRecu)
        {
            case 10:
                    if (compteur >= Integer.valueOf(ListPrixArme[0]))
                    {
                        compteur = compteur - Integer.valueOf(ListPrixArme[0]);
                        Log.i("Argent","Acheter bois");
                        puissance = puissance + PuissancRecu;

                    }
                    else
                    {
                        Log.i("Argent","Refuser bois");
                        Toast.makeText(LesMecaniques.this,"Pas assez d'argent", Toast.LENGTH_SHORT).show();
                    }
                break;
            case 30:
                if (compteur >= Integer.valueOf(ListPrixArme[1]))
                {
                    compteur = compteur - Integer.valueOf(ListPrixArme[1]);
                    Log.i("Argent","Acheter pierre");
                    puissance = puissance + PuissancRecu;

                }
                else
                {
                    Log.i("Argent","Refuser pierre");
                    Toast.makeText(LesMecaniques.this,"Pas assez d'argent", Toast.LENGTH_SHORT).show();
                }
                break;

            case 60:
                if (compteur >= Integer.valueOf(ListPrixArme[2]))
                {
                    compteur = compteur - Integer.valueOf(ListPrixArme[2]);
                    Log.i("Argent","Acheter fer");
                    puissance = puissance + PuissancRecu;

                }
                else
                {
                    Log.i("Argent","Refuser fer");
                    Toast.makeText(LesMecaniques.this,"Pas assez d'argent", Toast.LENGTH_SHORT).show();
                }
                break;

            case 90:
                if (compteur >= Integer.valueOf(ListPrixArme[3]))
                {
                    compteur = compteur - Integer.valueOf(ListPrixArme[3]);
                    Log.i("Argent","Acheter or");
                    puissance = puissance + PuissancRecu;

                }
                else
                {
                    Log.i("Argent","Refuser or");
                    Toast.makeText(LesMecaniques.this,"Pas assez d'argent", Toast.LENGTH_SHORT).show();
                }
                break;

            case 150:
                if (compteur >= Integer.valueOf(ListPrixArme[4]))
                {
                    compteur = compteur - Integer.valueOf(ListPrixArme[4]);
                    Log.i("Argent","Acheter diamant");
                    puissance = puissance + PuissancRecu;

                }
                else
                {
                    Log.i("Argent","Refuser diamant");
                    Toast.makeText(LesMecaniques.this,"Pas assez d'argent", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        PuissancRecu = 0;
    }


}
