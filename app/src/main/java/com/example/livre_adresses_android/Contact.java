package com.example.livre_adresses_android;

public class Contact {
    private int id;
    private String nom;
    private String telephone;
    private boolean favori;

    // Constructeur vide (parfois utile)
    public Contact() {
    }

    // Constructeur sans ID (pour la création d'un nouveau contact avant insertion en BDD)
    public Contact(String nom, String telephone, boolean favori) {
        this.nom = nom;
        this.telephone = telephone;
        this.favori = favori;
    }

    // Constructeur complet (pour la récupération depuis la BDD)
    public Contact(int id, String nom, String telephone, boolean favori) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.favori = favori;
    }

    // Getters et Setters
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    // Optionnel : pour afficher facilement le contact dans les logs
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", favori=" + favori +
                '}';
    }
}
