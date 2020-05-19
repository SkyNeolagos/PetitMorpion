package player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
    //Chaine de cractere donnant le chemin pour accéder a l'icone
    private String iconChemin;
    private int id;
    //Attribut stockant en memoire l'icone sous forme d'Image
    private Image image;
    //Attribut stockant en memoire l'icone sous forme d'ImageView
    private ImageView imageView;

    //Fonction contructeur
    public Player(int id) {
        this.id = id;
    }

    //Fonction contructeur
    public Player(String iconChemin, int id) {
        this.iconChemin = iconChemin;
        this.id = id;
    }

    public String getIconChemin() {
        return iconChemin;
    }
    public Image getImage(){
        image = new Image(Player.class.getResourceAsStream(this.iconChemin));
        return image;
    }
    public ImageView getImageView(){
        image = new Image(Player.class.getResourceAsStream(this.iconChemin));
        imageView= new ImageView();
        imageView.setImage(image);
        return imageView;
    }

    public void setIconChemin(String iconChemin) {
        this.iconChemin = iconChemin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IA.Move play() {
        return null;
    }
}
