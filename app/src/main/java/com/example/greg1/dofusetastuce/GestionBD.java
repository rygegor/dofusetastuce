package com.example.greg1.dofusetastuce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by greg1 on 28/05/2018.
 */

public class GestionBD {

    /* création base */

    private SQLiteDatabase maBase;
    private BDHelper monBDHelper;

    public GestionBD(Context context){
        monBDHelper = new BDHelper(context, "bddjdofus", null, 1);
    }

    public void open(){
        maBase = monBDHelper.getWritableDatabase();
    }

    public void close(){
        maBase.close();
    }

    /*
    Gestion table donjon------------------------------------------------------
    */

    public long ajoutDonjon(Donjon dj){
        ContentValues v = new ContentValues();
        v.put("id", dj.getId());
        v.put("nom", dj.getNom());
        v.put("pos", dj.getPos());
        v.put("lvl", dj.getLvl());
        v.put("idboss", dj.getIdboss());
        v.put("idzone", dj.getIdzone());
        return maBase.insert("Donjon",null, v);
    }

    public void suppDonjon(){
        maBase.delete("Donjon", null, null);
    }

    public ArrayList<String> donneLesNoms(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c = maBase.rawQuery("select nom from Donjon order by nom", null);

        while (c.moveToNext()) {
            liste.add(c.getString(0));
            System.out.println("nombre de ligne :"+c.getCount());
        }
        if(liste == null){
            liste.add("erreur de bdd !");
        }
        return liste;
    }

    public ArrayList<Integer> donneLesIds(){
        ArrayList<Integer> liste = new ArrayList<Integer>();
        Cursor c = maBase.rawQuery("select id from Donjon order by nom", null);

        while (c.moveToNext()) {
            liste.add(c.getInt(0));
            System.out.println("nombre de ligne :"+c.getCount());
        }
        if(liste == null){
            liste.add(0);
        }
        return liste;
    }

    public Donjon donneUnDonjon(String choix){
        Donjon leDonjon;

        String laRequete = "select id, nom, pos, lvl, idboss, idzone from Donjon where nom like '"+choix+"'";

        Cursor c = maBase.rawQuery(laRequete, null);

        if (c.moveToNext()){
            leDonjon = new Donjon(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5));
        } else {
            leDonjon = new Donjon(0, "erreurBd", "erreurBd", 0, 0, 0);
        }

        return leDonjon;
    }

    public Donjon donneUnDonjonIdboss(int idboss){
        Donjon leDonjon;

        String laRequete = "select id, nom, pos, lvl, idboss, idzone from Donjon where idboss like '"+idboss+"'";

        Cursor c = maBase.rawQuery(laRequete, null);

        if (c.moveToNext()){
            leDonjon = new Donjon(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5));
        } else {
            leDonjon = new Donjon(0, "erreurBd", "erreurBd", 0, 0, 0);
        }

        return leDonjon;
    }


    public ArrayList<String> donneLesDonjonIdzone(int idzone){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c = maBase.rawQuery("select nom from Donjon where idzone like "+idzone+" order by nom", null);

        while (c.moveToNext()) {
            liste.add(c.getString(0));
            System.out.println("nombre de ligne :"+c.getCount());
        }
        if(liste == null){
            liste.add("erreur de bdd !");
        }
        return liste;
    }

    /*
    Gestion table Boss---------------------------------------------------------------
     */

    public long ajoutBoss(Boss boss){
        ContentValues v = new ContentValues();
        v.put("id", boss.getId());
        v.put("nom", boss.getNom());
        v.put("lvl", boss.getLvl());
        v.put("pvmin", boss.getPvmin());
        v.put("pvmax", boss.getPvmax());
        v.put("pa", boss.getPa());
        v.put("pm", boss.getPm());
        return maBase.insert("Boss",null, v);
    }

    public void suppBoss(){
        maBase.delete("Boss", null, null);
    }

    public Boss donneUnBoss(int idboss){
        Boss boss;

        String laRequete = "select id, nom, lvl, pvmin, pvmax, pa, pm from Boss where id like '"+idboss+"'";

        Cursor c = maBase.rawQuery(laRequete, null);

        if (c.moveToNext()){
            boss = new Boss(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6));
        } else {
            boss = new Boss(0, "erreurBdd", 0, 0, 0, 0, 0);
        }

        return boss;
    }

    /*
    Gestion table zone---------------------------------------------------
     */

    public long ajoutZone(Zone zone){
        ContentValues v = new ContentValues();
        v.put("id", zone.getId());
        v.put("nom", zone.getNom());
        return maBase.insert("Zone",null, v);
    }

    public void suppZone(){
        maBase.delete("Zone", null, null);
    }

    public Zone donneUneZone(int idzone){
        Zone zone;

        String laRequete = "select id, nom from Zone where id like '"+idzone+"'";

        Cursor c = maBase.rawQuery(laRequete, null);

        if (c.moveToNext()){
            zone = new Zone(c.getInt(0), c.getString(1));
        } else {
            zone = new Zone(0, "erreurBdd");
        }

        return zone;
    }

}
