package com.example.baptiste.cigarrete;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends Activity {
Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecupInfo();
        ConsommationManager cm=new ConsommationManager(this);
    }

    /**
     * Créer le menu (la liste déroulante)
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * créer et associe les valeurs du menu des options au vue correspondante.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case (R.id.Conseil):
                Intent conseil= new Intent(this, Conseil.class);
                startActivity(conseil);
                break;
            case (R.id.Main):
                Intent main= new Intent(this,MainActivity.class);
                startActivity(main);
                break;
            case (R.id.Recompense):
                Intent rec= new Intent(this,Recompense.class);
                startActivity(rec);
                break;
            case (R.id.Motivation):
                Intent motiv= new Intent(this,Src_Motivation.class);
                startActivity(motiv);
                break;
            case (R.id.cig_Today):
                Intent cig= new Intent(this,Cig_Smoke_Today.class);
                startActivity(cig);
                this.closeContextMenu();
                break;
            case (R.id.graph):
                Intent graph= new Intent(this,Graph.class);
                startActivity(graph);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Cette fonction est appeler dès que l'utilisateur appuie sur le bouton de suivi. Le bouton
     * appelle la vue de suivi.
     * @param v
     */
    public void onMonitoringClick(View v)
    {
        Intent obj= new Intent(this,Graph.class);
        startActivity(obj);
    }

    /**
     * Cette fonction est appeler dès que l'utilisateur appuie sur le bouton de santé. Le bouton
     * appelle la vue de santé.
     * @param v
     */
    public void onSanteClick(View v)
    {
        Intent obj= new Intent(this,Sante.class);
        startActivity(obj);
    }

    /**
     * Cette fonction est appeler dès que l'utilisateur appuie sur le bouton de récompense. Le bouton
     * appelle la vue de récompense.
     * @param v
     */
    public void onRecClick(View v)
    {
        Intent obj= new Intent(this,Recompense.class);
        startActivity(obj);
    }

    /**
     * Cette fonction est appeler dès que l'utilisateur appuie sur le bouton de conseil. Le bouton
     * appelle la vue de conseil.
     * @param v
     */
    public void onAdviceClick(View v)
    {
        Intent obj= new Intent(this,Conseil.class);
        startActivity(obj);
    }
    public void onConsoClick(View v)
    {
        Intent obj= new Intent(this,Cig_Smoke_Today.class);
        startActivity(obj);
    }

    /**
     * Récupère les informations concernant l'utilisateur qui sont nécessaire pour remplire les divers
     * différents champs
     */
    public void RecupInfo()
    {
        TextView name= (TextView)findViewById(R.id.TVName);
        TextView txtNoSmoke=(TextView)findViewById(R.id.TxtCigNoSmoke);
        TextView txtMoneyEco=(TextView)findViewById(R.id.TxtMoneyEco);
        TextView txtDaysWithout=(TextView)findViewById(R.id.TVDWithoutSmok);
        try
        {
            UtilisateurManager um= new UtilisateurManager(this);
            Utilisateur u= um.getUtilisateur();
            int cigNo= u.getCigNoConsum();
            float moneyEco =u.getMoneyEconomised();
            int daysWithout= u.getDaysWithoutSmoke();
            name.setText(u.getPrenom());
            txtNoSmoke.setText(String.valueOf(cigNo));
            txtMoneyEco.setText(String.valueOf(moneyEco));
            txtDaysWithout.setText(String.valueOf(daysWithout));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
