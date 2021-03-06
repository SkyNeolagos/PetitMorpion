package sample.morpion;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import player.IA;
import player.Player;
import sample.controller.SceneController;


import java.io.IOException;


public class Morpion extends GridPane {//TODO améliorer complexité
    private SceneController sceneController;
    private Stage window;
    private Player currentPlayer;
    private Rule rule;
    private int optionJeux;
    private ImageView iconPlayer;
    private Cell[][] board;

    private Player tabPlayer[];

    //Fonction de contruction en fonction d'une Insatnce d'un Morpion pre-existant
    public Morpion(Morpion morpion,Cell[][] board){
        this.currentPlayer=morpion.currentPlayer;
        this.rule=new Rule();
        this.board=board;
    }
    //Fonction de contruction
    public Morpion(Stage window, SceneController sceneController, GridPane gridPane, ImageView iconPlayer, int optionJeux) {
        this.sceneController=sceneController;
        this.window=window;
        board = new Cell[3][3];
        this.rule=new Rule(board);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j]=new Cell(rule,this,true);
                gridPane.add(board[i][j],j,i);
            }
        }
        this.iconPlayer=iconPlayer;
        this.optionJeux=optionJeux;
        setupPlayer(optionJeux);
    }

    //Fonction remplissant le tableau de joueur en fonction du type de jeu choisie
    private void setupPlayer(int optionJeux){
        tabPlayer=new Player[3];
        //Ajout d'un arbitre
        Player arbitre=new Player(0);
        tabPlayer[0]=arbitre;

        //Ajout d'un joueur Humain
        Player j1=new Player("../imagesResources/profil/iconPeople.png",1);
        tabPlayer[1]=j1;
        this.currentPlayer=tabPlayer[1];
        this.iconPlayer.setImage(tabPlayer[1].getImage());

        //Humain vs Humain
        if(optionJeux==1) {
            //Ajout d'un deuxieme Humain
            Player j2 = new Player("../imagesResources/profil/iconAstronaut.png", 2);
            tabPlayer[2] = j2;
        }
        //Humain vs IA
        else{
                //Ajout d'une IA
                IA ia=new IA("../imagesResources/profil/iconAlien.png",2,this);
                tabPlayer[2]=ia;
        }
    }
    //Fonction lançeant la fonction move d'une IA
    //Lance la fonction move si le joueur est de type IA et qu'il est bien le joueur jouant actuellement
    public void game() throws IOException {
        if(currentPlayer==tabPlayer[1] && tabPlayer[1] instanceof IA){
            IA.Move move=tabPlayer[1].play();
            board[move.getI()][move.getJ()].handleClick();
        }
        if(currentPlayer==tabPlayer[2] && tabPlayer[2] instanceof IA){
            IA.Move move=tabPlayer[2].play();
            board[move.getI()][move.getJ()].handleClick();
        }
    }

    public Cell[][] getBoard() { return board; }
    public Rule getRule() {
        return rule;
    }
    public Player getPlayer() {
        return currentPlayer;
    }


    public static class Cell extends Pane {
        private Player player=new Player(0);
        private Rule rule;
        private Morpion morpion;
        private boolean option;

        public Cell(Cell cell){
            this.player=cell.player;
            this.option=false;
            this.rule=cell.rule;
            this.morpion=cell.morpion;
        }
        public Cell(Rule rule,Morpion morpion,boolean option) {
            this.rule=rule;
            setStyle("-fx-border-color: #303336");
            this.setPrefSize(300,300);
            //On lance la fonction handleClick si on clique sur la Cell
            this.setOnMouseClicked(e-> {
                try {
                    handleClick();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.morpion=morpion;
            this.option=option;
        }

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
            ImageView imageView=this.player.getImageView();
            imageView.setX(10);
            imageView.setY(10);
            imageView.setFitWidth(this.getWidth()-20);
            imageView.setFitHeight(this.getHeight()-20);
            getChildren().add(imageView);
        }


        //Fonction appliquant la prise de possesion de la case
        //Le joueur actuel peut prend possession de la case si elle est vide
        private void handleClick() throws IOException {
            //Si la case est vide
            if (player.getId()==0 && morpion.currentPlayer.getId()!=0){
                //On applique le Joueur actuel sur la case
                setPlayer(morpion.currentPlayer);
                //On verifie si il y a une vicroire grace a ce mouvement
                if(rule.victory(morpion.currentPlayer.getId())==morpion.currentPlayer.getId()){
                    Image imageWin=getImageWin(morpion.currentPlayer);
                    morpion.sceneController.goEndScene(morpion.window,imageWin);
                    morpion.currentPlayer=morpion.tabPlayer[0];
                }
                //On verifie si il ya une egalité
                else if(rule.equalityBetweenBothPlayer()){
                    Image imageDraw=getDrawImage();
                    morpion.sceneController.goEndScene(morpion.window,imageDraw);
                    morpion.currentPlayer=morpion.tabPlayer[0];
                }
                //Sinon on change de joueur en fonction du joueur actuel
                else{
                    switch (morpion.currentPlayer.getId()){
                        case 1:
                            morpion.currentPlayer=morpion.tabPlayer[2];
                            morpion.iconPlayer.setImage(morpion.tabPlayer[2].getImage());
                            break;
                        case 2:
                            morpion.currentPlayer=morpion.tabPlayer[1];
                            morpion.iconPlayer.setImage(morpion.tabPlayer[1].getImage());
                            break;
                    }
                    //On lance la fonction game si l'option est activé
                    //cette option permet d'éviter de lancer la fonction game quand on lance la fonction handleClick a partir de la fonction bestMove de l'IA
                    if(option){
                        morpion.game();
                    }
                }
            }
        }
        private Image getDrawImage(){
            return new Image(Player.class.getResourceAsStream("../imagesResources/draw.png"));
        }
        private Image getImageWin(Player player){
            if (player instanceof IA) {
                return new Image(Player.class.getResourceAsStream("../imagesResources/defaite.png"));
            }else{
                return new Image(Player.class.getResourceAsStream("../imagesResources/victoire.png"));
            }
        }
    }
}
