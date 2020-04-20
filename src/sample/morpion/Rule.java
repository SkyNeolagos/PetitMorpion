package sample.morpion;

public class Rule {
    private Morpion.Cell[][] board;

    public Rule(Morpion.Cell[][] board) {
        this.board = board;
    }

    boolean equalityBetweenBothPlayer(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j].getPlayer()==0){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkLine(int player){//TODO complexité a améliorer
        for (int i = 0; i < 3; i++) {
            if(board[i][0].getPlayer()==player && board[i][1].getPlayer()==player && board[i][2].getPlayer()==player){
                return true;
            }
        }
        return false;
    }
    boolean victory(int player){
        //TODO check diagonale
        if(checkLine(player)){
            System.out.println("Win");
            return true;
        }
        return false;
        //TODO check colonne
        //TODO check antidiagonale
    }
}
