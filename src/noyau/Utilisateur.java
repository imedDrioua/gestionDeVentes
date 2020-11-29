package noyau;

public class Utilisateur extends Personne{
       private String mot_de_passe="";

       public Utilisateur(String nom, String prenom, String adresse, String teephone, String mot_de_passe) {
              super(nom, prenom, adresse, teephone);
              this.mot_de_passe = mot_de_passe;
       }

       public String getMot_de_passe() {
              return mot_de_passe;
       }

       public void setMot_de_passe(String mot_de_passe) {
              this.mot_de_passe = mot_de_passe;
       }
}
