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

public class TraitementJSONBoss extends AsyncTask<String, Void, Boolean>{

    List<Boss> lesBosss = new ArrayList<Boss>();

    Context context;
    JSONObject jObj = null;
    URL url;
    HttpURLConnection connexion;
    GestionBD sgbd;

    public TraitementJSONBoss(Context context){
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
            String ficBoss;
            ficBoss = lectureFichierDistant();
            Log.i("doInBack","le fichier distant : "+ficBoss);

            JSONObject jsonBoss = parseBoss(ficBoss);
            Log.i("doInBack", "le fichier json : "+jsonBoss);

            recBoss(jsonBoss);

            Log.i("doInBack", "nombre de boss : "+lesBosss.size());
            int num = 1;
            StringBuilder message = new StringBuilder("les descript_bosss : ");
            long id;

            for (Boss bos : lesBosss){
                message.append(bos.getId());
                message.append(" : ");
                message.append(bos.getNom());
                message.append(" : ");
                message.append(bos.getLvl());
                message.append(" : ");
                message.append(bos.getPvmin());
                message.append(" : ");
                message.append(bos.getPvmax());
                message.append(" : ");
                message.append(bos.getPa());
                message.append(" : ");
                message.append(bos.getPm());
                message.append("\n");
                num++;
                id = sgbd.ajoutBoss(bos);
            }

            Log.i("doInBack", "message : "+message);
            sgbd.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void recBoss(JSONObject jsonBoss){

        JSONArray lesBossss = null;

        try{
            lesBossss = jsonBoss.getJSONArray("Boss");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("recBoss","erreurJSArray");
        }

        for(int i = 0; i < lesBossss.length(); i++){
            JSONObject nuplet = null;
            String id, nom, lvl, pvmin, pvmax, pa, pm;
            Long idBD;
            Boss bos;

            try{
                nuplet = lesBossss.getJSONObject(i);

                id = nuplet.getString("id");
                nom = nuplet.getString("nom");
                lvl = nuplet.getString("lvl");
                pvmin = nuplet.getString("pvmin");
                pvmax = nuplet.getString("pvmax");
                pa = nuplet.getString("pa");
                pm = nuplet.getString("pm");

                bos = new Boss(Integer.parseInt(id), nom, Integer.parseInt(lvl), Integer.parseInt(pvmin), Integer.parseInt(pvmax), Integer.parseInt(pa), Integer.parseInt(pm));

                lesBosss.add(bos);

            } catch (JSONException e){
                e.printStackTrace();

            }
        }
    }

    private JSONObject parseBoss(String textBoss){

        if(textBoss != null){

            try{
                jObj = new JSONObject(textBoss);
            }catch(JSONException e){
                e.printStackTrace();
                Log.i("parbos", "erreurJSObj");
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

    public List<Boss> getLesBosss(){
        return lesBosss;
    }

    public String getLesNoms(){
        String liste = "";
        for (Boss bos : lesBosss){
            liste += bos.getNom()+"\n";
        }
        return liste;
    }
}
