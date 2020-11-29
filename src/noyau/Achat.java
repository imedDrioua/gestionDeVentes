package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Achat extends Transaction{
    private Personne fournisseur;
    private ArrayList<Piece> pieces_achtes;
    private int prix_totale;


    public Achat(TypeDeTransaction type, Date date, Personne fournisseur, ArrayList<Piece> pieces_achtes, int prix_totale) {
        super(type, date);
        this.fournisseur = fournisseur;
        this.pieces_achtes = pieces_achtes;
        this.prix_totale = prix_totale;
    }
}
