package com.example.baptiste.cigarrete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Permet d'inscrire la consommation dans la base de donnée SQlite
 * Created by Baptiste.
 */
public class ConsommationManager {
    DatabaseManager dbm;
    SQLiteDatabase db;
    Context ctx;

    public ConsommationManager(Context ctx) {
        dbm = new DatabaseManager(ctx, "mabase", null, 1);
        this.ctx= ctx;
    }

    /**
     * Ouvre la base de donnée
     */
    public void open() {
        db = dbm.getWritableDatabase();
    }

    /**
     * Fermer la base de donnée
     */
    public void close() {
        db.close();
    }

    /**
     * Permet d'inscrire la date de consommation dans la base de donnée avec la quantité de cigarette
     * fumer, l'argent économiser et consommer.
     * @param conso
     * @param u
     * @param cigFume
     */
    public void addConso(Consommation conso, UtilisateurManager u, int cigFume)
    {
        open();
        ContentValues vals= new ContentValues();
        vals.put("Jour ", conso.getJour());
        vals.put("Mois", conso.getMois());
        vals.put("Annee", conso.getAnnee());
        vals.put("Semaine", conso.getSemaine());
        vals.put("JourDeSemaine",conso.getJourSemaine());
        vals.put("CigFume ", cigFume);
        float mc=cigFume*u.getPrixCig();
        float me=u.getCigJour()*u.getPrixCig()-mc;
        vals.put("MoneyEcon ", me);
        vals.put("moneyConsumn ",mc);
        db.insert("Consommation", null, vals);
        close();
        u.setDaysWithout(cigFume);
    }

    /**
     * Vérifie si la date est déjà inscrite dans la base de donnée ou pas.
     * @param j jour
     * @param m mois
     * @param a année
     * @return true si la date est déjà présente, sinon false.
     */
    public boolean alreadyEnter(int j, int m, int a)
    {
        boolean exist=false;
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select _id FROM Consommation Where Jour= "+j+" and Mois="+m+" and Annee="+a, null);
        if (c.getCount()!=0)
            exist=true;
        else
            exist=false;
        return exist;
    }

    /**
     * Récupère la consommation de la semaine passer en paramètre
     * @param Semaine
     * @return
     */
    public ArrayList<Consommation> getConsoSemaine(int Semaine)
    {
        int j=0;
        int m=0;
        int cigSmoke;
        ArrayList<Consommation> tabConso= new ArrayList<Consommation>();
        String str="";
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select _id, Jour, Mois, JourDeSemaine, CigFume FROM Consommation Where Semaine="+Semaine, null);
        try {
            if (c.getCount()!=0)
            {
                c.moveToFirst();
                for(int i=0; i<c.getCount(); i++)
                {
                    j=c.getInt(1);
                    m=c.getInt(2);
                    int js=c.getInt(3);
                    str=intToJour(js)+" "+j+"/"+(m+1)+" ";
                    if (c.getInt(2)!=0) {
                        cigSmoke=c.getInt(4);
                        tabConso.add(new Consommation(cigSmoke,str));
                    }
                    else
                    {
                        str+=": null";
                        tabConso.add(new Consommation("null",str));
                    }
                    c.moveToNext();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return tabConso;
    }

    /**
     * Converti le jour dont l'ID est passer en paramètre au nom correspondant
     * @param i
     * @return
     */
    public String intToJour(int i)
    {
        String jour="";
        switch (i)
        {
            case 2:
                jour="Lundi";
                break;
            case 3:
                jour="Mardi";
                break;

            case 4:
                jour="Mercredi";
                break;
            case 5:
                jour="Jeudi";
                break;
            case 6:
                jour="Vendredi";
                break;
            case 7:
                jour="Samedi";
                break;
            case 1:
                jour="Dimanche";
                break;
        }
        return jour;
    }

    /**
     * Récupère la consommation de la semaine passer en paramètre
     * @param mois
     * @return
     */
    public ArrayList<Consommation> getConsoMois(int mois)
    {
        int j=0;
        int m=0;
        int cigSmoke;
        ArrayList<Consommation> tabConso= new ArrayList<Consommation>();
        String str="";
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select _id, Jour, Mois, JourDeSemaine, CigFume FROM Consommation Where Mois="+mois, null);
        try {
            if (c.getCount()!=0)
            {
                c.moveToFirst();
                for(int i=0; i<c.getCount(); i++)
                {
                    j=c.getInt(1);
                    m=c.getInt(2);
                    int js=c.getInt(3);
                    str=intToJour(js)+" "+j+"/"+(m+1)+" ";
                    if (c.getInt(2)!=0) {
                        cigSmoke=c.getInt(4);
                        tabConso.add(new Consommation(cigSmoke,str));
                    }
                    else
                    {
                        str+=": null";
                        tabConso.add(new Consommation("null",str));
                    }
                    c.moveToNext();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return tabConso;
    }
}
