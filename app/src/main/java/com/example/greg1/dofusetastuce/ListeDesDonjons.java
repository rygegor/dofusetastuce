package com.example.greg1.dofusetastuce;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by greg1 on 27/05/2018.
 */

public class ListeDesDonjons extends ListActivity implements AdapterView.OnItemClickListener {

    ArrayList<String> lesValeurs = new ArrayList<String>();
    ListView listNom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_activity);

        GestionBD sgbd = new GestionBD(this);
        sgbd.open();

        lesValeurs = sgbd.donneLesNoms();
        Log.i("liste" +
                "","les noms : "+lesValeurs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesValeurs);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        sgbd.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detaildonjon = new Intent(this, DetailDonjon.class);
        Log.i("clic", "après avoir cliqué : "+position);
        //Toast.makeText(this, "position : "+position, Toast.LENGTH_LONG).show();
        detaildonjon.putExtra("leChoix", position);
        startActivity(detaildonjon);
    }
}
