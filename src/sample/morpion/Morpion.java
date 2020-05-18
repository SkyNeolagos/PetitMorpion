package sample.morpion;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import player.IA;
import player.Player;


public class Morpion extends GridPane {//TODO améliorer complexité
    private Player currentPlayer;
    private Rule rule;
    private int optionJeux;
    private ImageView iconPlayer;
    private Cell[][] board;

    private Player tabPlayer[];

    public Morpion(Morpion morpion,Cell[][] board){
        this.currentPlayer=morpion.currentPlayer;
        this.rule=new Rule();
        this.board=board;
    }
    public Morpion(GridPane gridPane, ImageView iconPlayer,int optionJeux) {
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


    private void setupPlayer(int optionJeux){
        //Partie commune au deux type de partie
        tabPlayer=new Player[3];
        Player arbitre=new Player(0);
        tabPlayer[0]=arbitre;
        Player j1=new Player("../imagesResources/profil/iconPeople.png",1);
        tabPlayer[1]=j1;
        this.currentPlayer=tabPlayer[1];
        this.iconPlayer.setImage(tabPlayer[1].getImage());

        if(optionJeux==1) {
            //Humain vs Humain
            Player j2 = new Player("../imagesResources/profil/iconAstronaut.png", 2);
            tabPlayer[2] = j2;
        }else{
                //Humain vs IA ou Humain vs Humain
                IA ia=new IA("../imagesResources/profil/iconAlien.png",2,this);
                tabPlayer[2]=ia;
        }
    }

    public void game(){
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
            this.setOnMouseClicked(e->handleClick());
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

        private void handleClick(){
            if (player.getId()==0 && morpion.currentPlayer.getId()!=0){
                setPlayer(morpion.currentPlayer);
                if(rule.victory(morpion.currentPlayer.getId())==morpion.currentPlayer.getId()){
                    System.out.println("Victoire de : "+morpion.currentPlayer);
                    AlertBox.display("Félicitation ! Vous avez gagné la partie !"); //TODO Améliorer l'affichage de l'alertbox
                    morpion.currentPlayer=morpion.tabPlayer[0];
                }
                else if(rule.equalityBetweenBothPlayer()){
                    System.out.println("Egalité");
                    morpion.currentPlayer=morpion.tabPlayer[0];
                }
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
                    if(option){
                        morpion.game();
                    }
                }
            }
        }
    }
}
