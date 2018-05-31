package com.example.greg1.dofusetastuce;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg1 on 28/05/2018.
 */

public class TraitementJSONZone extends AsyncTask<String, Void, Boolean>{


    List<Zone> lesZones = new ArrayList<Zone>();

    Context context;
    JSONObject jObj = null;
    URL url;
    HttpURLConnection connexion;
    GestionBD sgbd;

    public TraitementJSONZone(Context context){
        this.context = context;
        sgbd = new GestionBD(context);
    }

    @Override
    protected Boolean doInBackground(String... urls) {

        Log.i("doInBack", "le départ : ");
        sgbd.open();

        try {
            url = new URL(urls[0]);
            Log.i("doInBack", "le fichier distant : "+urls[0]);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        Boolean result = false;

        try {
            String ficZone;
            ficZone = lectureFichierDistant();
            Log.i("doInBack","le fichier distant : "+ficZone);

            JSONObject jsonZone = parseZone(ficZone);
            Log.i("doInBack", "le fichier json : "+jsonZone);

            recZone(jsonZone);

            Log.i("doInBack", "nombre de zones : "+lesZones.size());
            int num = 1;
            StringBuilder message = new StringBuilder("les descript_zones : ");
            long id;

            for (Zone zon : lesZones){
                message.append(zon.getId());
                message.append(" : ");
                message.append(zon.getNom());
                message.append("\n");
                num++;
                id = sgbd.ajoutZone(zon);
            }

            Log.i("doInBack", "message : "+message);
            sgbd.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void recZone(JSONObject jsonZone){

        JSONArray lesZoness = null;

        try{
            lesZoness = jsonZone.getJSONArray("Zone");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("reczon","erreurJSArray");
        }

        for(int i = 0; i < lesZoness.length(); i++){
            JSONObject nuplet = null;
            String id, nom;
            Long idBD;
            Zone zon;

            try{
                nuplet = lesZoness.getJSONObject(i);

                id = nuplet.getString("id");
                nom = nuplet.getString("nom");

                zon = new Zone(Integer.parseInt(id), nom);

                lesZones.add(zon);

            } catch (JSONException e){
                e.printStackTrace();

            }
        }
    }

    private JSONObject parseZone(String textZone){

        if(textZone != null){

            try{
                jObj = new JSONObject(textZone);
            }catch(JSONException e){
                e.printStackTrace();
                Log.i("parzon", "erreurJSObj");
            }
            return jObj;

        } else {
            return null;
        }
    }

    private String lectureFichierDistant(){

        StringBuilder builder = new StringBuilder();

        try{
            connexion = (HttpURLConnection) url.openConnection();
        } catch (IOException e){
            e.printStackTrace();
        }

        String line;
        BufferedReader br = null;

        try{
            br = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
        } catch (IOException e1){
            e1.printStackTrace();
        }

        try{
            while ((line = br.readLine()) != null){
                builder.append(line).append("\n");
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    public List<Zone> getLesZones(){
        return lesZones;
    }

    public String getLesNoms(){
        String liste = "";
        for (Zone zon : lesZones){
            liste += zon.getNom()+"\n";
        }
        return liste;
    }
}
