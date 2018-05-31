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

public class TraitementJSONDonjon extends AsyncTask<String, Void, Boolean>{


    List<Donjon> lesDonjons = new ArrayList<Donjon>();

    Context context;
    JSONObject jObj = null;
    URL url;
    HttpURLConnection connexion;
    GestionBD sgbd;

    public TraitementJSONDonjon(Context context){
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
            String ficDonjon;
            ficDonjon = lectureFichierDistant();
            Log.i("doInBack","le fichier distant : "+ficDonjon);

            JSONObject jsonDonjon = parseDonjon(ficDonjon);
            Log.i("doInBack", "le fichier json : "+jsonDonjon);

            recDonjon(jsonDonjon);

            Log.i("doInBack", "nombre de donjons : "+lesDonjons.size());
            int num = 1;
            StringBuilder message = new StringBuilder("les descript_donjons : ");
            long id;

            for (Donjon dj : lesDonjons){
                message.append(dj.getId());
                message.append(" : ");
                message.append(dj.getNom());
                message.append(" : ");
                message.append(dj.getPos());
                message.append(" : ");
                message.append(dj.getLvl());
                message.append(" : ");
                message.append(dj.getIdboss());
                message.append(" : ");
                message.append(dj.getIdzone());
                message.append("\n");
                num++;
                id = sgbd.ajoutDonjon(dj);
            }

            Log.i("doInBack", "message : "+message);
            sgbd.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void recDonjon(JSONObject jsonDonjon){

        JSONArray lesDonjonss = null;

        try{
            lesDonjonss = jsonDonjon.getJSONArray("Donjon");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("recdj","erreurJSArray");
        }

        for(int i = 0; i < lesDonjonss.length(); i++){
            JSONObject nuplet = null;
            String id, nom, pos, lvl, idboss, idzone;
            Long idBD;
            Donjon dj;

            try{
                nuplet = lesDonjonss.getJSONObject(i);

                id = nuplet.getString("id");
                nom = nuplet.getString("nom");
                pos = nuplet.getString("pos");
                lvl = nuplet.getString("lvl");
                idboss = nuplet.getString("idboss");
                idzone = nuplet.getString("idzone");

                dj = new Donjon(Integer.parseInt(id), nom, pos, Integer.parseInt(lvl), Integer.parseInt(idboss), Integer.parseInt(idzone));

                lesDonjons.add(dj);

            } catch (JSONException e){
                e.printStackTrace();

            }
        }
    }

    private JSONObject parseDonjon(String textDonjon){

        if(textDonjon != null){

            try{
                jObj = new JSONObject(textDonjon);
            }catch(JSONException e){
                e.printStackTrace();
                Log.i("pardj", "erreurJSObj");
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

    public List<Donjon> getLesDonjons(){
        return lesDonjons;
    }

    public String getLesNoms(){
        String liste = "";
        for (Donjon dj : lesDonjons){
            liste += dj.getNom()+"\n";
        }
        return liste;
    }

}
