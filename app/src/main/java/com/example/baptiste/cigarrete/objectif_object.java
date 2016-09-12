package com.example.baptiste.cigarrete;

/**
 * Cette classe est la classe des objectifs. Elle possède les variable intituler, condition et accomplit.
 * Created by Baptiste on 05/03/2016.
 */
public class objectif_object {
    int idObj;
    String intituler;
    double condition;
    boolean accomplit;


    public objectif_object(String intituler, double condition, boolean accomplit) {
        this.intituler = intituler;
        this.condition = condition;
        this.accomplit = accomplit;
    }

    public objectif_object(String intituler, double condition) {
        this.intituler = intituler;
        this.condition = condition;
    }

    /**
     * Récupère l'id de l'objectif
     * @return
     */
    public int getIdObj() {
        return idObj;
    }

    /**
     * Récupère l'intituler de l'objectif
     * @return
     */
    public String getIntituler() {
        return intituler;
    }

    /**
     * Permet de changer l'intituler
     * @param intituler
     */
    public void setIntituler(String intituler) {
        this.intituler = intituler;
    }

    /**
     * récupère la condition
     * @return
     */
    public double getCondition() {
        return condition;
    }

    /**
     * Récupère la valeur de accomplit.
     * @return
     */
    public boolean isAccomplit() {
        return accomplit;
    }

}
