package player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.morpion.AlertBox;
import sample.morpion.Morpion;
import sample.morpion.Rule;

import java.util.*;

public class IA extends Player {
    private Morpion plateau;
    private int id;

    public IA(String icon, int id,Morpion plateau) {
        super(icon, id);
        this.id=id;
        this.plateau=new Morpion(plateau,plateau.getBoard());
    }
    public Move play(){
        return bestMove(plateau.getBoard(),this.id);
    }
    private int getIdAdversaire(int id){
        int idAdversaire;
        if(id==1){
            idAdversaire=2;
        }else {
            idAdversaire=1;
        }
        return idAdversaire;
    }

    private Morpion.Cell[][] copyBoard(Morpion.Cell[][] board){
        Morpion.Cell[][] copyBoard=new Morpion.Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copyBoard[i][j]=new Morpion.Cell(board[i][j]);
            }
        }
        return copyBoard;
    }
    private Move bestMove(Morpion.Cell[][] board,int id){
        int idAdversaire=getIdAdversaire(id);
        int score=0;
        List<Move> collectionMove=new ArrayList<>();
        for(int i=0;i<3;i++){
            for (int j=0; j<3;j++) {
                if(board[i][j].getPlayer().getId()==0){//Case Vide
                    Morpion.Cell[][] copyBoard=copyBoard(board);
                    copyBoard[i][j].setPlayer(this);
                    plateau.getRule().setBoard(copyBoard);
                    int win=plateau.getRule().victory(id);
                    if(win==0 && plateau.getRule().equalityBetweenBothPlayer()) {
                        score=0;
                    }
                    else if(win==id){
                        score=1;
                    }
                    else{
                        score= score-bestMove(copyBoard, idAdversaire).score;
                    }
                    Move move=new Move(i,j,score);
                    if(score==1){
                        return move;
                    }
                    collectionMove.add(move);
                }
            }
        }
        Collections.sort(collectionMove, new Comparator<Move>() {
            @Override
            public int compare(Move o1, Move o2) {
                return o2.score-o1.score;
            }
        });
        return collectionMove.get(0);
    }

    public class Move{
        private int i;
        private int j;
        private int score;

        public Move(int i, int j, int score) {
            this.i = i;
            this.j = j;
            this.score = score;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }
}
