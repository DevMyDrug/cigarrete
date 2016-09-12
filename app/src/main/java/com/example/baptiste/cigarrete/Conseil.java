package com.example.baptiste.cigarrete;
/**
 * Cette classe est lier à une vue. Elle permet de choisir une des catégorie de conseil.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Conseil extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseil);
    }

    /**
     * Une fois que l'utilisateur appuie sur ce bouton, l'application appel la vue qui correspond au conseil par rapport au goût.
     * @param v
     */
    public void BtnGout(View v)
    {
        Intent i= new Intent(this, Conseil_Gout.class);
        startActivity(i);
    }

    /**
     * Une fois que l'utilisateur appuie sur ce bouton, l'application appel la vue qui correspond au conseil par rapport au geste.
     * @param v
     */
    public void BtnGeste(View v)
    {
        Intent i= new Intent(this, Conseil_Geste.class);
        startActivity(i);
    }

    /**
     * Une fois que l'utilisateur appuie sur ce bouton, l'application appel la vue qui correspond au conseil par rapport à la nicotine.
     * @param v
     */
    public void BtnNico(View v)
    {
        Intent i= new Intent(this, Conseil_Nico.class);
        startActivity(i);
    }
}
