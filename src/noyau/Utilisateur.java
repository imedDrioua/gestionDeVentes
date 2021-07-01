package noyau;

public class Utilisateur extends Personne implements Comparable< Utilisateur>{
       private String mot_de_passe="";

       public Utilisateur(String nom, String prenom, String adresse, String teephone, String mot_de_passe,int id) {
              super(nom, prenom, adresse, teephone,id);
              this.mot_de_passe = mot_de_passe;
       }

       public String getMot_de_passe() {
              return mot_de_passe;
       }

       public void setMot_de_passe(String mot_de_passe) {
              this.mot_de_passe = mot_de_passe;
       }

       public boolean auth(String nomP,String mtpP){
              return (nomP.equals(this.nom) && mtpP.equals(this.mot_de_passe));
       }

       @Override
       public int compareTo(Utilisateur o) {
              if(this.id == o.id) return 0;
              else return 1;
       }
}
