package com.example.baptiste.cigarrete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe est une activité, possède donc une vue. Elle permet de voir les objectifs informatique
 */
public class RecInfo extends MainActivity {
ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_info);
        liste=(ListView)findViewById(R.id.ListInfo);
        RecupInfo();
    }

    /**
     * Récupère les information depuis un tableau puis les ajoute dans la vue.
     */
    public void RecupInfo()
    {
        try
        {
            String[] intituler= getResources().getStringArray(R.array.RecompInformatique);
            String[] cond= getResources().getStringArray(R.array.condInformatique);
            ArrayList<objectif_object> list = new ArrayList<objectif_object>();
            double prix=0.0;
            for (int i = 0; i < intituler.length; ++i) {
                prix=Double.parseDouble(cond[i]);
                list.add(new objectif_object(String.valueOf(intituler[i]),prix, false));
            }

            ObjectifAdapter adapter= new ObjectifAdapter(list,this);
            ArrayAdapter<String> myArray= new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
            myArray.setDropDownViewResource(android.R.layout.simple_list_item_1);
            liste.setAdapter(adapter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
