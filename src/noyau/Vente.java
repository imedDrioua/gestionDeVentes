package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Vente extends Transaction {
    protected Personne client;
    protected ArrayList<Piece> piece_vendu;
    protected double prix_totale=0;


    public Vente(TypeDeTransaction type, Date date, Personne client, ArrayList<Piece> piece_vendu, double prix_totale) {
        super(type, date);
        this.client = client;
        this.piece_vendu = piece_vendu;
        this.prix_totale = prix_totale;
    }
}
