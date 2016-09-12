package com.example.baptiste.cigarrete;


/**
 * Cette classe est la classe de l'utilisateur. C'est cette classe qui sera appeller pour récupéré les
 * informations concernant l'utilisateur.
 * Created by Baptiste.
 */
public  class Utilisateur {
    private String nom,prenom;
    private int consoCig, cigPaquet, cigNoConsum, daysWithoutSmoke;
    private float prixPaq,prixCig,moneyEconomised;

    public Utilisateur(String nom, String prenom, int consoCig, int cigPaquet, int cigNoConsum, int daysWithoutSmoke, float prixPaq, float prixCig, float moneyEconomised) {
        this.nom = nom;
        this.prenom = prenom;
        this.consoCig = consoCig;
        this.cigPaquet = cigPaquet;
        this.cigNoConsum = cigNoConsum;
        this.daysWithoutSmoke = daysWithoutSmoke;
        this.prixPaq = prixPaq;
        this.prixCig = prixCig;
        this.moneyEconomised = moneyEconomised;
    }

    public Utilisateur() {
        super();
    }

    /**
     * récupère le nom de l'utilisateur
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * modifie le nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prénom
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la consommation en cigarette moyenne de l'utilisateur
     * @return
     */
    public int getConsoCig() {
        return consoCig;
    }

    /**
     * Modifie la consommation en cigarette moyenne de l'utilisateur
     * @param consoCig
     */
    public void setConsoCig(int consoCig) {
        this.consoCig = consoCig;
    }

    /**
     * Récupère la quantité de cigarette par paquet
     * @return
     */
    public int getCigPaquet() {
        return cigPaquet;
    }

    /**
     * Modifie la quantité de cigarette par paquet
     * @param cigPaquet
     */
    public void setCigPaquet(int cigPaquet) {
        this.cigPaquet = cigPaquet;
    }

    /**
     * Récupère le prix du paquet
     * @return
     */
    public float getPrixPaq() {
        return prixPaq;
    }

    /**
     * Récupère le prix de la cigarette à l'unité
     * @return
     */
    public float getPrixCig() {
        return prixCig;
    }

    /**
     * Récupère le nombre de cigarette non consommé
     * @return
     */
    public int getCigNoConsum() {
        return cigNoConsum;
    }

    /**
     * Modifie le nombre de cigarette non consommées
     * @param cigNoConsum
     */
    public void setCigNoConsum(int cigNoConsum) {
        this.cigNoConsum = cigNoConsum;
    }

    /**
     * Récupère le nombre de jour sans fumer
     * @return
     */
    public int getDaysWithoutSmoke() {
        return daysWithoutSmoke;
    }

    /**
     * Modifie le nombre de jour sans fumer
     * @param daysWithoutSmoke
     */
    public void setDaysWithoutSmoke(int daysWithoutSmoke) {
        this.daysWithoutSmoke = daysWithoutSmoke;
    }

    /**
     * Récupère l'argent economisé
     * @return
     */
    public float getMoneyEconomised() {
        return moneyEconomised;
    }

    /**
     * Modifie le prix du paquet
     * @param prixPaq
     */
    public void setPrixPaq(float prixPaq) {
        this.prixPaq = prixPaq;
    }

    /**
     * Modifie le prix de la cigarette à l'unité
     * @param prixCig
     */
    public void setPrixCig(float prixCig) {
        this.prixCig = prixCig;
    }

    /**
     * Modifie l'argent économisé.
     * @param moneyEconomised
     */
    public void setMoneyEconomised(float moneyEconomised) {
        this.moneyEconomised = moneyEconomised;
    }
}
