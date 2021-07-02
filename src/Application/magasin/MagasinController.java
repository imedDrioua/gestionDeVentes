package Application.magasin;

import Application.Controller;
import Bdd.BddConnection;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import noyau.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

public class MagasinController extends Controller implements Initializable {
    Magasin magasin;
    Stock stock;
    Utilisateur admin ;
    Connection connection;
    PreparedStatement pr = null;
    String sql;
    ////Données des pieces /////////////////////
    ObservableList<Piece> piecesData;
    FilteredList<Piece> listPieceFiltrer;
    SortedList<Piece> sortedData ;
    ////////////////////////////////////////////
    /////Données Ventes////////////////////////
    ObservableList<Vente> ventes;

    @FXML
    private AnchorPane infoBtn;


    @FXML
    private AnchorPane aboutBtn;

    @FXML
    private AnchorPane monCompteBtn;

    @FXML
    private AnchorPane ajouteUtilisateurBtn;

    @FXML
    private AnchorPane nvVenteBtb;

    @FXML
    private Tab monCompteTab;

    @FXML
    private Tab nouveauUtilisateurTab;

    @FXML
    private Tab catalogueAdminTab;

    @FXML
    private JFXTabPane tabDeTravaille;

    @FXML
    private JFXTextField nomMonCpt;

    @FXML
    private JFXTextField prenomMonCpt;

    @FXML
    private JFXPasswordField mtpMonCpt;

    @FXML
    private JFXTextField adrMonCpt;

    @FXML
    private JFXTextField tlpMonCpt;

    @FXML
    private JFXButton validerModification;

    @FXML
    private JFXTextField nuNom;

    @FXML
    private JFXTextField nuPrenom;

    @FXML
    private JFXTextField nuTlp;

    @FXML
    private JFXTextField nuAdr;

    @FXML
    private JFXPasswordField nuMtp;

    @FXML
    private JFXButton nuBtn;

    @FXML
    private TableView<Utilisateur> tableAdmin;


    @FXML
    private TableColumn<Utilisateur, Integer> idRow;

    @FXML
    private TableColumn<Utilisateur, String> nomRow;

    @FXML
    private TableColumn<Utilisateur,String> prenomRow;

    @FXML
    private TableColumn<Utilisateur,String> tlpRow;

    @FXML
    private TableColumn<Utilisateur,String> adrRow;

    @FXML
    private TableView<Piece> tablePiece;

    @FXML
    private TableColumn<Piece, String> referenceRow;

    @FXML
    private TableColumn<Piece, String> desRow;

    @FXML
    private TableColumn<Piece, Double> prixVenteRow;

    @FXML
    private TableColumn<Piece, Double> prixAchatRow;

    @FXML
    private TableColumn<Piece, Integer> stockRow;

    @FXML
    private TableColumn<Piece, Double> totaleVenteRow;

    @FXML
    private TableColumn<Piece, Double> totaleAchatRow;

    @FXML
    private TableColumn<Piece, Double> benificeRow;

    @FXML
    private AnchorPane cataloguePieceBtn;

    @FXML
    private AnchorPane nvPieceBtn;

    @FXML
    private AnchorPane printBtn;

    @FXML
    private AnchorPane carnetVenteBtn;

    @FXML
    private Tab cataloguePiece;

    @FXML
    private Tab nvPieceTab;

    @FXML
    private JFXTextField refNvPiece;

    @FXML
    private JFXTextField desNvPiece;

    @FXML
    private JFXTextField paNvPiece;

    @FXML
    private JFXTextField pvNvPiece;

    @FXML
    private JFXTextField ndNvPiece;


    @FXML
    private JFXButton nvPieceVBtn;


    @FXML
    private JFXTextField recherchePieceRef;

    @FXML
    private JFXTextField rechPieceDes;

    @FXML
    private JFXTextField rechPiecePrix;

    @FXML
    private Tab mesVentesTab;

    @FXML
    private TableView<Vente> tableVente;

    @FXML
    private TableColumn<Vente, String> referenceRowVente;

    @FXML
    private TableColumn<Vente, String> desRowVente;

    @FXML
    private TableColumn<Vente, Double> prixVenteRowVente;

    @FXML
    private TableColumn<Vente, Double> mainRow;

    @FXML
    private TableColumn<Vente, String> dateRow;

    @FXML
    private TableColumn<Vente, Double> benificeRowVente;

    @FXML
    private TableColumn<Vente, Integer> quantityRowVente;

    @FXML
    private Tab nvVenteTab;

    @FXML
    private JFXTextField refVente;

    @FXML
    private JFXTextField quantityVente;

    @FXML
    private JFXTextField montatnVente ;

    @FXML
    private JFXTextField mainVente;

    @FXML
    private JFXTextField desVente;

    @FXML
    private JFXButton validerVenteBtn;




    @FXML
    private void validerModification() throws SQLException {
        if(nomMonCpt.textProperty().isEmpty().get() || mtpMonCpt.textProperty().isEmpty().get()){
          transitionDesComposants(nomMonCpt);
          transitionDesComposants(monCompteBtn);
        }else {
            this.connection = BddConnection.getConnection();
            if (this.connection != null) {
             sql= "UPDATE admin SET nom = ?, mtp = ?, telephone = ?, adress = ?, prenom = ? WHERE ID = ?";
                try  {
                    this.apply();
                    pr.setString(1, nomMonCpt.getText());
                    pr.setString(2, mtpMonCpt.getText());
                    pr.setString(3, tlpMonCpt.getText());
                    pr.setString(4, adrMonCpt.getText());
                    pr.setString(5, prenomMonCpt.getText());
                    pr.setInt(6,admin.getId());
                   if( pr.executeUpdate() > 0 ) {
                        admin.setNom(nomMonCpt.getText());
                        admin.setMot_de_passe(mtpMonCpt.getText());
                        admin.setTelephone(tlpMonCpt.getText());
                        admin.setPrenom(prenomMonCpt.getText());
                        admin.setAdresse( adrMonCpt.getText());
                       this.updateUsers();
                    };
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    pr.close();
                }
            }
        }

    }
    @FXML
    private void validerNouveauUtilisateur() throws SQLException {
        this.connection=BddConnection.getConnection();
        if(this.connection != null){
            this.sql="INSERT INTO admin(nom,mtp,telephone,adress,prenom) VALUES(?,?,?,?,?)";
            try {
                this.apply();
                pr.setString(1,nuNom.getText());
                pr.setString(2,nuMtp.getText());
                pr.setString(3,nuTlp.getText());
                pr.setString(4,nuAdr.getText());
                pr.setString(5,nuPrenom.getText());
                if( pr.executeUpdate()==1){
                    Utilisateur nvUser = new Utilisateur(nuNom.getText(),nuPrenom.getText(),nuAdr.getText(),nuTlp.getText(),nuMtp.getText(),Controller.getIdAdmins() + 1);
                    magasin.getUtilisateurs().add(nvUser);
                }

               this.updateUsers();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                this.pr.close();
            }

        }

    }
    @FXML
    private void validerNvPiece() throws SQLException {
        this.connection=BddConnection.getConnection();
        if(this.connection != null){
            String ref=refNvPiece.getText().trim().toUpperCase();
            int nd=Integer.parseInt(ndNvPiece.getText().trim());
            Piece exPiece = this.stock.getPieces_disponible().get(ref);
            if(exPiece == null){
                String des=desNvPiece.getText().trim().toUpperCase();
                int prixV=Integer.parseInt(pvNvPiece.getText().trim());
                int prixA=Integer.parseInt(paNvPiece.getText().trim());
                Piece nvPiece = new Piece(ref,des,prixV,prixA,nd,0);
                this.stock.getPieces_disponible().put(ref,nvPiece);
                this.sql="INSERT INTO stock(reference,designiation,prixVente,prixAchat,stockDisponible) VALUES(?,?,?,?,?)";
                try{
                    this.apply();
                    pr.setString(1,ref);
                    pr.setString(2,des);
                    pr.setString(3,String.valueOf(prixV));
                    pr.setString(4,String.valueOf(prixA));
                    pr.setString(5,String.valueOf(nd));
                    pr.executeUpdate();

                    this.updatePieces();
                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }finally {
                    pr.close();
                }
            }else{
                exPiece.inccrementer(nd);
                this.sql="UPDATE stock SET reference = ?, designiation = ?, prixVente = ?, prixAchat = ?, stockDisponible = ? WHERE ID = ?";
                try {
                    String des = desNvPiece.getText().trim().isEmpty() ? exPiece.getDesigniation() : desNvPiece.getText().trim().toUpperCase();
                    this.apply();
                    this.pr.setString(1, ref);
                    this.pr.setString(2, des);
                    this.pr.setString(3, String.valueOf(exPiece.getPrix_de_vente()));
                    this.pr.setString(4, String.valueOf(exPiece.getPrix_de_achat()));
                    this.pr.setString(5, String.valueOf(exPiece.getStock_disponible()));
                    this.pr.setInt(6, exPiece.getId());
                    if(this.pr.executeUpdate() >0){
                        exPiece.setDesigniation(des);
                        this.updatePieces();
                    }

                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }finally {
                    pr.close();
                }
            }

        }

    }
    private void openTab(Tab tab)
    {
        if(! tabDeTravaille.getTabs().contains(tab)) {
            tabDeTravaille.getTabs().add(tab);
            tabDeTravaille.getSelectionModel().select(tab);
        }else tabDeTravaille.getSelectionModel().select(tab);
    }
    @FXML
    private void monCompte(){
       openTab(monCompteTab);
    }
    @FXML
    private void nouveauUtilisateur(){
      openTab(nouveauUtilisateurTab);


    }
    @FXML
    private void catalogue(){
       openTab( catalogueAdminTab);
    }
    @FXML
    private void cataloguePiece(){
        openTab(cataloguePiece);
    }
    @FXML
    private void carnetVente(){
        openTab(mesVentesTab);
    }
    private void nouvelleVente(){

        openTab(nvVenteTab);
    }

    @FXML
    private void nouvellePiece(){
       openTab(nvPieceTab);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.closeTab(nouveauUtilisateurTab);
        this.closeTab(monCompteTab);
        this.closeTab(catalogueAdminTab);
        this.closeTab(cataloguePiece);
        this.closeTab(nvPieceTab);
        this.closeTab(mesVentesTab);
        this.closeTab(nvVenteTab);


        ////////////////////////// Add textfields Listerners ///////////////////////////////////////////////////////////////////

        nomMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getNom().trim())));
        prenomMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getPrenom().trim())));
        tlpMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getTelephone().trim())));
        adrMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getAdresse().trim())));
        mtpMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getMot_de_passe().trim())));
        this.addNumeriqueListener(tlpMonCpt);
        this.addNumeriqueListener(paNvPiece);
        this.addNumeriqueListener(pvNvPiece);
        this.addNumeriqueListener(ndNvPiece);
        this.addNumeriqueListener(nuTlp);
        nuNom.textProperty().addListener((observable, oldValue, newValue) -> nuBtn.setDisable(newValue.isEmpty() || nuMtp.textProperty().isEmpty().get()));
        ndNvPiece.textProperty().addListener((observable, oldValue, newValue) -> nvPieceVBtn.setDisable(newValue.isEmpty() || refNvPiece.textProperty().isEmpty().get()));
        nuMtp.textProperty().addListener((observable, oldValue, newValue) -> nuBtn.setDisable(newValue.isEmpty() || nuNom.textProperty().isEmpty().get()));
        refNvPiece.textProperty().addListener((observable, oldValue, newValue) -> nvPieceVBtn.setDisable(newValue.isEmpty() || ndNvPiece.textProperty().isEmpty().get()));
        montatnVente.textProperty().addListener((observable, oldValue, newValue) -> validerVenteBtn.setDisable(newValue.isEmpty() || quantityVente.textProperty().isEmpty().get()));
        quantityVente.textProperty().addListener((observable, oldValue, newValue) -> validerVenteBtn.setDisable(newValue.isEmpty() || montatnVente.textProperty().isEmpty().get()));
        recherchePieceRef.textProperty().addListener((observable, oldValue, newValue) -> {
            listPieceFiltrer.setPredicate(piece -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String lowerCaseFilter = newValue.toLowerCase();

                return piece.getReference().toLowerCase().contains(lowerCaseFilter); // Filter matches first name.

            });
        });
        rechPieceDes.textProperty().addListener((observable, oldValue, newValue) -> {
            listPieceFiltrer.setPredicate(piece -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String lowerCaseFilter = newValue.toLowerCase();

                return piece.getDesigniation().toLowerCase().contains(lowerCaseFilter); // Filter matches first name.

            });
        });
        rechPiecePrix.textProperty().addListener((observable, oldValue, newValue) -> {
            listPieceFiltrer.setPredicate(piece -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                double prixRech = Double.parseDouble(newValue);

                return piece.getPrix_de_vente() == prixRech; // Filter matches first name.

            });
        });

        ///////////////////////// Buttons Events ///////////////////////////////////////////////////////////////////////////////
        this.addHoverStyle(monCompteBtn);
        this.addHoverStyle(ajouteUtilisateurBtn);
        this.addHoverStyle(infoBtn);
        this.addHoverStyle(aboutBtn);
        this.addHoverStyle(cataloguePieceBtn);
        this.addHoverStyle(nvPieceBtn);
        this.addHoverStyle(printBtn);
        this.addHoverStyle(carnetVenteBtn);
        this.addHoverStyle(nvVenteBtb);
        /////////////////////////Table Admin//////////////////////////////////////////////////////////////////////////////
        idRow.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        nomRow.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        prenomRow.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
        tlpRow.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("telephone"));
        adrRow.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("adresse"));
        /////////////////////////Table Piece///////////////////////////////////////////////////////////////////////////////
        referenceRow.setCellValueFactory(new PropertyValueFactory<Piece,String>("reference"));
        desRow.setCellValueFactory(new PropertyValueFactory<Piece,String>("designiation"));
        desRow.setCellFactory(WRAPPING_CELL_FACTORY);
        prixVenteRow.setCellValueFactory(new PropertyValueFactory<Piece,Double>("prix_de_vente"));
        prixAchatRow.setCellValueFactory(new PropertyValueFactory<Piece,Double>("prix_de_achat"));
        stockRow.setCellValueFactory(new PropertyValueFactory<Piece,Integer>("stock_disponible"));
        totaleAchatRow.setCellValueFactory(new PropertyValueFactory<Piece,Double>("factur_piece"));
        totaleVenteRow.setCellValueFactory(new PropertyValueFactory<Piece,Double>("totaleVente_piece"));
        benificeRow.setCellValueFactory(new PropertyValueFactory<Piece,Double>("benifice_piece"));
        tablePiece.setRowFactory( tv -> {
            TableRow<Piece> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Piece piece = row.getItem();
                    openTab(nvVenteTab);
                    refVente.setText(piece.getReference());
                    desVente.setText(piece.getDesigniation());
                    quantityVente.setText("1");
                    montatnVente.setText(String.valueOf(piece.getPrix_de_vente() * Double.valueOf(quantityVente.getText())));
                    mainVente.setText("0");

                }
            });
            return row ;
        });

        ////////////////////////Table Ventes///////////////////////////////////////////////////////////////////////////////
        referenceRowVente.setCellValueFactory(new PropertyValueFactory<Vente,String>("piece_vendu"));
        desRowVente.setCellValueFactory(new PropertyValueFactory<Vente,String>("des"));
        prixVenteRowVente.setCellValueFactory(new PropertyValueFactory<Vente,Double>("montant"));
        mainRow.setCellValueFactory(new PropertyValueFactory<Vente,Double>("main_oeuvre"));
        dateRow.setCellValueFactory(new PropertyValueFactory<Vente,String>("date"));
        benificeRowVente.setCellValueFactory(new PropertyValueFactory<Vente,Double>("benifice"));
        quantityRowVente.setCellValueFactory(new PropertyValueFactory<Vente,Integer>("nombre_exmp"));





    }
    public void setData() throws SQLException, ParseException {
        this.admin= magasin.getUtilisateur();
        this.stock = magasin.getStock();
        magasin.setCarnet_des_ventes(this.loadVente());
        nomMonCpt.setText(this.admin.getNom());
        prenomMonCpt.setText(this.admin.getPrenom());
        tlpMonCpt.setText(this.admin.getTelephone());
        mtpMonCpt.setText(this.admin.getMot_de_passe());
        adrMonCpt.setText(this.admin.getAdresse());
        this.updateUsers();
        this.updatePieces();
        this.updateVentes();


    }
    public void updateUsers(){
        ObservableList<Utilisateur> adminsData = FXCollections.observableArrayList(this.magasin.getUtilisateurs());
        tableAdmin.setItems(adminsData);
        tableAdmin.refresh();
    }
    private void updatePieces(){
        piecesData = FXCollections.observableArrayList(stock.getPieces_disponible().values());
        listPieceFiltrer = new FilteredList<>(piecesData,p->true);
        sortedData = new SortedList<>(listPieceFiltrer);
        sortedData.comparatorProperty().bind(tablePiece.comparatorProperty());
        tablePiece.setItems(sortedData);
        tablePiece.refresh();
    }
    private void updateVentes(){
         ventes = FXCollections.observableArrayList(magasin.getCarnet_des_ventes());
         tableVente.setItems(ventes);
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }
    private void apply(){
        try {
            this.pr=this.connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    private void closeTab(Tab tab) {
        EventHandler<Event> handler = tab.getOnClosed();
        if (null != handler) {
            handler.handle(null);
        } else {
            tabDeTravaille.getTabs().remove(tab);
        }
    }
    private Set<Vente> loadVente() throws SQLException, ParseException {
        this.connection=BddConnection.getConnection();
        Set<Vente> ventes = new TreeSet<Vente>();
        if (this.connection != null) {
            String sql="SELECT * FROM vendre";
            PreparedStatement pr = this.connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()){
                System.out.println(formatter.parse(rs.getString("date")));

                Vente vente = new Vente(formatter.parse(rs.getString("date")), stock.getPieces_disponible().get(rs.getString(1)), rs.getDouble(2),rs.getInt(5),rs.getDouble(6));

                ventes.add(vente);
            }
        }

         return  ventes;
    }

}
