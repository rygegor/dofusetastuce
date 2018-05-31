package com.example.greg1.dofusetastuce;

/**
 * Created by greg1 on 27/05/2018.
 */

public class Boss {
    int id;
    String nom;
    int lvl;
    int pvmin;
    int pvmax;
    int pa;
    int pm;

    public Boss(int id, String nom, int lvl, int pvmin, int pvmax, int pa, int pm) {
        this.id = id;
        this.nom = nom;
        this.lvl = lvl;
        this.pvmin = pvmin;
        this.pvmax = pvmax;
        this.pa = pa;
        this.pm = pm;
    }

    public Boss(){
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

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getPvmin() {
        return pvmin;
    }

    public void setPvmin(int pvmin) {
        this.pvmin = pvmin;
    }

    public int getPvmax() {
        return pvmax;
    }

    public void setPvmax(int pvmax) {
        this.pvmax = pvmax;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }
}
