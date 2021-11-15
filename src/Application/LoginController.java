package Application;

import Bdd.BddConnection;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import noyau.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoginController extends Controller implements Initializable {
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
    private Utilisateur admin;



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
        txfMtp.setText("chiheb");
        txfNomDeLutilisateur.setText("chiheb");
        connection = BddConnection.getConnection();


    }


    @FXML
    private void login() {
        if(this.connection != null){
            try {
                ArrayList<Utilisateur> adminItems=this.getUtilisateurs();
                if (auth(txfNomDeLutilisateur.getText(),txfMtp.getText(),adminItems)){
                    Magasin magasin=new Magasin();
                    magasin.setUtilisateur(admin);
                    magasin.setUtilisateurs(adminItems);
                    JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
                    Stock stock = new Stock(this.loadStock(autoCompletePopup),new HashSet<>());
                    magasin.setStock(stock);
                    magasin.setCharges(this.loadCharges());
                    this.getAdminsId();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("magasin.fxml"));
                    Stage stage = new Stage();
                    Parent root = loader.load();
                    MagasinController magasinController=loader.getController();
                    magasinController.setMagasin(magasin);
                    magasinController.setSuggestions(autoCompletePopup);
                    magasinController.setData();
                    Scene scene = new Scene(root);
                    stage.setTitle("Gestion de Vente");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            magasinController.closeThreads();
                        }
                    });
                    stage.show();
                    ((Stage)(btnConnecter.getScene().getWindow())).close();
                }else{
                    transitionDesComposants(txfMtp);
                    transitionDesComposants(txfNomDeLutilisateur);
                    plaqueErreur.setText("Erreur !");

                }
            } catch (SQLException | IOException | ParseException | DocumentException throwables) {
                throwables.printStackTrace();
            }


        }
    }
    private boolean auth(String nom , String mtp,ArrayList<Utilisateur> users){
        for (Utilisateur user : users){
             if(user.auth(nom, mtp)) {
                 this.admin=user;
                 return true;
             }

        }
        return false;
    }
    private void getAdminsId() throws SQLException {
        this.connection = BddConnection.getConnection();
        String idQuery = "SELECT MAX(ID) AS LAST FROM admin";
        assert this.connection != null;
        PreparedStatement pr = this.connection.prepareStatement(idQuery);
        ResultSet rs = pr.executeQuery();
        int currentID = Integer.parseInt(rs.getString("LAST"));
        Controller.setIdAdmins(currentID );
        pr.close();

    }
    private ArrayList<Utilisateur> getUtilisateurs() throws SQLException {
        this.connection = BddConnection.getConnection();
        String sqlQuery="SELECT * FROM admin";
        assert this.connection != null;
        PreparedStatement pr = this.connection.prepareStatement(sqlQuery);
        ArrayList<Utilisateur> adminItems=new ArrayList<>();
        ResultSet rs = pr.executeQuery();
        while (rs.next()){
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String tel = rs.getString("telephone");
            String adresse=rs.getString("adress");
            String mtp = rs.getString("mtp");
            int id= rs.getInt("ID");
            Utilisateur user = new Utilisateur(nom,prenom,adresse,tel,mtp,id);
            adminItems.add(user);
        }
        pr.close();
        rs.close();
        return adminItems;

    }
    private Map<String, Piece> loadStock( JFXAutoCompletePopup<String> autoCompletePopup) throws SQLException {
        Map<String , Piece> pieceDisponible=new HashMap<>();
        String sql="SELECT * FROM stock";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        ResultSet rs = pr.executeQuery();
        while (rs.next()){
            String ref = rs.getString(1);
            String des = rs.getString(2);
            int pv = rs.getInt(3);
            int pa = rs.getInt(4);
            int sd = rs.getInt(5);
            int id = rs.getInt(6);
            Piece piece = new Piece(ref,des,pv,pa,sd,id);
            pieceDisponible.put(ref , piece);
            autoCompletePopup.getSuggestions().add(ref);

        }
        return pieceDisponible;
    }
    private Set<Charge> loadCharges() throws SQLException, ParseException {
        Set<Charge> charges = new TreeSet<>();
        String sql  = "SELECT * FROM charge";
        PreparedStatement pr = this.connection.prepareStatement(sql);
        ResultSet rs = pr.executeQuery();
        while (rs.next()){
            String name = rs.getString(1);
            String date = rs.getString(2);
            String somme = rs.getString(3);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Charge charge = new Charge(name,formatter.parse(date),Double.parseDouble(somme));
            charges.add(charge);
        }
        return charges;
    }



}
