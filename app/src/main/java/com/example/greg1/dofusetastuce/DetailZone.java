package com.example.greg1.dofusetastuce;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by greg1 on 29/05/2018.
 */

public class DetailZone extends AppCompatActivity {

    TextView nomZoneTV;
    ArrayList<Integer> lesIds = new ArrayList<Integer>();
    ArrayList<String> lesValeurs = new ArrayList<String>();

    Integer idZone = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idZone = extras.getInt("id",0);
        }
        setContentView(R.layout.detailzone);

        GestionBD sgbd = new GestionBD(this);
        sgbd.open();
        lesIds = sgbd.donneLesIds();

        Integer zoneChoisi = idZone;
        Log.i("dans detailZone", "l'id : " + zoneChoisi);

        final Zone lechoix = sgbd.donneUneZone(zoneChoisi);



        nomZoneTV = (TextView) findViewById(R.id.nomZone);
        ListView listdjzone =(ListView) findViewById(R.id.listeDonjonZone);


        //--------------------------------------------------------

        nomZoneTV.setText("Nom : "+lechoix.getNom());


        //liste des donjons :
        lesValeurs = sgbd.donneLesDonjonIdzone(lechoix.getId());
        Log.i("liste" +
                "","les noms : "+lesValeurs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesValeurs);
        listdjzone.setAdapter(adapter);
        sgbd.close();

    }

}
