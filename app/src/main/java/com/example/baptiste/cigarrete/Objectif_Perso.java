package com.example.baptiste.cigarrete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Cette classe est une activité, possède donc une vue. Elle permet de voir les objectifs personnels
 * défini pas l'utilisateur.
 */
public class Objectif_Perso extends MainActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectif__perso);
        liste=(ListView)findViewById(R.id.LVPersoGoals);
        RecupValeur();
    }

    /**
     * Si l'utilisateur appui sur le bouton d'ajout, le bouton appelle l'activité d'ajout.
     * @param v
     */
    public void onClickAdd(View v)
    {
        Intent intent = new Intent(this, ADD_Objectif.class);
        startActivity(intent);
    }

    /**
     * Récupère les objectifs personnels puis les place dans la vue.
     */
    public void RecupValeur()
    {
        try
        {
            ObjectifManager om= new ObjectifManager(this);
            ArrayList<objectif_object> list = om.getAllObjectif();
            if (list!=null)
            {
                ObjectifAdapter adapter= new ObjectifAdapter(list,this);
                liste.setAdapter(adapter);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
