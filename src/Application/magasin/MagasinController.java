package Application.magasin;

import Application.Controller;
import Bdd.BddConnection;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import noyau.Magasin;
import noyau.Utilisateur;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class MagasinController extends Controller implements Initializable {
    Magasin magasin;
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
    private JFXTreeTableView<Utilisateur> tableAdmin;


    @FXML
    private TreeTableColumn<Utilisateur, Integer> idRow;

    @FXML
    private TreeTableColumn<Utilisateur, String> nomRow;

    @FXML
    private TreeTableColumn<Utilisateur,String> prenomRow;

    @FXML
    private TreeTableColumn<Utilisateur,String> tlpRow;

    @FXML
    private TreeTableColumn<Utilisateur,String> adrRow;


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
                System.out.println( pr.executeUpdate());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                this.pr.close();
            }

        }

    }


   private void apply(){
       try {
           this.pr=this.connection.prepareStatement(sql);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tabDeTravaille.getTabs().remove(2);
        tabDeTravaille.getTabs().remove(1);

        ////////////////////////// Add textfields Listerners ///////////////////////////////////////////////////////////////////

        nomMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(nomMonCpt.getText().trim().equals(admin.getNom().trim())));
        prenomMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getPrenom().trim())));
        tlpMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getTelephone().trim())));
        adrMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getAdresse().trim())));
        mtpMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getMot_de_passe().trim())));

        nuNom.textProperty().addListener((observable, oldValue, newValue) -> nuBtn.setDisable(newValue.isEmpty() || nuMtp.textProperty().isEmpty().get()));
        nuMtp.textProperty().addListener((observable, oldValue, newValue) -> nuBtn.setDisable(newValue.isEmpty() || nuNom.textProperty().isEmpty().get()));
        ///////////////////////// Buttons Events ///////////////////////////////////////////////////////////////////////////////
        monCompteBtn.setOnMouseEntered(mouseEvent -> monCompteBtn.setStyle("-fx-background-color: Blue;-fx-background-radius: 30"));
        monCompteBtn.setOnMouseExited(mouseEvent -> monCompteBtn.setStyle("-fx-background-color: transparent"));
        ajouteUtilisateurBtn.setOnMouseEntered(mouseEvent -> ajouteUtilisateurBtn.setStyle("-fx-background-color: Blue;-fx-background-radius: 30"));
        ajouteUtilisateurBtn.setOnMouseExited(mouseEvent -> ajouteUtilisateurBtn.setStyle("-fx-background-color: transparent"));
        infoBtn.setOnMouseEntered(mouseEvent -> infoBtn.setStyle("-fx-background-color: Blue;-fx-background-radius: 30"));
        infoBtn.setOnMouseExited(mouseEvent -> infoBtn.setStyle("-fx-background-color: transparent"));
        infoBtn.setOnMouseEntered(mouseEvent -> infoBtn.setStyle("-fx-background-color: Blue;-fx-background-radius: 30"));
        infoBtn.setOnMouseExited(mouseEvent -> infoBtn.setStyle("-fx-background-color: transparent"));
        aboutBtn.setOnMouseEntered(mouseEvent -> aboutBtn.setStyle("-fx-background-color: Blue;-fx-background-radius: 30"));
        aboutBtn.setOnMouseExited(mouseEvent -> aboutBtn.setStyle("-fx-background-color: transparent"));
         //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////Table Admin//////////////////////////////////////////////////////////////////////////////
        idRow.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        nomRow.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        prenomRow.setCellValueFactory(new TreeItemPropertyValueFactory<>("prenom"));
        tlpRow.setCellValueFactory(new TreeItemPropertyValueFactory<>("telephone"));
        adrRow.setCellValueFactory(new TreeItemPropertyValueFactory<>("adresse"));
    }
    public void setData(){
        this.admin= magasin.getUtilisateur();
        nomMonCpt.setText(this.admin.getNom());
        prenomMonCpt.setText(this.admin.getPrenom());
        tlpMonCpt.setText(this.admin.getTelephone());
        mtpMonCpt.setText(this.admin.getMot_de_passe());
        adrMonCpt.setText(this.admin.getAdresse());
        TreeItem<Utilisateur> parent = new TreeItem<>();
     //   TreeItem<Utilisateur> item = new TreeItem<Utilisateur>(admin);
        Collection<TreeItem<Utilisateur>> items = new ArrayList<TreeItem<Utilisateur>>();
        items.add(new TreeItem(admin));
        parent.getChildren().addAll( items);
        tableAdmin.setRoot(parent);
        tableAdmin.setShowRoot(false);


    }
    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }
    @FXML
    private void monCompte(){
        tabDeTravaille.getTabs().add(monCompteTab);
        tabDeTravaille.getSelectionModel().select(monCompteTab);
    }
    @FXML
    private void nouveauUtilisateur(){
        tabDeTravaille.getTabs().add(nouveauUtilisateurTab);
        tabDeTravaille.getSelectionModel().select(nouveauUtilisateurTab);
    }
    @FXML
    private void catalogue(){
        tabDeTravaille.getTabs().add(catalogueAdminTab);
        tabDeTravaille.getSelectionModel().select(catalogueAdminTab);
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
}
