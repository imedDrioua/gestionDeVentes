package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Achat extends Transaction{
    private final Personne fournisseur;
    private final ArrayList<Piece> pieces_achtes;
    private double prix_totale=0;


    public Achat( Date date, Personne fournisseur, ArrayList<Piece> pieces_achtes) {
        super( date);
        this.type = TypeDeTransaction.ACHAT;
        this.fournisseur = fournisseur;
        this.pieces_achtes = pieces_achtes;
        calculerPrixTotale();

    }
    public void ajouterPieceAchtes(Piece piece){
        this.pieces_achtes.add(piece);
    }
    private void calculerPrixTotale(){
        for(Piece piece : this.pieces_achtes){
            this.prix_totale =+ piece.getPrix_de_vente();
        }
    }
}
