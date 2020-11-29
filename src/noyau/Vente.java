package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Vente extends Transaction {
    protected Personne client;
    protected ArrayList<Piece> piece_vendu;
    protected double prix_totale=0;


    public Vente( Date date, Personne client, ArrayList<Piece> piece_vendu) {
        super(date);
        this.type = TypeDeTransaction.VENTE;
        this.client = client;
        this.piece_vendu = piece_vendu;
        calculerPrixTotale();

    }

    public void ajouterPieceVendu(Piece piece){
        this.piece_vendu.add(piece);
    }
    protected void calculerPrixTotale(){
        for(Piece piece : this.piece_vendu){
            this.prix_totale =+ piece.getPrix_de_vente();
        }
    }
}
