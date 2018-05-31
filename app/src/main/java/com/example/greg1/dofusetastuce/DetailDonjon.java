package com.example.greg1.dofusetastuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by greg1 on 28/05/2018.
 */

public class DetailDonjon extends AppCompatActivity{

    private Integer[] imgDjId = {
            R.drawable.kankreblath,
            R.drawable.mob_leponge,
            R.drawable.bouftou_royal,
            R.drawable.kardorim,
            R.drawable.tournesol_affame
    };

    ImageView imageDonjonIV;
    TextView nomDonjonTV;
    TextView posDonjonTV;
    TextView lvlDonjonTV;
    TextView nomBossTV;
    TextView nomZoneTV;
    ArrayList<String> lesNoms = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detaildonjon);

        GestionBD sgbd = new GestionBD(this);
        sgbd.open();
        lesNoms = sgbd.donneLesNoms();

        Intent intent = getIntent();
        int laPosition = intent.getIntExtra("leChoix", 0);

        //Toast.makeText(this, "le choix : " + laPosition, Toast.LENGTH_LONG).show();
        Log.i("dans detailDonjon", "la position : " + laPosition);

        String donjonChoisi = lesNoms.get(laPosition);
        Log.i("dans detailDonjon", "le nom : " + donjonChoisi);

        //------------------------------------------------------------------------

        final Donjon lechoix = sgbd.donneUnDonjon(donjonChoisi);

        Boss leBoss = sgbd.donneUnBoss(lechoix.getIdboss());
        Zone laZone = sgbd.donneUneZone(lechoix.getIdzone());

        imageDonjonIV = (ImageView) findViewById(R.id.imageDonjon);
        imageDonjonIV.setImageResource(imgDjId[laPosition]);

        nomDonjonTV = (TextView) findViewById(R.id.nomDonjon);
        posDonjonTV = (TextView) findViewById(R.id.posDonjon);
        lvlDonjonTV = (TextView) findViewById(R.id.lvlDonjon);

        nomBossTV = (TextView) findViewById(R.id.nomBoss);
        nomZoneTV = (TextView) findViewById(R.id.nomZone);

        //--------------------------------------------------------
        //afficher

        nomDonjonTV.setText("Nom : "+lechoix.getNom());
        posDonjonTV.setText("Pos : "+lechoix.getPos());
        lvlDonjonTV.setText("Lvl du donjon : "+Integer.toString(lechoix.getLvl()));

        //afficher en surlignant

        SpannableString contentBoss = new SpannableString(leBoss.getNom());
        contentBoss.setSpan(new UnderlineSpan(), 0, contentBoss.length(), 0);
        nomBossTV.setText("Nom du Boss : "+contentBoss);

        SpannableString contentZone = new SpannableString(laZone.getNom());
        contentZone.setSpan(new UnderlineSpan(), 0, contentZone.length(), 0);
        nomZoneTV.setText("Nom de la zone : "+contentZone);

        //----------------------------------------------------------
        //liens cliquables :

        nomBossTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailboss = new Intent(getBaseContext(), DetailBoss.class);
                detailboss.putExtra("id", lechoix.getIdboss());
                startActivity(detailboss);
            }
        });

        nomZoneTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailzone = new Intent(getBaseContext(), DetailZone.class);
                detailzone.putExtra("id", lechoix.getIdzone());
                startActivity(detailzone);
            }
        });

    }

}
