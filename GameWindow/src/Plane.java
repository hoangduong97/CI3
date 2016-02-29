import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoang Duong on 28/02/2016.
 */
public class Plane {
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

    private static final int maxBullets = 10;// maximum number of bullets on screen (depends on delay, bullet's speed, window height)
    private static final int delay = 30; // time between two bullets is (delay * 17) milliseconds

    private int positionX;
    private int positionY;
    private int damage;
    private int speed;
    private int health;
    private int direction;
    private BufferedImage sprite;
    private int type;
    private int counter = 0; // counter for delay between two bullets (1 counter unit = 17 milliseconds)
    private int numBullets = 0;
    private boolean isFull = false; // to use 'run-a-round array'
    private Bullet[] bullet = new Bullet[maxBullets];

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

        /* include bullets' move in plane's move */
        if (this.isFull) { // if some bullets ran out of the screen, use run-a-round array
            for (int i = 0; i < this.maxBullets; i++)
                this.bullet[i].move();
        } else { // else simulate bullets currently on screen
            for (int i = 0; i < this.numBullets; i++) {
                this.bullet[i].move();
            }
        }
    }

    private void fire() {
        this.bullet[this.numBullets] = new Bullet();
        try {
            this.bullet[this.numBullets].setSprite(ImageIO.read(new File("Resouces/DAN.png")));
        } catch (IOException e) {
        }
        this.bullet[this.numBullets].setSpeed(2);
        this.bullet[this.numBullets].setPositionX((this.positionX + (this.sprite.getWidth() / 2)) - (this.bullet[this.numBullets].getSprite().getWidth() / 2));
        this.bullet[this.numBullets].setPositionY(this.positionY - this.bullet[this.numBullets].getSprite().getHeight());
        this.numBullets++;
    }

    void update() {
        if (this.counter % delay == 0) // delay for (delay * 17) milliseconds before firing
            this.fire();
        if (this.numBullets == this.maxBullets) { // if some bullets ran out of the screen, use run-a-round array
            this.numBullets = 0;
            this.isFull = true;
        }
        this.move(); // simulate moves of plane and bullets
        this.counter++;
    }

    void draw(Graphics g) {
        g.drawImage(this.sprite, this.positionX, this.positionY, null);
        if (this.isFull) { // if some bullets ran out of the screen, use run-a-round array, print all bullets (maybe on screen or not)
            for (int i = 0; i < this.maxBullets; i++) {
                this.bullet[i].draw(g);
            }
        } else { // else print bullets currently on screen
            for (int i = 0; i < this.numBullets; i++) {
                this.bullet[i].draw(g);
            }
        }
    }
}
