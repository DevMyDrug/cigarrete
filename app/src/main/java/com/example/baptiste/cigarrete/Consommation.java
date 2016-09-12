package com.example.baptiste.cigarrete;

/**
 * Permets de récupéré la date de la consommation.
 * Created by Baptiste.
 */
public class Consommation {

    private int jour,mois,annee,semaine, jourSemaine, cigFume;
    private String nul;
    private String date;

    /**
     * Pl
     * @param nul
     * @param date
     */
    public Consommation(String nul,String date) {
        this.nul=nul;
        this.date = date;
    }

    public Consommation(int cigFume, String date) {
        this.cigFume = cigFume;
        this.date=date;
    }

    public Consommation(int jour, int mois, int annee, int semaine, int jourSemaine) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.semaine = semaine;
        this.jourSemaine=jourSemaine;
    }

    /**
     * Récupère la date complète du style Lundi 01/01
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Récupère la consommation de la journée
     * @return
     */
    public int getCigFume() {
        return cigFume;
    }

    /**
     * récupère la valeur de nul
     * @return
     */
    public String getNul() {
        return nul;
    }

    /**
     * récupère le jour de la semaine
     * @return
     */
    public int getJourSemaine() {
        return jourSemaine;
    }

    /**
     * Permets de modifier le jour de la semaine
     * @param jourSemaine
     */
    public void setJourSemaine(int jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    /**
     * récupère le jour
     * @return
     */
    public int getJour() {
        return jour;
    }

    /**
     * Récupère le mois
     * @return
     */
    public int getMois() {
        return mois;
    }

    /**
     * récupère l'année
     * @return
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Récupère la semaine de l'année
     * @return
     */
    public int getSemaine() {
        return semaine;
    }

}
