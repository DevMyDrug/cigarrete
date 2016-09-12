package com.example.baptiste.cigarrete;
/**
 * La classe permet de récupéré la consommation de l'utilisateur, entrer cette consommation dans la table
 * SQlite correspondante
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Cig_Smoke_Today extends MainActivity {
EditText et;
    Consommation c;
    ConsommationManager cm;
    UtilisateurManager um;
    int j,m,a,s,js; //j= jour, m= mois, a= année, s=semaine
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cig__smoke__today);
        et=(EditText)findViewById(R.id.cigSmokeToday);
        getDate();
        c=new Consommation(j,m,a,s,js);
        cm= new ConsommationManager(this);
    }

    /**
     * Ce que dois faire le programme une fois que l'utilisateur appuie sur le bouton valider
     * @param v
     */
    public void onClickValide(View v)
    {
        int i= Integer.parseInt(et.getText().toString());
        um= new UtilisateurManager(this);
        if (!cm.alreadyEnter(j,m,a))
        {
            um.setCigNoConsum(i);
            cm.addConso(c, um, i);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, R.string.errDate,Toast.LENGTH_LONG).show();
    }

    /**
     * Récupère la date d'aujourd'hui
     */
    public void getDate()
    {
        Calendar cal= Calendar.getInstance();
        j=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR);
        s=cal.get(Calendar.WEEK_OF_YEAR);
        js=cal.get(Calendar.DAY_OF_WEEK);
    }

}
