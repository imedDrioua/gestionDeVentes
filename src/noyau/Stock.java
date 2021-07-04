package noyau;


import java.util.Map;
import java.util.Set;

public class Stock {
    private Map<String,Piece> pieces_disponible;
    private Set<Piece> piece_a_achtes;
    private double factur=0;
    private double benifice=0;

    public Stock(Map<String,Piece> pieces_disponible, Set<Piece> piece_a_achtes) {
        this.pieces_disponible = pieces_disponible;
        this.piece_a_achtes = piece_a_achtes;
        this.calculerBenifice();
        this.calculerFactur();

    }
    public void calculerBenifice(){
       for (Piece piece : pieces_disponible.values()){
           this.benifice =+ piece.getBenifice_piece();
       }
    }
    public void calculerFactur(){
        for (Piece piece : pieces_disponible.values()){
            this.factur =+ piece.getFactur_piece();
        }
    }
    public void ajouterPieceAuStock(Piece piece){
        Piece pieceEx=pieces_disponible.put(piece.getReference(),piece);

          if( pieceEx  != null ){
              this.factur = this.factur - pieceEx.getFactur_piece();
              pieceEx.inccrementer(1);

        }
        this.factur = this.factur + piece.getFactur_piece();
    }
    public void retirerPieceDeStock(Piece piece){
        pieces_disponible.remove(piece.getReference());
    }

    public Map<String, Piece> getPieces_disponible() {
        return pieces_disponible;
    }

    public void setPieces_disponible(Map<String, Piece> pieces_disponible) {
        this.pieces_disponible = pieces_disponible;
    }

    public double getFactur() {
        return factur;
    }

    public double getBenifice() {
        return benifice;
    }
}
