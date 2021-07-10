package Application;

import Application.Controller;
import Bdd.BddConnection;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import noyau.*;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MagasinController extends Controller implements Initializable {
    Magasin magasin;
    Stock stock;
    Utilisateur admin ;
    Connection connection;
    PreparedStatement pr = null;
    String sql;
    String langue = "FR";
    ////Données des pieces /////////////////////
    ObservableList<Piece> piecesData;
    FilteredList<Piece> listPieceFiltrer;
    SortedList<Piece> sortedData ;
    ObservableList<Piece> piecesManquantes;
    ////////////////////////////////////////////
    Piece piece_de_vente=null;
    /////Données Ventes////////////////////////
    ObservableList<Vente> ventes;
    JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
    Vente ventAsupprimer = null;


    public void setSuggestions(JFXAutoCompletePopup<String> autoCompletePopup){
        this.autoCompletePopup = autoCompletePopup;
        autoCompletePopup.setSelectionHandler(event -> {
            refNvPiece.setText(event.getObject());

            // you can do other actions here when text completed
        });

    }
    @FXML
    private Label moncmpText;

    @FXML
    private Label nvAdminText;

    @FXML
    private Label catadminText;

    @FXML
    private Label catPieceText;

    @FXML
    private Label nvPieceText;

    @FXML
    private Label pieceManqText;

    @FXML
    private Label carnetVentText;


    @FXML
    private Label nvVenteText;

    @FXML
    private Label clientsText;

    @FXML
    private Tab magasinTab;

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
    private JFXTextField totalePiece;

    @FXML
    private JFXTextField venteTot;
    @FXML
    private JFXTextField factureTot;
    @FXML
    private JFXTextField benificeTot;


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
    private JFXButton supprVente;

    @FXML
    private Label messageVente;

    @FXML
    private AnchorPane colormsg;

    @FXML
    private JFXDatePicker dateVentdebut;

    @FXML
    private JFXDatePicker dateVentefin;

    @FXML
    private JFXButton filtrerVente;

    @FXML
    private JFXTextField totaleQuanti;

    @FXML
    private JFXTextField totaleMont;

    @FXML
    private JFXTextField totaleMain;

    @FXML
    private JFXTextField totaleBenifice;

    @FXML
    private Tab pieceManqueTab;

    @FXML
    private TableView<Piece> tablePieceManque;

    @FXML
    private TableColumn<Piece, String> refManqueRow;

    @FXML
    private TableColumn<Piece, String> desManqueRow;

    @FXML
    private TableColumn<Piece, Integer> stockManqueRow;
    @FXML
    private JFXCheckBox ecraserStock;

    @FXML
    void filtrerVente(ActionEvent event) throws ParseException {
        int totaleQuant = 0;
        double totaleMonta = 0;
        double totaleMaind = 0;
        double totaleBeni=0;

        if( ! (dateVentefin.getValue() == null && dateVentdebut.getValue() == null) ) {
            filtrerVente.setText(filtrerVente.getText().equals("Filtrer") ? "Annuler" : "Filtrer");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            filtrerVente.setStyle(filtrerVente.getText().equals("Filtrer") ? "-fx-background-color :  #9ACD32" : "-fx-background-color : #7400FF");
            if(filtrerVente.getText().equals("Annuler")) {
                Date dateDebut = formatter.parse(dateVentdebut.getValue() == null ? "2019-12-30 00:00:00" : dateVentdebut.getValue().toString().concat(" 00:00:00"));
                Date dateFin = formatter.parse(dateVentefin.getValue() == null ? "2025-12-30 00:00:00" : dateVentefin.getValue().toString().concat(" 00:00:00"));
               if( dateFin.compareTo(dateDebut) >= 0)
               {
                   ArrayList<Vente> ventesFiltred = new ArrayList<>();
                   for (Vente v : ventes) {
                       if (v.getDateO().compareTo(dateDebut) >= 0 && v.getDateO().compareTo(dateFin) <= 0) {
                           ventesFiltred.add(v);
                           totaleQuant = totaleQuant + v.getNombre_exmp();
                           totaleMonta = totaleMonta + v.getMontant();
                           totaleMaind = totaleMaind + v.getMain_oeuvre();
                           totaleBeni = totaleBeni + v.getFacture();
                       }
                   }
                   ObservableList<Vente> filtredVente = FXCollections.observableArrayList(ventesFiltred);
                   tableVente.setItems(filtredVente);
                   totaleQuanti.setText(String.valueOf(totaleQuant));
                   totaleMont.setText(String.valueOf((int)totaleMonta));
                   totaleMain.setText(String.valueOf((int)totaleMaind));
                   totaleBenifice.setText(String.valueOf((int)totaleBeni));
               }


            }else{
                totaleQuanti.setText("");
                totaleMont.setText("");
                totaleMain.setText("");
                totaleBenifice.setText("");
                tableVente.setItems(ventes);
                dateVentefin.setValue(null);
                dateVentdebut.setValue(null);

            }
        }else {
            tableVente.setItems(ventes);
            if(filtrerVente.getText().equals("Annuler") ){
                filtrerVente.setText("Filtrer");
                filtrerVente.setStyle("-fx-background-color :  #9ACD32" );

            }
        }

    }



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
            int prixV=Integer.parseInt(pvNvPiece.getText().trim().isEmpty() ? "0" : pvNvPiece.getText().trim());
            int prixA=Integer.parseInt(paNvPiece.getText().trim().isEmpty() ? "0" : paNvPiece.getText().trim());
            if(exPiece == null){
                String des=desNvPiece.getText().trim().toUpperCase();

                Piece nvPiece = new Piece(ref,des,prixV,prixA,nd,0);
                this.stock.ajouterPieceAuStock(nvPiece);
                this.sql="INSERT INTO stock(reference,designiation,prixVente,prixAchat,stockDisponible) VALUES(?,?,?,?,?)";
                try{
                    this.apply();
                    pr.setString(1,ref);
                    pr.setString(2,des);
                    pr.setString(3,String.valueOf(prixV));
                    pr.setString(4,String.valueOf(prixA));
                    pr.setString(5,String.valueOf(nd));
                    pr.executeUpdate();
                    autoCompletePopup.getSuggestions().add(ref);
                    this.updatePieces();
                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }finally {
                    pr.close();

                }
            }else{
              if(ecraserStock.isSelected() ) exPiece.setStock_disponible(nd); else exPiece.inccrementer(nd)  ;
                this.sql="UPDATE stock SET  designiation = ?, prixVente = ?, prixAchat = ?, stockDisponible = ? WHERE reference = ?";
                try {
                    String des = desNvPiece.getText().trim().isEmpty() ? exPiece.getDesigniation() : desNvPiece.getText().trim().toUpperCase();
                    this.apply();
                    this.pr.setString(1, des);
                    this.pr.setString(2, String.valueOf( prixV ));
                    this.pr.setString(3, String.valueOf(prixA));
                    this.pr.setString(4, String.valueOf(exPiece.getStock_disponible()));
                    this.pr.setString(5, exPiece.getReference());
                    if(this.pr.executeUpdate() >0){
                        exPiece.setDesigniation(des);
                        exPiece.setPrix_de_achat(prixA);
                        exPiece.setPrix_de_vente(prixV);
                        this.updatePieces();
                    }

                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }finally {
                    pr.close();
                }
            }
            refNvPiece.setText("");
            desNvPiece.setText("");
            pvNvPiece.setText("");
            paNvPiece.setText("");
            ndNvPiece.setText("");
            ecraserStock.setDisable(true);

        }

    }

    @FXML
    private void undoVente() throws SQLException {
        if(ventAsupprimer != null ){
            ventAsupprimer.getPiece().inccrementer(ventAsupprimer.getNombre_exmp());
            this.magasin.getCarnet_des_ventes().remove(ventAsupprimer);
            this.sql = "DELETE FROM vendre WHERE reference = ? AND date = ?";
            this.connection = BddConnection.getConnection();
            if(this.connection != null){
                this.apply();
                pr.setString(1,ventAsupprimer.getPiece_vendu());
                pr.setString(2,ventAsupprimer.getDate());
                if( pr.executeUpdate() > 0){
                    this.updateVentes();
                    this.updatePieces();
                    this.upDateAcceuil();
                    this.updatePieceManque();
                    supprVente.setDisable(true);
                    this.sql = "UPDATE stock SET stockDisponible = ? WHERE reference = ?";
                    this.apply();
                    pr.setInt(1,  ventAsupprimer.getPiece().getStock_disponible());
                    pr.setString(2,ventAsupprimer.getPiece_vendu());
                    pr.executeUpdate();
                    ventAsupprimer = null;
                }
                }
        }
        transitionDesComposants(supprVente);
    }
    @FXML
    private void validerNvVente() throws ParseException, SQLException {
        messageVente.setText("");
        if(quantityVente.textProperty().isEmpty().get() || montatnVente.textProperty().isEmpty().get() || refVente.textProperty().isEmpty().get() ){
            transitionDesComposants(quantityVente);
            transitionDesComposants(refVente);
            transitionDesComposants(montatnVente);
            messageVente.setText("Veuillez remplir les champs ");
        }else {
             Piece piecevendu = piece_de_vente == null ? stock.getPieces_disponible().get(refVente.getText()) : piece_de_vente;
             if (piecevendu != null){
            int quantity = Integer.parseInt(quantityVente.getText().trim());
            if (quantity > piecevendu.getStock_disponible() ) {
                transitionDesComposants(quantityVente);
                messageVente.setText("Quantité invalide ");
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String datestr = dtf.format(LocalDateTime.now());
                Date date = formatter.parse(datestr);
                double montant = Double.parseDouble(montatnVente.getText().trim());
                double main = Double.parseDouble(mainVente.getText().trim());
                Vente vente = new Vente(date, piecevendu, montant, quantity, main);
                piecevendu.decrementer(quantity);
                this.updatePieces();
                magasin.getCarnet_des_ventes().add(vente);
                this.connection = BddConnection.getConnection();
                if (this.connection != null) {
                    this.sql = "INSERT INTO vendre (reference,montant,date,exemplaire,mainOeuvre) VALUES (?,?,?,?,?)";
                    try {
                        this.apply();
                        pr.setString(1, refVente.getText());
                        pr.setDouble(2, montant);
                        pr.setString(3, datestr);
                        pr.setInt(4, quantity);
                        pr.setDouble(5, main);
                       if( pr.executeUpdate() > 0){ //Insertion dans le carnet a été ressui
                           this.updateVentes();
                           this.sql = "UPDATE stock SET stockDisponible = ? WHERE reference = ?";
                           this.apply();
                           pr.setInt(1, piecevendu.getStock_disponible());
                           pr.setString(2, piecevendu.getReference());
                           if(pr.executeUpdate()  > 0){
                               messageVente.setText("Vendu !");

                           }
                       }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } finally {
                        pr.close();
                    }

                }


            }
        }else {//Mal Refrence entrée
                 messageVente.setText("Verifier la reference !");

             }

        }

    }
    private void openTab(Tab tab)
    {
        if(admin.getPrenom().equals("Amar") || tab.getText().equals("Catalogue Piéces"))
        {
            if(! tabDeTravaille.getTabs().contains(tab)) {
                tabDeTravaille.getTabs().add(tab);
                tabDeTravaille.getSelectionModel().select(tab);
            }else tabDeTravaille.getSelectionModel().select(tab);
        }

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
        messageVente.setText("");
    }

    @FXML
    private void nouvellePiece(){
        ecraserStock.setSelected(false);
        ecraserStock.setDisable(true);
        openTab(nvPieceTab);
        refNvPiece.setText("");
        desNvPiece.setText("");
        pvNvPiece.setText("");
        paNvPiece.setText("");
        ndNvPiece.setText("");
    }

    @FXML
    private void  nvVenteBtn(){
        nouvelleVente();
        piece_de_vente=null;
        refVente.setText("");
        desVente.setText("");
        montatnVente.setText("");
        quantityVente.setText("");
        mainVente.setText("");
    }
    @FXML
    void onManqueBtn(MouseEvent event) {
        openTab(pieceManqueTab);

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
        this.closeTab(pieceManqueTab);


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
        this.addNumeriqueListener(montatnVente);
        this.addNumeriqueListener(quantityVente);
        this.addNumeriqueListener(mainVente);

        nuNom.textProperty().addListener((observable, oldValue, newValue) -> nuBtn.setDisable(newValue.isEmpty() || nuMtp.textProperty().isEmpty().get()));
        ndNvPiece.textProperty().addListener((observable, oldValue, newValue) -> nvPieceVBtn.setDisable(newValue.isEmpty() || refNvPiece.textProperty().isEmpty().get()));
        nuMtp.textProperty().addListener((observable, oldValue, newValue) -> nuBtn.setDisable(newValue.isEmpty() || nuNom.textProperty().isEmpty().get()));
        refNvPiece.textProperty().addListener((observable, oldValue, newValue) -> nvPieceVBtn.setDisable(newValue.isEmpty() || ndNvPiece.textProperty().isEmpty().get()));
        refNvPiece.textProperty().addListener(observable -> {
            autoCompletePopup.filter(string -> string.toLowerCase().contains(refNvPiece.getText().toLowerCase()));
            if (autoCompletePopup.getFilteredSuggestions().isEmpty() || refNvPiece.getText().isEmpty()) {
                autoCompletePopup.hide();
            } else {
                autoCompletePopup.show(refNvPiece);
            }
        });
        montatnVente.textProperty().addListener((observable, oldValue, newValue) -> validerVenteBtn.setDisable(newValue.isEmpty() || quantityVente.textProperty().isEmpty().get()));
        quantityVente.textProperty().addListener((observable, oldValue, newValue) -> {
            validerVenteBtn.setDisable(newValue.isEmpty()|| montatnVente.textProperty().isEmpty().get() );
            if(piece_de_vente != null)
            {
                montatnVente.setText(String.valueOf(Integer.parseInt(quantityVente.getText().trim().isEmpty() ? "0" : quantityVente.getText().trim()) * piece_de_vente.getPrix_de_vente()));
            }
        });
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
                if (event.getClickCount() == 2 && (! row.isEmpty()) && event.getButton().equals(MouseButton.PRIMARY) && admin.getPrenom().equals("Amar") ) {
                    closeTab(nvPieceTab);
                    messageVente.setText("");
                    Piece piece = row.getItem();
                    openTab(nvVenteTab);
                    refVente.setText(piece.getReference());
                    piece_de_vente = stock.getPieces_disponible().get(piece.getReference());
                    desVente.setText(piece.getDesigniation());
                    quantityVente.setText("1");
                    montatnVente.setText(String.valueOf((int)piece.getPrix_de_vente() ));
                    mainVente.setText("0");

                }
                if (event.getClickCount() == 1 && (! row.isEmpty()) && event.getButton().equals(MouseButton.SECONDARY) && admin.getPrenom().equals("Amar")) {

                    Piece piece = row.getItem();
                    openTab(nvPieceTab);
                    ecraserStock.setDisable(false);
                    ecraserStock.setSelected(false);
                    refNvPiece.setText(piece.getReference());
                    desNvPiece.setText(piece.getDesigniation());
                    paNvPiece.setText(String.valueOf((int)piece.getPrix_de_achat()));
                    pvNvPiece.setText(String.valueOf((int)piece.getPrix_de_vente()));
                    ndNvPiece.setText("0");

                }
            });
            return row ;
        });
        tableVente.setRowFactory( tv -> {
            TableRow<Vente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                supprVente.setDisable(false);
                // TODO Auto-generated method stub
               ventAsupprimer = row.getItem();

            });
            return row;
        });


        ////////////////////////Table Ventes///////////////////////////////////////////////////////////////////////////////
        referenceRowVente.setCellValueFactory(new PropertyValueFactory<Vente,String>("piece_vendu"));
        desRowVente.setCellValueFactory(new PropertyValueFactory<Vente,String>("des"));
        desRowVente.setCellFactory(WRAPPING_CELL_FACTORY_VENTES);
        prixVenteRowVente.setCellValueFactory(new PropertyValueFactory<Vente,Double>("montant"));
        mainRow.setCellValueFactory(new PropertyValueFactory<Vente,Double>("main_oeuvre"));
        dateRow.setCellValueFactory(new PropertyValueFactory<Vente,String>("date"));
        benificeRowVente.setCellValueFactory(new PropertyValueFactory<Vente,Double>("facture"));
        quantityRowVente.setCellValueFactory(new PropertyValueFactory<Vente,Integer>("nombre_exmp"));
         /////////////////////Table Piee Manquantes///////////////////////////////////////////////////////////////////////
        refManqueRow.setCellValueFactory(new PropertyValueFactory<Piece,String>("reference"));
        desManqueRow.setCellValueFactory(new PropertyValueFactory<Piece,String>("designiation"));
        stockManqueRow.setCellValueFactory(new PropertyValueFactory<Piece,Integer>("stock_disponible"));
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

this.arab();



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
        this.updatePieceManque();
        tablePiece.refresh();
        this.upDateAcceuil();
    }
    private void updateVentes(){
         ventes = FXCollections.observableArrayList(magasin.getCarnet_des_ventes());
         tableVente.setItems(ventes);
         this.upDateAcceuil();
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }
    private void apply(){
        if(admin.getPrenom().equals("Amar")) {
            try {
                this.pr = this.connection.prepareStatement(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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


                Vente vente = new Vente(formatter.parse(rs.getString("date")), stock.getPieces_disponible().get(rs.getString(1)), rs.getDouble(2),rs.getInt(5),rs.getDouble(6));

                ventes.add(vente);
            }
        }

         return  ventes;
    }
    private ArrayList<Piece> checkStock(){
        ArrayList<Piece> array=new ArrayList<>();
        for (Piece p : piecesData){
            if (p.getStock_disponible() <= 1) array.add(p);
        }
        return array;
    }
    private void updatePieceManque(){
        piecesManquantes = FXCollections.observableArrayList(checkStock());
        if(! piecesManquantes.isEmpty())
        {
            tablePieceManque.setItems(piecesManquantes);
            tablePieceManque.refresh();
        }
    }
    private void upDateAcceuil (){
        int nb=0;
        for(Piece piece : piecesData){
            nb = nb + piece.getStock_disponible();
        }
           stock.calculerFactur();
        stock.calculerBenifice();
        totalePiece.setText(String.valueOf(nb));
        factureTot.setText(String.valueOf(stock.getFactur()));
        benificeTot.setText(String.valueOf(stock.getBenifice()));
        venteTot.setText(String.valueOf(stock.getVentes()));
    }
    private void arab(){
        catalogueAdminTab.setText("المستخدمون");
        mesVentesTab.setText("المبيعات");
        monCompteTab.setText("حساب المستخدم");
        nouveauUtilisateurTab.setText("اضافة مستخدم");
        nvPieceTab.setText("اضافة قطعة");
        nvVenteTab.setText("اضافة بيع");
        pieceManqueTab.setText("قطع ناقصة");
        cataloguePiece.setText("قطع المخزن");
         moncmpText.setText("حسابي");
         nvAdminText.setText("اضافةمستخدم");
         catadminText.setText("المستخدمون");
         catPieceText.setText("قطع المخزن");
         nvPieceText.setText("اضافة قطعة");
         pieceManqText.setText("قطع ناقصة");
         carnetVentText.setText("دفتر المبيعات");
         nvVenteText.setText("اضافة بيع");
         clientsText.setText("العملاء");
         magasinTab.setText("المتجر");
    }

}
