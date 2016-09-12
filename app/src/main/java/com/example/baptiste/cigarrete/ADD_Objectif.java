package com.example.baptiste.cigarrete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Cette activité permet d'ajouter un nouvel objectif.
 */
public class ADD_Objectif extends MainActivity {

    EditText intituler,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__objectif);
        intituler=(EditText)findViewById(R.id.EtIntit);
        price=(EditText)findViewById(R.id.ETPrice);
    }

    /**
     * Cette fonction permet de gérer l'action quand l'utilisateur appuyer sur le bouton. La fonction
     * test si toute les information ont été saisi et si c'est la cas ajoute l'objectif dans la base de donnée.
     * @param v
     */
    public void onAddObjClick(View v)
    {
        if (intituler.getText().toString()!=null && price.getText().toString()!=null)
        {
            String inti=intituler.getText().toString();
            int prix=Integer.parseInt(price.getText().toString());
            ObjectifManager om= new ObjectifManager(this);
            om.dbRequest(inti,prix);
            Intent intent= new Intent(this, Objectif_Perso.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, R.string.errSubs,Toast.LENGTH_LONG).show();
    }


}
