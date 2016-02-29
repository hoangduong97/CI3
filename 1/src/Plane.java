import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hoang Duong on 28/02/2016.
 */
public class Plane {
    private int positionX;
    private int positionY;
    private int damage;
    private int speed;
    private int health;
    private int direction;
    private BufferedImage sprite;
    private int type;

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    private void move() {
        if (this.type == 1) {
            if (this.direction == 1) {
                this.positionY -= this.speed;
            } else if (this.direction == 2) {
                this.positionY += this.speed;
            } else if (this.direction == 3) {
                this.positionX -= this.speed;
            } else if (this.direction == 4) {
                this.positionX += this.speed;
            }
        }
    }

    void update() {
        move();
    }

    void draw(Graphics g) {
        g.drawImage(this.sprite, this.positionX, this.positionY, null);
    }
}
