package sample.morpion;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import player.IA;
import player.Player;

public class Morpion extends GridPane {//TODO améliorer complexité
    private Player currentPlayer;
    private Rule rule;
    private ImageView iconPlayer;

    private Player tabPlayer[];

    public Morpion(GridPane gridPane, ImageView iconPlayer){
        tabPlayer=new Player[3];
        Player arbitre=new Player(0);
        Player j1=new Player("../imagesResources/iconSpaceNavet.png",1);
        IA ia=new IA("../imagesResources/iconGears.png",2);
        tabPlayer[0]=arbitre;
        tabPlayer[1]=j1;
        tabPlayer[2]=ia;


        this.currentPlayer=tabPlayer[1];
        this.iconPlayer=tabPlayer[1].getImageView();
        Cell[][] board = new Cell[3][3];
        this.rule=new Rule(board);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j]=new Cell(rule);
                gridPane.add(board[i][j],j,i);
            }
        }
    }

    public Rule getRule() {
        return rule;
    }

    public Player getPlayer() {
        return currentPlayer;
    }
    public class Cell extends Pane {
        private Player player=new Player(0);
        private Rule rule;

        public Cell(Rule rule) {
            this.rule=rule;
            setStyle("-fx-border-color: #303336");
            this.setPrefSize(300,300);
            this.setOnMouseClicked(e->handleClick());
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
            if (player.getId()==0 && currentPlayer.getId()!=0){
                setPlayer(currentPlayer);
                if(rule.victory(currentPlayer.getId())){
                    System.out.println("Victoire de : "+currentPlayer);
                    AlertBox.display("Félicitation ! Vous avez gagné la partie !"); //TODO Améliorer l'affichage de l'alertbox
                    currentPlayer=tabPlayer[0];
                }
                else if(rule.equalityBetweenBothPlayer()){
                    System.out.println("Egalité");
                    currentPlayer=tabPlayer[0];
                }
                else{
                    switch (currentPlayer.getId()){
                        case 1:
                            currentPlayer=tabPlayer[2];
                            System.out.println("Changement Joueur");
                            break;
                        case 2:
                            currentPlayer=tabPlayer[1];
                            System.out.println("Changement Joueur");
                            break;
                    }
                }
            }
        }
    }
}
