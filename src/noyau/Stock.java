package noyau;

import java.util.ArrayList;

public class Stock {
    private ArrayList<Piece> pieces_disponible;
    private ArrayList<Piece> piece_a_achtes;
    private double faceteur=0;
    private double benifice=0;

    public Stock(ArrayList<Piece> pieces_disponible, ArrayList<Piece> piece_a_achtes, double faceteur, double benifice) {
        this.pieces_disponible = pieces_disponible;
        this.piece_a_achtes = piece_a_achtes;
        this.faceteur = faceteur;
        this.benifice = benifice;
    }
}
