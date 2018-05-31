package com.example.greg1.dofusetastuce;

/**
 * Created by greg1 on 27/05/2018.
 */

public class Zone {
    int id;
    String nom;

    public Zone(int id, String nom){
        this.id = id;
        this.nom = nom;
    }

    public Zone(){
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
}
