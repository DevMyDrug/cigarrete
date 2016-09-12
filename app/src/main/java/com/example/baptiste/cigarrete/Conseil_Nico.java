package com.example.baptiste.cigarrete;
/**
 * Cette classe permet de repertorier des conseils a propos du manque du consommateur par rapport a
 * la nicotine
 */
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Conseil_Nico extends MainActivity {
    ListView lister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseil__nico);
        lister= (ListView)findViewById(R.id.ListConsNico);
        RecupInfo();
    }

    /**
     * Récupère les informations le tableau consGest depuis le fichier array.xml du dossier value.
     */
    public void RecupInfo()
    {
        try
        {
            String[] values= getResources().getStringArray(R.array.ConsNicotine);

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
