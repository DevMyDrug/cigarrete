package com.example.baptiste.cigarrete;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Graph extends MainActivity {

    ListView lister;
    List<Integer> allWeek;
    Spinner spinDate;
    Spinner spinSemMois;
    ConsommationManager cm=new ConsommationManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        lister=(ListView)findViewById(R.id.listViewConso);
        spinDate=(Spinner)findViewById(R.id.SpinnerDate);
        spinSemMois=(Spinner)findViewById(R.id.SpSemMois);
        setFirstSpinner();
    }

    /**
     * Recupère les information de la semaine passer en paramètre pour les mettres dans la vue.
     * @param semaine
     */
    public void RecupInfoSemaine(int semaine )
    {
        try
        {
            ArrayList<Consommation> values= cm.getConsoSemaine(semaine);
            List<Consommation> list = new ArrayList<Consommation>();
            for (int i = 0; i < values.size(); ++i) {
                if(values.get(i).getNul()==null)
                    list.add(new Consommation(values.get(i).getCigFume(),values.get(i).getDate()));
                else
                    list.add(new Consommation(values.get(i).getNul(),values.get(i).getDate()));
            }
            tabAdapter adapter= new tabAdapter(list,this);
            lister.setAdapter(adapter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Récupère toute les semaines de l'année
     */
    public void getAllSemaine()
    {
        Calendar c=Calendar.getInstance();
        int nbSemaine=c.getActualMaximum(Calendar.WEEK_OF_YEAR);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < nbSemaine; ++i) {
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> myArray= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,list);
        myArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDate.setAdapter(myArray);
    }

    /**
     * Récupère tout les mois de l'année, récupère leur nom et les entre dans la liste déroulante correspondante.
     */
    public void getAllMonth()
    {
        int nbMois=12;
        List<String> list = new ArrayList<String>();

        for (int i = 1; i < nbMois+1; ++i) {
            list.add(intToMonth(i));
        }
        ArrayAdapter<String> myArray= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,list);
        myArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDate.setAdapter(myArray);
    }

    /**
     * Cette fonction écoute la liste déroulante où l'utilisateur chois la semaine ou le mois et
     * appelle la fonction adéquate.
     */
    public void getSpinSemMoisLister()
    {
        spinSemMois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem == getString(R.string.semaine)) {
                    lister.setAdapter(null);
                    getAllSemaine();
                    setSecondSpinnerListerSemaine();
                } else {
                    lister.setAdapter(null);
                    getAllMonth();
                    setSecondSpinnerListerMois();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Convertit l'ID passer en paramètre en nom de mois.
     * @param i
     * @return
     */
    public String intToMonth(int i)
    {
        String str="";
        switch (i)
        {
            case 1:
                str=getString(R.string.janvier);
                break;
            case 2:
                str=getString(R.string.fevrier);
                break;

            case 3:
                str=getString(R.string.mars);
                break;
            case 4:
                str=getString(R.string.avril);
                break;
            case 5:
                str=getString(R.string.mai);
                break;
            case 6:
                str=getString(R.string.juin);
                break;
            case 7:
                str=getString(R.string.juillet);
                break;
            case 8:
                str=getString(R.string.aout);
                break;
            case 9:
                str=getString(R.string.septembre);
                break;
            case 10:
                str=getString(R.string.octobre);
                break;
            case 11:
                str=getString(R.string.novembre);
                break;
            case 12:
                str=getString(R.string.decembre);
                break;
        }
        return str;
    }

    /**
     * ajoute 'mois' et 'semaine' dans la première liste déroulante
     */
    public void setFirstSpinner()
    {
        String semaine=getString(R.string.semaine);
        String mois= getString(R.string.mois);
        List<String> list = new ArrayList<String>();
        list.add(semaine);
        list.add(mois);
        ArrayAdapter<String> myArray= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,list);
        myArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSemMois.setAdapter(myArray);
        getSpinSemMoisLister();
    }

    /**
     * Permet d'écouter la seconde liste déroulante dans le cas où la première liste à la valeur
     * 'Semaine'. Ainsi on récupère les informations depuis la base et les insères dans la vue.
     */
    public void setSecondSpinnerListerSemaine()
    {
        spinDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i=position;
                RecupInfoSemaine(position);
                String str="";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    /**
     * Permet d'écouter la seconde liste déroulante dans le cas où la première liste à la valeur
     * 'Mois'. Ainsi on récupère les informations depuis la base et les insères dans la vue.
     */
    public void setSecondSpinnerListerMois()
    {
        spinDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lister.setAdapter(null);
                RecupInfoMois(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * On récupère les informations de consommation du mois passer en paramètre et on les insère dans
     * la vue.
     * @param mois
     */
    public void RecupInfoMois(int mois )
    {
        try
        {
            ArrayList<Consommation> values= cm.getConsoMois(mois);
            List<Consommation> list = new ArrayList<Consommation>();
            for (int i = 0; i < values.size(); ++i) {
                if(values.get(i).getNul()==null)
                    list.add(new Consommation(values.get(i).getCigFume(),values.get(i).getDate()));
                else
                    list.add(new Consommation(values.get(i).getNul(),values.get(i).getDate()));
            }
            tabAdapter adapter= new tabAdapter(list,this);
            lister.setAdapter(adapter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
