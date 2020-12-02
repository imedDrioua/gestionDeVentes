package Application.login;

import Application.magasin.MagasinController;
import Bdd.BddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import noyau.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ImageView voiture;

    @FXML
    private Label logoLabel;

    @FXML
    private JFXTextField txfNomDeLutilisateur;

    @FXML
    private JFXPasswordField txfMtp;

    @FXML
    private JFXButton btnConnecter;
    @FXML
    private Label plaqueErreur;

    private Connection connection = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txfNomDeLutilisateur.setOnMouseClicked(mouseEvent -> plaqueErreur.setText(""));
        txfMtp.setOnMouseClicked(mouseEvent -> plaqueErreur.setText(""));
        logoLabel.setVisible(false);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.8), voiture);
        transition.setFromY(0);
        transition.setToY(-250);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
        transition.setOnFinished(actionEvent -> logoLabel.setVisible(true));
        txfMtp.setText("sari");
        txfNomDeLutilisateur.setText("imedq");
        connection = BddConnection.getConnection();


    }
    public void transitionDesComposants(Node element) {// Methode d'animation de 'Shake'
        int duration = 100;
        int count = 3;

        TranslateTransition transition1 = new TranslateTransition(Duration.millis(duration), element);
        transition1.setFromX(0);
        transition1.setToX(-5);
        transition1.setInterpolator(Interpolator.LINEAR);

        TranslateTransition transition2 = new TranslateTransition(Duration.millis(duration), element);
        transition2.setFromX(-5);
        transition2.setToX(5);
        transition2.setDelay(Duration.millis(duration));
        transition2.setInterpolator(Interpolator.LINEAR);
        transition2.setCycleCount(count);

        TranslateTransition transition3 = new TranslateTransition(Duration.millis(duration), element);
        transition3.setToX(0);
        transition3.setDelay(Duration.millis((count + 1) * duration));
        transition3.setInterpolator(Interpolator.LINEAR);

        transition1.play();
        transition2.play();
        transition3.play();
    }

    @FXML
    private void login() throws SQLException {
        if(this.connection != null){
            String mtp = this.txfMtp.getText();
            String nomDeLutilisateur = this.txfNomDeLutilisateur.getText();
            PreparedStatement pr = null;
            ResultSet rs = null;
            String sqlQuery="SELECT * FROM admin where nom = ? and mtp = ?";
            try {
                pr = this.connection.prepareStatement(sqlQuery);
                pr.setString(1,nomDeLutilisateur);
                pr.setString(2,mtp);
                rs = pr.executeQuery();
                if (rs.next()){
                    String prenom = rs.getString("prenom") ;
                    String id = rs.getString("ID");
                    String adresse= rs.getString("adress");
                    String telephone = rs.getString("telephone");
                    System.out.println("connecté");
                    Utilisateur admin = new Utilisateur(nomDeLutilisateur,prenom,adresse,telephone,mtp,id);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../magasin/magasin.fxml"));
                    Stage stage = new Stage();
                    Parent root = loader.load();
                    MagasinController magasinController=loader.getController();
                    magasinController.setAdmin(admin);
                    magasinController.setData();
                    Scene scene = new Scene(root);
                    stage.setTitle("Gestion de Vente");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    ((Stage)(btnConnecter.getScene().getWindow())).close();
                }else{
                    System.out.println("failled");
                    transitionDesComposants(txfMtp);
                    transitionDesComposants(txfNomDeLutilisateur);
                    plaqueErreur.setText("Utilisateur non trouvé");

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            } finally {
                pr.close();
                rs.close();

            }


        }
    }


}
