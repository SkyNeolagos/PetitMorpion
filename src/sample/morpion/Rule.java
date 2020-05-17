package sample.morpion;

public class Rule {
    private Morpion.Cell[][] board;

    public Rule() {}
    public Rule(Morpion.Cell[][] board) {
        this.board = board;
    }
    public void setBoard(Morpion.Cell[][] board) {
        this.board = board;
    }

    public boolean equalityBetweenBothPlayer(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j].getPlayer().getId()==0){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkLine(int player){//TODO complexité a améliorer
        for (int i = 0; i < 3; i++) {
            if(board[i][0].getPlayer().getId()==player && board[i][1].getPlayer().getId()==player && board[i][2].getPlayer().getId()==player){
                return true;
            }
        }
        return false;
    }

    private boolean checkColonne(int player){
        for(int i = 0; i < 3; i++){
            if(board[0][i].getPlayer().getId()==player && board[1][i].getPlayer().getId()==player && board[2][i].getPlayer().getId()==player){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonale(int player){
        if(board[0][0].getPlayer().getId()==player && board[1][1].getPlayer().getId()==player && board[2][2].getPlayer().getId()==player){
            return true;
        }
        return false;
    }

    private boolean checkAntiDiagonale(int player){
        if(board[2][0].getPlayer().getId()==player && board[1][1].getPlayer().getId()==player && board[0][2].getPlayer().getId()==player){
            return true;
        }
        return false;
    }


    public int victory(int player){
        if(checkLine(player)){
            return player;
        }
        if(checkColonne(player)){
            return player;
        }
        if(checkDiagonale(player)){
            return player;
        }
        if(checkAntiDiagonale(player)){
            return player;
        }
        return 0;

    }
}
