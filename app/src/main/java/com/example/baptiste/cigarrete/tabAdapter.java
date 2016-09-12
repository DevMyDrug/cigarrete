package com.example.baptiste.cigarrete;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Cette classe permet de personnaliser un listview et ainsi permet d'ajouter des composant graphique
 * Created by Baptiste.
 */
public class tabAdapter extends BaseAdapter {
    private List<Consommation> mListObj;
    private int cigMoy;
    private Context ctx;
    private LayoutInflater mInflater;

    public tabAdapter(List<Consommation> mListObj, Context ctx) {
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
        if (convertView == null)
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.list_tab_adapter, parent, false);
        else
            layoutItem = (LinearLayout) convertView;

        TextView txt = (TextView) layoutItem.findViewById(R.id.txtConso);
        UtilisateurManager um = new UtilisateurManager(ctx);
        Utilisateur u = um.getUtilisateur();
        int cigMoy=u.getConsoCig();

        String str="";

        if(mListObj.get(position).getNul()==null)
        {
            txt.setText(mListObj.get(position).getDate() + ": " + mListObj.get(position).getCigFume()+" "+ctx.getString(R.string.cigarette));
            if (mListObj.get(position).getCigFume()<=cigMoy/2)
                txt.setTextColor(layoutItem.getResources().getColor(R.color.green));
            else if(mListObj.get(position).getCigFume()<=cigMoy)
            {
                txt.setTextColor(layoutItem.getResources().getColor(R.color.blue));
            }
            else if(mListObj.get(position).getCigFume()<=(cigMoy*1.5))
                txt.setTextColor(layoutItem.getResources().getColor(R.color.gold));
            else
                txt.setTextColor(layoutItem.getResources().getColor(R.color.red));
            str="";
        }
        else {
            txt.setText(mListObj.get(position).getDate() + " " + mListObj.get(position).getNul());
            txt.setTextColor(layoutItem.getResources().getColor(R.color.black));
        }
        return layoutItem;
    }

    public void clearAll()
    {
    }
}
