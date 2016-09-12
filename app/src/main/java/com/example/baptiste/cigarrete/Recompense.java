package com.example.baptiste.cigarrete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * Cette classe est une activité, possède donc une vue. Elle permet de voir les divers catégories
 * de récompense et d'en sélectionner une.
 */
public class Recompense extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recompense);
    }

    /**
     * Cette fonction permet de gérer l'action quand l'utilisateur appuyer sur le bouton Consommable. Le bouton
     * appelle l'activité de recompense consommable
     * @param v
     */
    public void onClickConso(View v)
    {
        Intent intent= new Intent(this,RecConso.class);
        startActivity(intent);
    }

    /**
     * Cette fonction permet de gérer l'action quand l'utilisateur appuyer sur le bouton Informatique. Le bouton
     * appelle l'activité de recompense informatique
     * @param v
     */
    public void onClickInfo(View v)
    {
        Intent intent= new Intent(this,RecInfo.class);
        startActivity(intent);
    }

    /**
     * Cette fonction permet de gérer l'action quand l'utilisateur appuyer sur le bouton Sportif. Le bouton
     * appelle l'activité de recompense sportif
     * @param v
     */
    public void onClickSport(View v)
    {
        Intent intent= new Intent(this,RecSport.class);
        startActivity(intent);
    }


    /**
     * Cette fonction permet de gérer l'action quand l'utilisateur appuyer sur le bouton Personnel. Le bouton
     * appelle l'activité de recompense personnel
     * @param v
     */
    public void onClickPerso(View v)
    {
        Intent intent= new Intent(this, Objectif_Perso.class);
        startActivity(intent);
    }

}
