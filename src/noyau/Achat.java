package noyau;

import java.util.ArrayList;

public class Achat extends Transaction{
    private Personne fournisseur;
    private ArrayList<Piece> pieces_achtes;
    private int prix_totale;
}
