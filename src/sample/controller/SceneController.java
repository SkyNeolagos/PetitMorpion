package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.morpion.Morpion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML private BorderPane borderPane; //On recupere le BorderPane avec comme fx:id borderPane
    @FXML private ImageView iconPlayer; //On recupere l'ImageView avec comme fx:id iconPlayer
    @FXML private ImageView imageWin; //On recupere l'ImageView avec comme fx:id imageWin

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    //Fonction de fermeture de l'application
    public void closeAppEvent(ActionEvent event){
        System.exit(0);
    }

    //Fonction changeant la vue par celle de sceneParametreFXML
    public void goSetupScene(ActionEvent event) throws IOException {
        //On recupere la fenetre de l'application
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        //On recupere la ressource de notre vue
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../morpion/FXML/sceneParametreFXML.fxml"));
        //On applique le controller a notre vue si ce n'est pas déja fait
        if(loader.getController()==null){
            loader.setController(this);
        }
        //On charge notre vue
        Parent root = (Parent) loader.load();

        //On applique notre vue dans une scene
        Scene parameterScene=new Scene(root,800,600);
        //On applique la scene dans notre fenetre
        window.setScene(parameterScene);
        window.setResizable(false);
        window.show();
    }

    //Fonction changeant la vue par celle de sample
    public void goMenuScene(ActionEvent event) throws IOException {
        //On recupere la fenetre de l'application
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        //On recupere la ressource de notre vue
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        //On charge notre vue
        Parent root = (Parent) loader.load();

        //On applique notre vue dans une scene
        Scene parameterScene=new Scene(root,800,600);
        //On applique la scene dans notre fenetre
        window.setScene(parameterScene);
        window.setResizable(false);
        window.show();
    }

    //Fonction permettant de lancer le changement de vue par celle de gameView tout en avertissant du type de jeu
    //Mode choisie : Humain Vs Humain
    public void goStartGameHuman(ActionEvent event) throws IOException {
        goGameScene(event,1);
    }
    //Fonction permettant de lancer le changement de vue par celle de gameView tout en avertissant du type de jeu
    //Mode choisie : Humain Vs IA
    public void goStartGameIA(ActionEvent event) throws IOException {
        goGameScene(event,2);
    }

    //Fonction changeant notre vue actuelle par celle de gameView
    //Elle lance en parralele le morpion
    public void goGameScene(ActionEvent event,int optionJeux) throws IOException {
        //On recupere la fenetre de l'application
        Stage window =(Stage) ((Node)event.getSource()).getScene().getWindow();
        //On recupere la ressource de notre vue
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../morpion/FXML/gameView.fxml"));
        //On applique le controller a notre vue si ce n'est pas déja fait
        if(loader.getController()==null){
            loader.setController(this);
        }
        //On charge notre vue
        Parent root = (Parent) loader.load();
        //On creer un GridPane qui sera envoyer a notre morpion pour relié avec le plateau
        GridPane gPane=new GridPane();
        //On creer une instance de notre Morpion en envoyant le lien pour notre iconPlayer
        // (reprensente l'emplacement pour savoir quel joueur joue actuellement)
        //On passe aussi en arguemnt l'option de jeu.
        Morpion morpion=new Morpion(window,this,gPane,iconPlayer,optionJeux);
        //on applique notre gridpane precedemment modifié par notre morpion dans le borderPane
        borderPane.setCenter(gPane);

        //On applique notre vue dans une scene
        Scene scene=new Scene(root,800,600);
        //On applique la scene dans notre fenetre
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    public void goEndScene(Stage window,Image image) throws IOException {
        //On recupere la ressource de notre vue
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../morpion/FXML/alertbox.fxml"));
        //On applique le controller a notre vue si ce n'est pas déja fait
        if(loader.getController()==null){
            loader.setController(this);
        }

        //On charge notre vue
        Parent root = (Parent) loader.load();
        //On applique l'image message dans notre vue
        imageWin.setImage(image);
        //On applique notre vue dans une scene
        Scene scene = new Scene(root,800,600);
        //On applique la scene dans notre fenetre
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
