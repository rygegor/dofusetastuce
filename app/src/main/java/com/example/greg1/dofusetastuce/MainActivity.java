package com.example.greg1.dofusetastuce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //ImageView imgIntro;
    //TextView intro;
    Button boutonstart;
    private List<Donjon> lesDonjons;
    TraitementJSONDonjon traiteJSONDonjon;
    TraitementJSONBoss traiteJSONBoss;
    TraitementJSONZone traiteJSONZone;
    GestionBD sgbd;
    ArrayList<String> lesNoms = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lesDonjons = new ArrayList<Donjon>();

        traiteJSONDonjon = new TraitementJSONDonjon(this);
        traiteJSONBoss = new TraitementJSONBoss(this);
        traiteJSONZone = new TraitementJSONZone(this);

        sgbd = new GestionBD(this);
        sgbd.open();
        //supprime les tables si elles existent déjà
        sgbd.suppDonjon();
        sgbd.suppBoss();
        sgbd.suppZone();

        traiteJSONDonjon.execute("https://dofusetastuces.000webhostapp.com/JSON/JSONDonjon.json");
        traiteJSONBoss.execute("https://dofusetastuces.000webhostapp.com/JSON/JSONBoss.json");
        traiteJSONZone.execute("https://dofusetastuces.000webhostapp.com/JSON/JSONZone.json");

        sgbd.close();

        boutonstart = (Button) findViewById(R.id.bouton_donjon);
        boutonstart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent liste = new Intent(this, ListeDesDonjons.class);
        startActivity(liste);
    }
}
