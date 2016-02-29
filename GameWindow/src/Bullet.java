import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hoang Duong on 29/02/2016.
 */
public class Bullet {
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    private int positionX;
    private int positionY;
    private int speed;
    private BufferedImage sprite;

    void move() {
        this.positionY -= this.speed;
    }

    void draw(Graphics g) {
        g.drawImage(this.sprite, this.positionX, this.positionY, null);
    }
}
