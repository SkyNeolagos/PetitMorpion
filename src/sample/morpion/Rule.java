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

    private boolean checkColonne(int player){
        for(int i = 0; i < 3; i++){
            if(board[0][i].getPlayer()==player && board[1][i].getPlayer()==player && board[2][i].getPlayer()==player){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonale(int player){
        if(board[0][0].getPlayer()==player && board[1][1].getPlayer()==player && board[2][2].getPlayer()==player){
            return true;
        }
        return false;
    }

    private boolean checkantiDiagonale(int player){
        if(board[2][0].getPlayer()==player && board[1][1].getPlayer()==player && board[0][2].getPlayer()==player){
            return true;
        }
        return false;
    }


    boolean victory(int player){
        if(checkLine(player)){
            System.out.println("Win");
            return true;
        }
        if(checkColonne(player)){
            System.out.println("Win");
            return true;
        }
        if(checkDiagonale(player)){
            System.out.println("Win");
            return true;
        }
        if(checkantiDiagonale(player)){
            System.out.println("Win");
            return true;
        }
        return false;

    }
}
