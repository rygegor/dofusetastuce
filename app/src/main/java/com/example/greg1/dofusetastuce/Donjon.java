package com.example.greg1.dofusetastuce;

/**
 * Created by greg1 on 27/05/2018.
 */

public class Donjon {
    int id;
    String nom;
    String pos;
    int lvl;
    int idboss;
    int idzone;

    public Donjon(int id, String nom, String pos, int lvl, int idboss, int idzone) {
        this.id = id;
        this.nom = nom;
        this.pos = pos;
        this.lvl = lvl;
        this.idboss = idboss;
        this.idzone = idzone;
    }

    public Donjon(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPos() { return pos; }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getIdboss() {
        return idboss;
    }

    public void setIdboss(int idboss) {
        this.idboss = idboss;
    }

    public int getIdzone() {
        return idzone;
    }

    public void setIdzone(int idzone) {
        this.idzone = idzone;
    }
}
