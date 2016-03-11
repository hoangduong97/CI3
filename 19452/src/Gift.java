import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by AsusA42F on 3/11/2016.
 */
public class Gift extends GameObject {
    private Gift() {

    }

    public Gift(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        try {
            this.sprite = ImageIO.read(new File("Resources/GIFT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(this.sprite, this.positionX, this.positionY, null);
    }

    public boolean gotGift(Rectangle rectangle) {
        Rectangle rectangle1 = new Rectangle(this.positionX, this.positionY, this.sprite.getWidth(), this.sprite.getHeight());
        return rectangle1.intersects(rectangle);
    }
}
