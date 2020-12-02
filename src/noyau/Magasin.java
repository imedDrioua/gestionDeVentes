package noyau;

public class Magasin {

    private Utilisateur utilisateur;
    private Stock stock;
    private Carnet carnet_des_ventes;
    private Carnet carnet_des_achats;
    private Carnet carnet_de_credit;

    public Magasin(Stock stock, Carnet carnet_des_ventes, Carnet carnet_des_achats, Carnet carnet_de_credit) {
        this.stock = stock;
        this.carnet_des_ventes = carnet_des_ventes;
        this.carnet_des_achats = carnet_des_achats;
        this.carnet_de_credit = carnet_de_credit;
    }
    public Magasin(){}

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
