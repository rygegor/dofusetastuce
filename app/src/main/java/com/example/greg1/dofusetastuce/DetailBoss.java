package com.example.greg1.dofusetastuce;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by greg1 on 29/05/2018.
 */

public class DetailBoss extends AppCompatActivity {

/*    private Integer[] imgBossId = {
            R.drawable.kankreblath,
            R.drawable.mob_leponge,
            R.drawable.bouftou_royal,
            R.drawable.kardorim,
            R.drawable.tournesol_affame
    };
*/

    TextView nomBossTV;
    TextView lvlBossTV;
    TextView pvBossTV;
    TextView paBossTV;
    TextView pmBossTV;
    TextView nomDonjonTV;
    ArrayList<Integer> lesIds = new ArrayList<Integer>();

    Integer idBoss = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idBoss = extras.getInt("id",0);
        }
        setContentView(R.layout.detailboss);

        GestionBD sgbd = new GestionBD(this);
        sgbd.open();
        lesIds = sgbd.donneLesIds();

        Integer bossChoisi = idBoss;
        Log.i("dans detailBoss", "l'id : " + bossChoisi);

        final Boss lechoix = sgbd.donneUnBoss(bossChoisi);

        Donjon leDonjon = sgbd.donneUnDonjonIdboss(lechoix.getId());

//        imageBossIV = (ImageView) findViewById(R.id.imageBoss);
//        imageBossIV.setImageResource(imgBossId[laPosition]);

        nomBossTV = (TextView) findViewById(R.id.nomBoss);
        lvlBossTV = (TextView) findViewById(R.id.lvlBoss);
        pvBossTV = (TextView) findViewById(R.id.pvBoss);
        paBossTV = (TextView) findViewById(R.id.paBoss);
        pmBossTV = (TextView) findViewById(R.id.pmBoss);

        nomDonjonTV = (TextView) findViewById(R.id.nomDonjonl);

        //--------------------------------------------------------

        nomBossTV.setText("Nom : "+lechoix.getNom());
        lvlBossTV.setText("Lvl : "+Integer.toString(lechoix.getLvl()));
        pvBossTV.setText("PV entre : "+Integer.toString(lechoix.getPvmin())+" et "+Integer.toString(lechoix.getPvmax()));
        paBossTV.setText("PA : "+Integer.toString(lechoix.getPa()));
        pmBossTV.setText("PM : "+Integer.toString(lechoix.getPm()));


        SpannableString contentDonjon = new SpannableString(leDonjon.getNom());
        nomDonjonTV.setText("Nom du Donjon : "+contentDonjon);

    }


}
