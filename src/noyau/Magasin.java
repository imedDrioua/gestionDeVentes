package noyau;

import java.util.ArrayList;
import java.util.Set;

public class Magasin {

    private Utilisateur admin;
    private ArrayList<Utilisateur> utilisateurs ;
    private Stock stock;
    private Set<Vente> carnet_des_ventes;
    private Carnet carnet_des_achats;
    private Carnet carnet_de_credit;
    private Set<Charge> charges ;

    public Magasin(Stock stock,  Carnet carnet_des_achats, Carnet carnet_de_credit,Set<Charge> charges) {
        this.stock = stock;
        this.carnet_des_achats = carnet_des_achats;
        this.carnet_de_credit = carnet_de_credit;
        this.charges = charges;
    }
    public Magasin(){}

    public void setUtilisateur(Utilisateur utilisateur) {
        this.admin = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return admin;
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Stock getStock() {
        return stock;
    }
    public Set<Vente> getCarnet_des_ventes() {return carnet_des_ventes;}

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setCarnet_des_ventes(Set<Vente> carnet_des_ventes) {
        this.carnet_des_ventes = carnet_des_ventes;
    }

    public Utilisateur getAdmin() {
        return admin;
    }

    public void setCharges(Set<Charge> charges) {
        this.charges = charges;
    }

    public Set<Charge> getCharges() {
        return charges;
    }
}
