package com.example.baptiste.cigarrete;

import java.util.Calendar;

/**
 * Created by Baptiste on 03/07/2016.
 */
public class Cig_Object {
    private int nbCig;
    private int dayOfYears;
    private static Cig_Object single;
    private Cig_Object()
    {
        nbCig=0;
        dayOfYears= Calendar.DAY_OF_YEAR;
    }
    public static Cig_Object getInstance()
    {
        if(single==null)
        {
            single= new Cig_Object();
        }
        return single;
    }

    public int getNbCig() {
        return nbCig;
    }

    public void setNbCig(int nbCig) {
        this.nbCig = nbCig;
    }

    public int getDayOfYears() {
        return dayOfYears;
    }

    public void setDayOfYears(int dayOfYears) {
        this.dayOfYears = dayOfYears;
    }
}
