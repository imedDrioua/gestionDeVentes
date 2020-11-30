package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Credit extends Vente{
    private double somme_remb;
    private double somme_rest;

    public Credit( Date date, Personne client, ArrayList<Piece> piece_vendu, double prix_totale, double somme_remb) {
        super( date, client, piece_vendu);
        this.type = TypeDeTransaction.Credit;
        calculerPrixTotale();
        this.somme_remb = somme_remb;
        this.somme_rest = this.prix_totale - this.somme_remb;
    }

}
