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

    public String getTeephone() {
        return teephone;
    }

    public void setTeephone(String teephone) {
        this.teephone = teephone;
    }
}
