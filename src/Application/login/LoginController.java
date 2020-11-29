package Application.login;

import Bdd.BddConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import noyau.Transaction;

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
    private JFXTextField txfMtp;

    @FXML
    private JFXButton btnConnecter;
    @FXML
    private Label plaqueErreur;

    private Connection connection = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txfNomDeLutilisateur.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                plaqueErreur.setText("");
            }
        });
        txfMtp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                plaqueErreur.setText("");
            }
        });
        logoLabel.setVisible(false);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.8), voiture);
        double y = voiture.getLayoutY();
        transition.setFromY(0);
        transition.setToY(-250);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                logoLabel.setVisible(true);
            }
        });
        txfMtp.setText("");
        txfNomDeLutilisateur.setText("");
        try {
            connection = BddConnection.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void transitionDesComposants(Node composants) {// Methode d'animation de 'Shake'
        int duration = 100;
        int count = 3;

        TranslateTransition transition1 = new TranslateTransition(Duration.millis(duration), composants);
        transition1.setFromX(0);
        transition1.setToX(-5);
        transition1.setInterpolator(Interpolator.LINEAR);

        TranslateTransition transition2 = new TranslateTransition(Duration.millis(duration), composants);
        transition2.setFromX(-5);
        transition2.setToX(5);
        transition2.setDelay(Duration.millis(duration));
        transition2.setInterpolator(Interpolator.LINEAR);
        transition2.setCycleCount(count);

        TranslateTransition transition3 = new TranslateTransition(Duration.millis(duration), composants);
        transition3.setToX(0);
        transition3.setDelay(Duration.millis((count + 1) * duration));
        transition3.setInterpolator(Interpolator.LINEAR);

        transition1.play();
        transition2.play();
        transition3.play();
    }

    @FXML
    private void login()  {
        if(this.connection != null){
            String mtp = this.txfMtp.getText();
            String nomDeLutilisateur = this.txfNomDeLutilisateur.getText();
            PreparedStatement pr =null;
            ResultSet rs=null;
            String sqlQuery="SELECT * FROM admin where nom = ? and mtp = ?";
            try {
                pr = this.connection.prepareStatement(sqlQuery);
                pr.setString(1,nomDeLutilisateur);
                pr.setString(2,mtp);
                rs = pr.executeQuery();
                if (rs.next()){
                    System.out.println("connecté");
                }else{
                    System.out.println("failled");
                    transitionDesComposants(txfMtp);
                    transitionDesComposants(txfNomDeLutilisateur);
                    plaqueErreur.setText("Utilisateur non trouvé");

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
    }


}
