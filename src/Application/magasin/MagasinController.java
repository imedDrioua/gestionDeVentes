package Application.magasin;

import Bdd.BddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import noyau.Utilisateur;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MagasinController implements Initializable {
    Utilisateur admin;
    Connection connection;
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
    private void validerModification() {
        this.connection= BddConnection.getConnection();
        if(this.connection != null){
            String sql="UPDATE admin SET nom = ?, mtp = ?, telephone = ?, adress = ?, prenom = ? WHERE ID = ?";
            try (PreparedStatement pr = this.connection.prepareStatement(sql)) {
                pr.setString(1, nomMonCpt.getText());
                pr.setString(2, mtpMonCpt.getText());
                pr.setString(3, tlpMonCpt.getText());
                pr.setString(4, adrMonCpt.getText());
                pr.setString(5, prenomMonCpt.getText());
                pr.setString(6, admin.getId());
                System.out.println(pr.executeUpdate());
                System.out.println(admin.getNom().hashCode());

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabDeTravaille.getTabs().remove(1);
        ////////////////////////// Add textfields Listerners ///////////////////////////////////////////////////////////////////

        nomMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(nomMonCpt.getText().trim().equals(admin.getNom().trim())));
        prenomMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getPrenom().trim())));
        tlpMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getTeephone().trim())));
        adrMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getAdresse().trim())));
        mtpMonCpt.textProperty().addListener((observable, oldValue, newValue) -> validerModification.setDisable(newValue.trim().equals(admin.getMot_de_passe().trim())));

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



    }
    public void setData(){
        nomMonCpt.setText(this.admin.getNom());
        prenomMonCpt.setText(this.admin.getPrenom());
        tlpMonCpt.setText(this.admin.getTeephone());
        mtpMonCpt.setText(this.admin.getMot_de_passe());
        adrMonCpt.setText(this.admin.getAdresse());
    }
    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }
    @FXML
    private void monCompte(){
        tabDeTravaille.getTabs().add(monCompteTab);
        tabDeTravaille.getSelectionModel().select(monCompteTab);
    }



}
