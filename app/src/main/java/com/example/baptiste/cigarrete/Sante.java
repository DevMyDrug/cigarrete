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
 * Cette classe est une activité, possède donc une vue. Elle permet de voir les divers informations
 * sur la santé lors de l'arrêt de consommation.
 */
public class Sante extends MainActivity {
    ListView lister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sante);
        lister= (ListView)findViewById(R.id.LVSante);
        RecupInfo();
    }

    /**
     * Cette méthode permet de récupéré les information sur la santée pour les mettre dans la vue.
     */
    public void RecupInfo()
    {
        try
        {
            String[] values= getResources().getStringArray(R.array.Sante);
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < values.length; ++i) {
                list.add(String.valueOf(values[i]));
            }
            ArrayAdapter<String> myArray= new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
            myArray.setDropDownViewResource(android.R.layout.simple_list_item_1);
            lister.setAdapter(myArray);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
