package noyau;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Personne extends RecursiveTreeObject<Utilisateur> {
    protected int id;
    protected String nom="";
    protected String prenom="";
    protected String adresse="";
    protected String telephone ="";

    public Personne(String nom, String prenom, String adresse, String telephone, int id) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }
}
