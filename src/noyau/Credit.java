package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Credit extends Vente{
    private double somme_remb=0;
    private double somme_rest=0;

    public Credit(TypeDeTransaction type, Date date, Personne client, ArrayList<Piece> piece_vendu, double prix_totale, double somme_remb, double somme_rest) {
        super(type, date, client, piece_vendu, prix_totale);
        this.somme_remb = somme_remb;
        this.somme_rest = somme_rest;
    }
}
