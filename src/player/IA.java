package player;

import sample.morpion.Morpion;

import java.util.*;

public class IA extends Player {
    private Morpion plateau;
    private int id;
    private Player ennemi;

    public IA(String icon, int id,Morpion plateau) {
        super(icon, id);
        this.id=id;
        this.plateau=new Morpion(plateau,plateau.getBoard());
        this.ennemi=new Player("../imagesResources/iconSpaceNavet.png",1);

    }
    public Move play(){
        return bestMove(plateau.getBoard(),this);
    }
    private Player getAdversaire(Player player){
        Player adversaire;
        if(player.getId()==2){
            adversaire=ennemi;
        }else {
            adversaire=this;
        }
        return adversaire;
    }
    private void affichage(Morpion.Cell[][] board){
        System.out.println("-----------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j].getPlayer().getId()+" ");
            }
            System.out.println();
        }
        System.out.println("-----------");
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
    private Move bestMove(Morpion.Cell[][] board,Player player){
        //TODO Optimisation du premier coup avec une fonction de coupe sur les branches inutiles
        Player adversaire=getAdversaire(player);
        List<Move> collectionMove=new ArrayList<>();
        int score;
        for(int i=0;i<3;i++){
            for (int j=0; j<3;j++) {
                if(board[i][j].getPlayer().getId()==0){
                    Morpion.Cell[][] copyBoard=copyBoard(board);
                    copyBoard[i][j].setPlayer(player);
                    plateau.getRule().setBoard(copyBoard);
                    int win=plateau.getRule().victory(player.getId());
                    if(win==0 && plateau.getRule().equalityBetweenBothPlayer()) {
                        score=0;
                    }
                    else if(win==player.getId()){
                        score=1;
                    }
                    else{
                        score= -bestMove(copyBoard, adversaire).score;
                    }
                    Move move=new Move(i,j,score);
                    if(score==1){
                        return move;
                    }
                    collectionMove.add(move);
                }
            }
        }
        Collections.sort(collectionMove, (o1, o2) -> o2.score - o1.score);
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
