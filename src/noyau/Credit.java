package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Credit extends Vente{
    private double somme_remb;
    private double somme_rest;


    public Credit(Date date, Piece piece_vendu, double montant, int exmp, double main_oeuvre) {
        super(date, piece_vendu, montant, exmp, main_oeuvre);
    }
}
