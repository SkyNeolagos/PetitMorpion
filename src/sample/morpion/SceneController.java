package sample.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private ImageView iconPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("gameView.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();

        GridPane gPane=new GridPane();
        Morpion morpion=new Morpion(gPane,iconPlayer);
        borderPane.setCenter(gPane);


        Scene scene=new Scene(root,800,600);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
