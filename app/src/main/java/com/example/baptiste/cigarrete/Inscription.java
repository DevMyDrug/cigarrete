package com.example.baptiste.cigarrete;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Inscription extends MainActivity {
    EditText prenom,nom,consoCig,prixPaq,cigPaq;
    String nomU,PrenomU;
    int consoCigU, cigPaqU;
    float prixPaqU;
    UtilisateurManager um;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        nom=(EditText) findViewById(R.id.ETNom);
        prenom=(EditText) findViewById(R.id.ETPrenom);
        consoCig=(EditText) findViewById(R.id.ETNCigMoy);
        prixPaq=(EditText) findViewById(R.id.PrixPaquet);
        cigPaq=(EditText) findViewById(R.id.ETCigPaq);
        um= new UtilisateurManager(this);
    }

    /**
     * Cette fonction est appeler dès que l'utilisateur appuie sur le bouton de validation. Si toute
     * les informations ont été saisi, alors l'utilisateur est ajouter dans la base de données.
     * @param view
     */
    public void Valider(View view)
    {

        if (Complete())
        {
            float prixCigU= PrixCig();
            getValue();
            Utilisateur u= new Utilisateur(nomU,PrenomU,consoCigU,cigPaqU,0,0,prixPaqU,prixCigU,0);
            long lg=um.AddUser(u);
            Intent ide = new Intent(this, MainActivity.class);
            startActivity(ide);
        }
        else
            Toast.makeText(this,R.string.errSubs,Toast.LENGTH_LONG).show();
    }

    /**
     * récupère le prix de la cigarette à l'unité
     * @return
     */
    public float PrixCig()
    {
        float cigPaqu= Float.valueOf(cigPaq.getText().toString());
        float pricePaq=Float.valueOf(prixPaq.getText().toString());
        float res= pricePaq/cigPaqu;
        res= (float)Math.round(res*1000)/1000;
        return res;
    }

    /**
     * récupère les valeurs entré dans les champs de saisi et les ajoutes dans la classe utilisateur
     */
    public void getValue()
    {
        try{
            nomU=nom.getText().toString();
            PrenomU=prenom.getText().toString();
            consoCigU=Integer.parseInt(consoCig.getText().toString());
            cigPaqU=Integer.parseInt(cigPaq.getText().toString());
            prixPaqU=Float.valueOf(prixPaq.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Vérifie que les champs de saisis soient bien tous rempli.
     * @return
     */
    public boolean Complete()
    {
        boolean res=false;
        if (prenom.getText().length()!=0 && nom.getText().length()!=0&&consoCig.getText().length()!=0&&cigPaq.getText().length()!=0&&prixPaq.getText().length()!=0 )
            res=true;
        return res;
    }


}
