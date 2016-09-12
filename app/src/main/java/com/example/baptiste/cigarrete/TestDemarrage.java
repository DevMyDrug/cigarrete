package com.example.baptiste.cigarrete;

import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

/** Cette classe permet de vérifier si l'utilisateur est déjà inscrit ou non.
 * Created by Baptiste.
 */
public class TestDemarrage implements Runnable {

    boolean fini;
    boolean exist;
    UtilisateurManager um;

    public TestDemarrage(UtilisateurManager um) {
        this.um = um;
    }

    /**
     * Retourne la valeur de exist.
     * @return
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * Thread permettant de regarder la base pour savoir si l'utilisateur est inscrit ou non.
     */
    @Override
    public void run() {
        if (um.existUser())
            exist=true;
        else
            exist=false;
        fini=true;
    }

    /**
     * retourne la valeur de fini.
     * @return
     */
    public boolean isFini() {
        return fini;
    }
}

