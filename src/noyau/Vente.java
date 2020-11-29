package noyau;

import java.util.ArrayList;

public class Vente extends Transaction {
    protected Personne client;
    protected ArrayList<Piece> piece_vendu;
    protected double prix_totale=0;
}
