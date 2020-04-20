package sample.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public void closeAppEvent(ActionEvent event){
        System.exit(0);
    }

    public void goParameterScene(ActionEvent event) throws IOException {
        Parent parameterParent=FXMLLoader.load(getClass().getResource("sceneParametreFXML.fxml"));
        Scene parameterScene=new Scene(parameterParent,800,600);
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(parameterScene);
        window.setResizable(false);
        window.show();
    }
    public void goMenuScene(ActionEvent event) throws IOException {
        Parent menuParent=FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene parameterScene=new Scene(menuParent,800,600);
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(parameterScene);
        window.setResizable(false);
        window.show();
    }
    public void goGameScene(ActionEvent event) throws IOException {
        //TODO RENDRE PROPRE AFFICHAGE
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();

        GridPane pane=new GridPane();
        Morpion morpion=new Morpion(pane);

        BorderPane borderPane=new BorderPane();
        borderPane.setCenter(pane);

        Scene scene=new Scene(borderPane,600,600);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
