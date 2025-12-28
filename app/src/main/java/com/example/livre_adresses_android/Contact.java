package com.example.livre_adresses_android;


public class Contact {

    private int id;
    private String nom;
    private String telephone;
    private boolean favori;

    // Constructeur complet
    public Contact(int id, String nom, String telephone, boolean favori) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.favori = favori;
    }

    // Constructeur sans id (pour l'ajout avant insertion en DB)
    public Contact(String nom, String telephone, boolean favori) {
        this.nom = nom;
        this.telephone = telephone;
        this.favori = favori;
    }

    // Getters et setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getTelephone() { return telephone; }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    public boolean isFavori() { return favori; }

    public void setFavori(boolean favori) { this.favori = favori; }
}