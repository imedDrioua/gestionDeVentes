package noyau;

public class Piece {
    private String reference="";
    private String id="";
    private String designiation="";
    private double prix_de_vente=0;
    private double prix_de_achat=0;
    private int stock_disponible=0;

    public Piece(String reference, String designiation, double prix_de_vente, double prix_de_achat, int stock_disponible) {
        this.reference = reference;
        this.designiation = designiation;
        this.prix_de_vente = prix_de_vente;
        this.prix_de_achat = prix_de_achat;
        this.stock_disponible = stock_disponible;
    }
}
