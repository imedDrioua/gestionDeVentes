package Application;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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


}
