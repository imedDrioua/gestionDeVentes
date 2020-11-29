package noyau;

public class Personne {
    protected String nom="";
    protected String prenom="";
    protected String adresse="";
    protected String teephone="";

    public Personne(String nom, String prenom, String adresse, String teephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.teephone = teephone;
    }
}
