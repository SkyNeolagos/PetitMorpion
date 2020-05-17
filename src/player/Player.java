package player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
    private String icon;
    private int id;

    public Player(int id) {
        this.id = id;
    }

    public Player(String icon, int id) {
        this.icon = icon;
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }
    public ImageView getImageView(){
        final ImageView imv = new ImageView();
        System.out.println(this.icon);
        final Image image2 = new Image(Player.class.getResourceAsStream(this.icon));
        imv.setImage(image2);
        return imv;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
