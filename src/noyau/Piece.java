package noyau;

import java.util.Objects;

public class Piece {
    private int id;
    private String reference="";
    private String designiation="";
    private double prix_de_vente=0;
    private double prix_de_achat=0;
    private int stock_disponible=0;
    private double totaleVente_piece =0;
    private double factur_piece=0;
    private double benifice_piece=0;

    public Piece(String reference, String designiation, double prix_de_vente, double prix_de_achat, int stock_disponible,int id) {
        this.id=id;
        this.reference = reference;
        this.designiation = designiation;
        this.prix_de_vente = prix_de_vente;
        this.prix_de_achat = prix_de_achat;
        this.stock_disponible = stock_disponible;
        calculerTotaleVente();
        calculerFactur();
        calculerBenifice();
    }
    public Piece(){

    }
    private void calculerTotaleVente(){
        this.totaleVente_piece = prix_de_vente * stock_disponible;
    }
    private void calculerBenifice(){
        this.benifice_piece = totaleVente_piece - factur_piece;
    }
    private  void calculerFactur(){
        this.factur_piece = prix_de_achat * stock_disponible;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return reference.equals(piece.reference);
    }
    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public void setDesigniation(String designiation) {
        this.designiation = designiation;
    }
    public void setPrix_de_vente(double prix_de_vente) {
        this.prix_de_vente = prix_de_vente;
    }
    public void setPrix_de_achat(double prix_de_achat) {
        this.prix_de_achat = prix_de_achat;
    }
    public void setStock_disponible(int stock_disponible) {
        this.stock_disponible = stock_disponible;
    }
    public String getReference() {
        return reference;
    }
    public String getDesigniation() {
        return designiation;
    }
    public double getPrix_de_vente() {
        return prix_de_vente;
    }
    public double getPrix_de_achat() {
        return prix_de_achat;
    }
    public int getStock_disponible() {
        return stock_disponible;
    }
    public double getTotaleVente_piece() {
        return totaleVente_piece;
    }
    public double getFactur_piece() {
        return factur_piece;
    }

    public int getId() {
        return id;
    }

    public double getBenifice_piece() {
        return benifice_piece;
    }
    public void inccrementer(int value){
        this.stock_disponible =this.stock_disponible+ value;
        this.calculer();
    }
    public void decrementer(int value){
        this.stock_disponible =this.stock_disponible - value;
        this.calculer();
    }
    public void calculer(){
        this.calculerTotaleVente();
        this.calculerFactur();
        this.calculerBenifice();
    }
}
