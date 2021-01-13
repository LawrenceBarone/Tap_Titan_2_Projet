package com.example.taptitan2projettp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Mecanique extends RecyclerView.Adapter<Mecanique.MyViewHolder>
{

    String Nom[],Force[],prix[];
    Context ct;
    int image[],test,argent,Leprix;

    public Mecanique(Context ct, String Nom2[], String Force2[], String Prix2[], int[] priseImage,int m,int monaie)
    {
        Log.i("test","1");
        Nom = Nom2;
        Force = Force2;
        prix = Prix2;
        image = priseImage;
        test = m;
        argent = monaie;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) { // Deuxième methode éxécuté par le système d'adapter du recyclerview.

        Log.i("test","2");
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.main,parent,false); // On charge le layout personnalisé qu'on a crée pour chaque ligne/instance
        Log.i("test","2.1");
        MyViewHolder myViewHolder = new MyViewHolder(relativeLayout); //On envoie ce textview au viewholder pour qu'il associe en mémoire ce widget
        Log.i("test","2.2");
        //LayoutInflater inflater = LayoutInflater.from(ct);
        //View view = inflater.inflate(R.layout.liste_plat,parent,false);

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) { // Ici il récupère en paramètre le viewholder retourné par oncreateviewholder. Il n'y a plus qu'à changer le texte
        viewHolder.NomArme.setText(Nom[position]);
        viewHolder.PuissanceArme.setText("Puissance: +"+Force[position]);
        viewHolder.PrixArme.setText("Prix: "+prix[position]);
        viewHolder.myimage.setImageResource(image[position]);
        viewHolder.myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("la",String.valueOf(Integer.valueOf(prix[position])));
                Leprix =Integer.valueOf(prix[position]);
                //Log.i("la",String.valueOf(Integer.valueOf(prix[position])));
                //Log.i("la",String.valueOf(argent));
                if(argent >= Leprix)
                {
                    //Log.i("la","Up: "+ Force[position]);
                    argent = Leprix;
                    test = Integer.valueOf(Force[position]);
                    LesMecaniques.changer_puissance(test);
                    LesMecaniques.changer_argent(argent);
                }
            }
        });
        Log.i("test","3");
    }

    @Override
    public int getItemCount() { //Première méthode éxécuté par le système d'adapter du recyclerview, elle permet de connaitre combien d'instances de viewholder il faudra créer.

        Log.i("test","4");
        return Nom.length;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView NomArme,PuissanceArme,PrixArme;
        ImageView myimage;
        public MyViewHolder(RelativeLayout itemView) {//(@NonNull View itemView
            super(itemView);
            NomArme = (TextView) itemView.findViewById(R.id.Arme);
            PuissanceArme = (TextView) itemView.findViewById(R.id.Puissance);
            PrixArme = (TextView) itemView.findViewById(R.id.Prix);
            myimage = (ImageView) itemView.findViewById(R.id.imageArme);
            Log.i("test","6");
        }
    }

}
