package noyau;

import java.util.ArrayList;
import java.util.Date;

public class Vente extends Transaction  {

    protected Piece piece_vendu;
    protected String des;
    protected int  nombre_exmp;
    protected double main_oeuvre=0;
    protected double montant=0;
    protected double benifice=0;

    public Vente( Date date, Piece piece_vendu,double montant,int exmp,double main_oeuvre) {
        super(date);
        this.type = TypeDeTransaction.VENTE;
        this.piece_vendu = piece_vendu;
        this.des = piece_vendu.getDesigniation();
        this. nombre_exmp = exmp;
        this.main_oeuvre = main_oeuvre;
        this.montant = (nombre_exmp * piece_vendu.getPrix_de_vente() )+ main_oeuvre;
        this.benifice = this.montant - (nombre_exmp * piece_vendu.getPrix_de_achat());
    }

    public String getPiece_vendu() {
        return piece_vendu.getReference();
    }

    public int getNombre_exmp() {
        return nombre_exmp;
    }

    public double getMain_oeuvre() {
        return main_oeuvre;
    }

    public double getMontant() {
        return montant;
    }

    public double getBenifice() {
        return benifice;
    }

    public String getDes() {
        return des;
    }
}
