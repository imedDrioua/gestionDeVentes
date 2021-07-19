package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Credit extends Vente{
    private double somme_remb=0;
    private double somme_restant=0;



    public Credit(Date date, Piece piece_vendu, double montant, int exmp, double main_oeuvre,double somme_remb) {
        super(date, piece_vendu, montant, exmp, main_oeuvre);
        this.somme_remb=somme_remb;
        this.somme_restant =( this.montant +this.main_oeuvre ) - this.somme_remb;

    }
}
