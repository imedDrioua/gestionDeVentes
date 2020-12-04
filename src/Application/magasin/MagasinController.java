package Application.magasin;

import Application.Controller;
import Bdd.BddConnection;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import noyau.Magasin;
import noyau.Piece;
import noyau.Stock;
import noyau.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ResourceBundle;

public class MagasinController extends Controller implements Initializable {
    Magasin magasin;
    Stock stock;
    Utilisateur admin ;
    Connection connection;
    PreparedStatement pr = null;
    String sql;
    @FXML
    private AnchorPane infoBtn;


    @FXML
    private AnchorPane aboutBtn;

    @FXML
    private AnchorPane monCompteBtn;

    @FXML
    private AnchorPane ajouteUtilisateurBtn;

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
                    System.out.println(pr.executeUpdate());

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
        int id=nuMtp.hashCode();
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
    private void monCompte(){
        if(! tabDeTravaille.getTabs().contains(monCompteTab)) {
            tabDeTravaille.getTabs().add(monCompteTab);
            tabDeTravaille.getSelectionModel().select(monCompteTab);
        }else tabDeTravaille.getSelectionModel().select(monCompteTab);
    }
    @FXML
    private void nouveauUtilisateur(){
        if(! tabDeTravaille.getTabs().contains(nouveauUtilisateurTab)) {
            tabDeTravaille.getTabs().add(nouveauUtilisateurTab);
            tabDeTravaille.getSelectionModel().select(nouveauUtilisateurTab);

        }else {
            tabDeTravaille.getSelectionModel().select(nouveauUtilisateurTab);
        }

    }
    @FXML
    private void catalogue(){
        if(! tabDeTravaille.getTabs().contains(catalogueAdminTab)) {
            tabDeTravaille.getTabs().add(catalogueAdminTab);
            tabDeTravaille.getSelectionModel().select(catalogueAdminTab);
        }else     tabDeTravaille.getSelectionModel().select(catalogueAdminTab);
    }
    @FXML
    private void cataloguePiece(){
        if(! tabDeTravaille.getTabs().contains(cataloguePiece)) {
            tabDeTravaille.getTabs().add(cataloguePiece);
            tabDeTravaille.getSelectionModel().select(cataloguePiece);
        }else     tabDeTravaille.getSelectionModel().select(cataloguePiece);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.closeTab(nouveauUtilisateurTab);
        this.closeTab(monCompteTab);
        this.closeTab(catalogueAdminTab);
        this.closeTab(cataloguePiece);

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
        nuMtp.textProperty().addListener((observable, oldValue, newValue) -> nuBtn.setDisable(newValue.isEmpty() || nuNom.textProperty().isEmpty().get()));
        refNvPiece.textProperty().addListener((observable, oldValue, newValue) -> nvPieceVBtn.setDisable(newValue.isEmpty() || ndNvPiece.textProperty().isEmpty().get()));
        ndNvPiece.textProperty().addListener((observable, oldValue, newValue) -> nvPieceVBtn.setDisable(newValue.isEmpty() || refNvPiece.textProperty().isEmpty().get()));
        ///////////////////////// Buttons Events ///////////////////////////////////////////////////////////////////////////////
        this.addHoverStyle(monCompteBtn);
        this.addHoverStyle(ajouteUtilisateurBtn);
        this.addHoverStyle(infoBtn);
        this.addHoverStyle(aboutBtn);
        this.addHoverStyle(cataloguePieceBtn);
        this.addHoverStyle(nvPieceBtn);
        this.addHoverStyle(printBtn);
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




    }
    public void setData(){
        this.admin= magasin.getUtilisateur();
        this.stock = magasin.getStock();
        nomMonCpt.setText(this.admin.getNom());
        prenomMonCpt.setText(this.admin.getPrenom());
        tlpMonCpt.setText(this.admin.getTelephone());
        mtpMonCpt.setText(this.admin.getMot_de_passe());
        adrMonCpt.setText(this.admin.getAdresse());
        this.updateUsers();
        this.updatePieces();
    }
    public void updateUsers(){
        ObservableList<Utilisateur> adminsData = FXCollections.observableArrayList(this.magasin.getUtilisateurs());
        tableAdmin.setItems(adminsData);
    }
    private void updatePieces(){
        ObservableList<Piece> piecesData = FXCollections.observableArrayList(stock.getPieces_disponible().values());
        tablePiece.setItems(piecesData);
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

}
