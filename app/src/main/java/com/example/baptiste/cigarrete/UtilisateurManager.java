package com.example.baptiste.cigarrete;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Cette classe permet de faire le lien entre la base de données et l'application concernant l'utilisateur.
 * Created by Baptiste.
 */
public class UtilisateurManager {
    DatabaseManager dbm;
    SQLiteDatabase db;

    public UtilisateurManager(Context ctx){
        dbm=new DatabaseManager(ctx, "mabase", null,1);
    }

    /**
     * Ouvre la base de données
     */
    public void open()
    {
        db= dbm.getWritableDatabase();
    }

    /**
     * Ferme la base de données
     */
    public void close()
    {
        db.close();
    }

    /**
     * Ajoute l'utilisateur dans la base de données
     * @param u
     * @return
     */
    public long AddUser(Utilisateur u)
    {
        open();
        ContentValues vals= new ContentValues();
        vals.put("Prenom",u.getPrenom());
        vals.put("Nom",u.getNom());
        vals.put("cigJour",u.getConsoCig());
        vals.put("prixPaquet",u.getPrixPaq());
        vals.put("cigPaq", u.getCigPaquet());
        vals.put("prixCig", u.getPrixCig());
        vals.put("daysWithoutSmoke",0);
        vals.put("cigNoConsum",0);
        vals.put("moneyEconomised",0);
        long i=db.insert("user", null, vals);
        close();
        return i;
    }

    /**
     * Vérifie si l'utilisateur existe dans la base de données.
     * @return
     */
    public boolean existUser()
    {
        boolean exist=false;
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select Nom FROM USER", null);
        if (c.getCount()!=0)
            exist=true;
        else
            exist=false;
        return exist;
    }

    /**
     * Récupère l'utilisateur depuis les informations inscrite dans la base de données
     * @return
     */
    public Utilisateur getUtilisateur()
    {
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select Prenom, Nom, cigJour, prixPaquet, cigPaq, prixCig,daysWithoutSmoke,cigNoConsum, moneyEconomised FROM USER",null);
        if (c.getCount()==0)
            return null;
        c.moveToFirst();
        Utilisateur u= new Utilisateur();
        u.setPrenom(c.getString(0));
        u.setNom(c.getString(1));
        u.setConsoCig(c.getInt(2));
        u.setPrixPaq(c.getFloat(3));
        u.setCigPaquet(c.getInt(4));
        u.setPrixCig(c.getFloat(5));
        u.setDaysWithoutSmoke(c.getInt(6));
        u.setCigNoConsum(c.getInt(7));
        u.setMoneyEconomised(c.getFloat(8));
        close();
        return u;
    }

    /**
     * Modifie la valeur de cigarette non consommées dans la base de données
     * @param nb
     */
    public void setCigNoConsum(int nb)
    {
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select _id, cigJour, cigNoConsum FROM USER",null);
        c.moveToFirst();
        String id= c.getString(0);
        int cigJour=c.getInt(1);
        int cigNo=0;
        cigNo=c.getInt(2);
        int res= cigNo+(cigJour-nb);
        open();
        db.execSQL("UPDATE USER set cigNoConsum= " + res + " where _id='" + id + "'");
        setMoneyEco(res);
        close();
    }

    /**
     * Modifie la valeur de l'argent économisé.
     * @param i
     */
    public void setMoneyEco(int i)
    {
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select _id, prixCig, moneyEconomised FROM USER", null);
        c.moveToFirst();
        String id= c.getString(0);
        float prixCig=c.getFloat(1);
        float res=0;
        res=(i*prixCig);
        open();
        db.execSQL("UPDATE USER set moneyEconomised= "+res+" where _id='"+id+"'");
        close();
    }

    /**
     * Récupère le prix de la cigarette à l'unité depuis la base de données
     * @return
     */
    public float getPrixCig()
    {
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select prixCig FROM USER",null);
        c.moveToFirst();
        int i=0;
        i=c.getInt(0);
        return i;
    }

    /**
     * Récupère le nombre de cigarette que l'utilisateur consomme par jour en moyenne depuis la
     * base de données.
     * @return
     */
    public int getCigJour()
    {
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select cigJour FROM USER", null);
        c.moveToFirst();
        int i=0;
        i=c.getInt(0);
        return i;
    }

    /**
     * Modifie le nombre de jour sans fumer dans la base de données.
     * @param i
     */
    public void setDaysWithout(int i)
    {
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select _id, daysWithoutSmoke FROM USER", null);
        c.moveToFirst();
        String id= c.getString(0);
        int daysWithou=c.getInt(1);
        int res=0;
        if (i==0)
            res=daysWithou+1;
        else
            res=0;
        open();
        db.execSQL("UPDATE USER set daysWithoutSmoke= "+res+" where _id='"+id+"'");
        close();
    }

}

