package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.morpion.Morpion;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private ImageView iconPlayer;
    @FXML private ImageView imageWin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeAppEvent(ActionEvent event){
        System.exit(0);
    }

    public void goSetupScene(ActionEvent event) throws IOException {
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../morpion/FXML/sceneParametreFXML.fxml"));
        if(loader.getController()==null){
            loader.setController(this);
        }
        Parent root = (Parent) loader.load();

        //Parent parameterParent=FXMLLoader.load(getClass().getResource("FXML/sceneParametreFXML.fxml"));
        Scene parameterScene=new Scene(root,800,600);
        window.setScene(parameterScene);
        window.setResizable(false);
        window.show();
    }
    public void goMenuScene(ActionEvent event) throws IOException {
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = (Parent) loader.load();

        //Parent menuParent=FXMLLoader.load(getClass().getResource("FXML/sample.fxml"));
        Scene parameterScene=new Scene(root,800,600);
        window.setScene(parameterScene);
        window.setResizable(false);
        window.show();
    }
    public void goStartGameHuman(ActionEvent event) throws IOException {
        goGameScene(event,1);
    }
    public void goStartGameIA(ActionEvent event) throws IOException {
        goGameScene(event,2);
    }
    public void goGameScene(ActionEvent event,int optionJeux) throws IOException {
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../morpion/FXML/gameView.fxml"));
        if(loader.getController()==null){
            loader.setController(this);
        }
        Parent root = (Parent) loader.load();

        GridPane gPane=new GridPane();
        Morpion morpion=new Morpion(window,this,gPane,iconPlayer,optionJeux);
        borderPane.setCenter(gPane);


        Scene scene=new Scene(root,800,600);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    public void goEndScene(Stage window,Image image) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../morpion/FXML/alertbox.fxml"));
        if(loader.getController()==null){
            loader.setController(this);
        }

        Parent root = (Parent) loader.load();
        imageWin.setImage(image);
        Scene scene = new Scene(root,300,200);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
