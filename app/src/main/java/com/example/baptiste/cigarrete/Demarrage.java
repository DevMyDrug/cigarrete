package com.example.baptiste.cigarrete;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Demarrage extends Activity {
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demarrage);
        pb= (ProgressBar)findViewById(R.id.pBar);
        NextActivities();
    }

    /**
     * Permet de tester au démarrage si l'utilisateur à été inscrit ou pas.
     */
    public void NextActivities()  {
        pb.setVisibility(View.VISIBLE);
        UtilisateurManager um= new UtilisateurManager(this);
        TestDemarrage td= new TestDemarrage(um);
        Thread t= new Thread(td);

        t.start();

        while (!td.isFini())
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pb.setVisibility(View.INVISIBLE);
        if (td.isExist())
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            this.closeContextMenu();
        }
        else
        {
            Intent insc= new Intent(this, Inscription.class);
            startActivity(insc);

        }
    }

}
