package com.example.baptiste.cigarrete;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Baptiste on 17/02/2016.
 */
public class DatabaseManager extends SQLiteOpenHelper{

    String tableUser="Create table user (_id INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT NOT NULL, Prenom TEXT NOT NULL, cigJour INT NOT NULL, prixPaquet REAL NOT NULL, cigPaq INT NOT NULL, prixCig REAL NOT NULL, daysWithoutSmoke INT NOT NULL, cigNoConsum INT NOT NULL, moneyEconomised FLOAT)";
    String tableObjectif="Create table Objectif (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, intituler TEXT NOT NULL, Condition INT NOT NULL)";
    String tableConso="Create table Consommation (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Jour INT , Mois INT, Annee INT, Semaine INT, JourDeSemaine INT, CigFume INT, MoneyEcon FLOAT, moneyConsumn FLOAT )";

    Context ctx;
    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx=context;
    }

    /**
     * Insère les tables dans la base de donnée
     * @param db
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUser);
        db.execSQL(tableObjectif);
        db.execSQL(tableConso);
        ConsommationManager cm= new ConsommationManager(ctx);
    }

    /**
     * Permet mettre à jour les tables de la base de données.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS Objectif");
        db.execSQL("DROP TABLE IF EXISTS Consommation");

        this.onCreate(db);
    }


}
