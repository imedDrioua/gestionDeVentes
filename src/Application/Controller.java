package Application;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import noyau.Piece;

public class Controller {

    public static void transitionDesComposants(Node element) {// Methode d'animation de 'Shake'
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
    private static int idAdmins;

    public static int getIdAdmins() {
        return idAdmins;
    }

    public static void setIdAdmins(int idAdmins) {
        Controller.idAdmins = idAdmins;
    }
    protected void addNumeriqueListener(JFXTextField textField){

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    protected void addHoverStyle(AnchorPane anchor){
        anchor.setOnMouseEntered(mouseEvent -> anchor.setStyle("-fx-background-color: Blue;-fx-background-radius: 30"));
        anchor.setOnMouseExited(mouseEvent -> anchor.setStyle("-fx-background-color: transparent"));
    }
    public static final Callback<TableColumn<Piece,String>, TableCell<Piece,String>> WRAPPING_CELL_FACTORY =
            new Callback<>() {

                @Override
                public TableCell<Piece, String> call(TableColumn<Piece, String> param) {
                    TableCell<Piece, String> tableCell = new TableCell<Piece, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            if (item == getItem()) return;
                            super.updateItem(item, empty);
                            if (item == null) {
                                super.setText(null);
                                super.setGraphic(null);
                            } else {
                                super.setText(null);
                                Label l = new Label(item);
                                l.setWrapText(true);
                                VBox box = new VBox(l);
                                l.heightProperty().addListener((observable, oldValue, newValue) -> {
                                    box.setPrefHeight(newValue.doubleValue() + 7);
                                    Platform.runLater(() -> this.getTableRow().requestLayout());
                                });
                                super.setGraphic(box);
                            }
                        }
                    };
                    return tableCell;
                }
            };


}
