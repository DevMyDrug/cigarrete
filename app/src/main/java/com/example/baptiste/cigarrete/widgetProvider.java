package com.example.baptiste.cigarrete;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by Baptiste on 14/05/2016.
 */
public class widgetProvider extends AppWidgetProvider {
    int val=-1;
    Context ctx;
    Cig_Object cig=Cig_Object.getInstance();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int nb= appWidgetIds.length;
        ctx=context;
        for (int i=0;i<nb; i++)
        {
            //Toast.makeText(ctx,"Today is:"+String.valueOf())+" Last cig: "+String.valueOf(cig.getDayOfYears()),Toast.LENGTH_LONG).show();
            SameDate();
            int widgetId=appWidgetIds[i];
            val=cig.getNbCig();
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            String s="0";
            if (val==0)
                remoteViews.setTextViewText(R.id.txtWidget,s);
            else
                remoteViews.setTextViewText(R.id.txtWidget, String.valueOf(val));

            Intent intent= new Intent(context,widgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.BtnAdd, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
            val+=1;
            cig.setNbCig(val);
            cig.setDayOfYears(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        }
    }

    /**
     * If the today's date is an other one than the last smoking date, the number of cigarettes will be reinitialize.
     * @return
     */
    public void SameDate()
    {
        if(cig.getDayOfYears()!=Calendar.getInstance().get(Calendar.DAY_OF_YEAR))
        {
            ConsommationManager cm= new ConsommationManager(ctx);
            UtilisateurManager u= new UtilisateurManager(ctx);
            Consommation c= new Consommation(Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.WEEK_OF_YEAR),Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            cm.addConso(c,u,cig.getNbCig());
            cig.setNbCig(1);
            cig.setDayOfYears(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
            val=1;
        }
    }
}
