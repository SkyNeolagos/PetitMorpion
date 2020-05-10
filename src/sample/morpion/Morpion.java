package sample.morpion;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Morpion extends GridPane {//TODO améliorer complexité
    private int currentPlayer;
    private Rule rule;
    private ImageView iconPlayer;

    public Morpion(GridPane gridPane, ImageView iconPlayer){
        this.currentPlayer=1;
        this.iconPlayer=iconPlayer;
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

    public int getPlayer() {
        return currentPlayer;
    }
    public class Cell extends Pane {
        private int player=0;
        private Rule rule;

        public Cell(Rule rule) {
            this.rule=rule;
            setStyle("-fx-border-color: #303336");
            this.setPrefSize(300,300);
            this.setOnMouseClicked(e->handleClick());
        }
        int getPlayer(){
            return player;
        }
        public void setPlayer(int player) {
            //TODO Changer les icones
            this.player = player;
                if(this.player==1){
                    Line line1=new Line(10,10,this.getHeight()-10,10);
                    line1.endXProperty().bind(this.widthProperty().subtract(10));
                    line1.endYProperty().bind(this.heightProperty().subtract(10));

                    Line line2=new Line(10,10,this.getWidth()-10,this.getHeight()-10);
                    line2.endXProperty().bind(this.widthProperty().subtract(10));
                    line2.startYProperty().bind(this.heightProperty().subtract(10));

                    getChildren().addAll(line1,line2);
                }
                else if(this.player==2){
                    Ellipse ellipse=new Ellipse(this.getWidth()/2,this.getHeight()/2,this.getWidth()/2-10,this.getHeight()/2-10);
                    ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                    ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                    ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                    ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                    ellipse.setStroke(Color.BEIGE);
                    ellipse.setFill(Color.ROSYBROWN);
                getChildren().add(ellipse);
            }
        }

        private void handleClick(){
            if (player==0 && currentPlayer!=0){
                setPlayer(currentPlayer);
                if(rule.victory(currentPlayer)){
                    System.out.println("Victoire de : "+currentPlayer);
                    AlertBox.display("Félicitation ! Vous avez gagné la partie !"); //TODO Améliorer l'affichage de l'alertbox
                    currentPlayer=0;
                }
                else if(rule.equalityBetweenBothPlayer()){
                    System.out.println("Egalité");
                    currentPlayer=0;
                }
                else{
                    switch (currentPlayer){
                        case 1:
                            currentPlayer=2;
                            System.out.println("Changement Joueur");
                            break;
                        case 2:
                            currentPlayer=1;
                            System.out.println("Changement Joueur");
                            break;
                    }
                }
            }
        }
    }
}
