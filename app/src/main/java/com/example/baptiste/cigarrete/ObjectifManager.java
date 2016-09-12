package com.example.baptiste.cigarrete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe fais le lien entre la base de données et l'application et plus particulièrement concernant
 * les objectifs.
 * Created by Baptiste on 05/03/2016.
 */
public class ObjectifManager {
    DatabaseManager dbm;
    SQLiteDatabase db;
    Context ctx;
    public ObjectifManager(Context ctx)
    {
        dbm=new DatabaseManager(ctx, "mabase", null,1);
        this.ctx=ctx;
    }

    /**
     * ouvre la base de données
     */
    public void open()
    {
        db= dbm.getWritableDatabase();
    }

    /**
     * Ferme la base de données
     */
    public void close() { db.close();}

    /**
     * Récupère tout les objectifs de la base de données, les ajoutes dans une liste d'objectifs qui
     * est renvoyer une fois tout les ajouts effectuer.
     * @return
     */
    public ArrayList<objectif_object> getAllObjectif()
    {
        db= dbm.getReadableDatabase();
        Cursor c= db.rawQuery("Select intituler, Condition FROM Objectif",null);
        if (c.getCount()==0)
            return null;
        c.moveToFirst();
        ArrayList<objectif_object> list = new ArrayList<objectif_object>();
        for(int i=0; i<c.getCount(); i++)
        {
            objectif_object oo=new objectif_object(c.getString(0),c.getInt(1));
            list.add(oo);
            c.moveToNext();
        }
        return list;
    }

    /**
     * Ajoute un objectif dans la base de données
     * @param intituler
     * @param cond
     */
    public void dbRequest(String intituler, double cond)
    {
        open();
        ContentValues vals= new ContentValues();
        vals.put("intituler",intituler);
        vals.put("Condition",cond);
        db.insert("Objectif", null, vals);
        close();
    }
}
