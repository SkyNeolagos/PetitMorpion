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

    //Fonction trouvant l'ennemi en fonction d'un Player
    //Retourne un Player
    private Player getAdversaire(Player player){
        Player adversaire;
        if(player.getId()==2){
            adversaire=ennemi;
        }else {
            adversaire=this;
        }
        return adversaire;
    }

    //Fonction de copie d'un tableau
    //Retourne un tableau de type Morpion.Cell[][]
    private Morpion.Cell[][] copyBoard(Morpion.Cell[][] board){
        Morpion.Cell[][] copyBoard=new Morpion.Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copyBoard[i][j]=new Morpion.Cell(board[i][j]);
            }
        }
        return copyBoard;
    }

    //Fonction selectionnant le meilleur coup a jouer
    //Retourne un Move
    private Move bestMove(Morpion.Cell[][] board,Player player){
        //On recupere le Player adverse
        Player adversaire=getAdversaire(player);
        //On creer une liste de Move vide on y ajoutera les possiblités de Move
        List<Move> collectionMove=new ArrayList<>();
        int score;
        //On parcours le tableau pour classer chaque possiblitée
        for(int i=0;i<3;i++){
            for (int j=0; j<3;j++) {
                //Si la case est vide
                if(board[i][j].getPlayer().getId()==0){
                    //On copie notre tableau
                    Morpion.Cell[][] copyBoard=copyBoard(board);
                    //On joue sur notre copie
                    copyBoard[i][j].setPlayer(player);
                    plateau.getRule().setBoard(copyBoard);
                    //on verifie si il ya un gagnant
                    int win=plateau.getRule().victory(player.getId());
                    //Si il y egalité alors on met un score de 0
                    if(win==0 && plateau.getRule().equalityBetweenBothPlayer()) {
                        score=0;
                    }
                    //Sinon si il permet de gagner alors on met le score  a 1
                    else if(win==player.getId()){
                        score=1;
                    }
                    //Sinon , on le score avec l'opposé du score pour le joueur adverse
                    //en fonction du meilleur
                    else{
                        score= -bestMove(copyBoard, adversaire).score;
                    }
                    //un Move est represente par un i et un j indiquent la position sur le plateau
                    //et un score permettant de classer le Move par rapport aux autres
                    Move move=new Move(i,j,score);
                    //Si le score est 1, on joue le coup
                    if(score==1){
                        return move;
                    }
                    //Sinon on l'ajout dans la liste de coup possible
                    collectionMove.add(move);
                }
            }
        }
        //Une fois tous les coups presents dans la listes, on trie de maniere decroissante en fonction du score
        Collections.sort(collectionMove, (o1, o2) -> o2.score - o1.score);
        //On retourne le Move ayant le plus haut score
        return collectionMove.get(0);
    }


    public class Move{
        private int i;//Representant ça position sur l'axe des lignes
        private int j;//Representant ça position sur l'axe des colonnes
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
