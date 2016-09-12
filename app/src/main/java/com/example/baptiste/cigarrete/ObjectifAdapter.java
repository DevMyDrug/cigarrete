package com.example.baptiste.cigarrete;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Cette classe permet de personnaliser un listview et ainsi permet d'ajouter des composant graphique
 * Created by Baptiste.
 */
public class ObjectifAdapter extends BaseAdapter {
    private List<objectif_object> mListObj;
    private Context ctx;
    private LayoutInflater mInflater;

    public ObjectifAdapter(List<objectif_object> mListObj, Context ctx) {
        this.mListObj = mListObj;

        this.ctx = ctx;
        this.mInflater = LayoutInflater.from(ctx);
    }

    /**
     * Récupère la taille de la liste
     * @return
     */
    @Override
    public int getCount() {
        return mListObj.size();
    }

    /**
     * Récupère l'objet de la liste à la position passer en paramètre.
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mListObj.get(position);
    }

    /**
     * Récupère l'ID de l'objet qui est à la position passer en paramètre.
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Récupère la vue et insère les données dans les différents composant graphique.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        if (convertView==null)
            layoutItem=(LinearLayout)mInflater.inflate(R.layout.list_objectif_layout,parent,false);
        else
            layoutItem=(LinearLayout) convertView;
        TextView txt=(TextView)layoutItem.findViewById(R.id.txtObj);
        TextView cond= (TextView)layoutItem.findViewById(R.id.Txt_cond);
        UtilisateurManager um= new UtilisateurManager(ctx);
        Utilisateur u=um.getUtilisateur();
        txt.setText(mListObj.get(position).intituler);

        double prix= mListObj.get(position).condition;
        float argent= u.getMoneyEconomised();
        int nb= (int)(argent/prix);
        double temp= argent-(nb*prix);
        double reste= prix-temp;
        reste=(double)Math.round(reste*100)/100;
        double prixCig= u.getPrixCig();
        int nbCigReste=(int)(prix/prixCig);
        String str= ctx.getString(R.string.adapterCout);
        str+= String.valueOf(prix);
        str+=ctx.getString(R.string.adapterPossess);
        str+=String.valueOf(nb);
        str+=ctx.getString(R.string.adapterManque);
        str+=String.valueOf(reste);str+=ctx.getString(R.string.adapterProchain);
        str+=ctx.getString(R.string.correspond);
        str+=nbCigReste;
        str+=ctx.getString(R.string.cigarette);
        cond.setText(str);


        return layoutItem;
    }
}
