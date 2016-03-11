import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Tdh4vnPC on 3/1/2016.
 */
public class Bullet extends GameObject{

    private int speed;
    private PlaneEnemy destroyedPlaneEnemy;
    private boolean isHitPlaneByMouse, isHitPlaneByKey;

    private Bullet() {
        positionX = 0;
        positionY = 0;
    }

    public Bullet(int positionX, int positionY, int speed) {//Alt + Inseart
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        try {
            this.sprite = ImageIO.read(new File("Resources/DAN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(){
        this.positionY -= this.speed;
    }

    public void update(){
        this.move();
        if (checkCollision()){
            Rectangle rectBullet = new Rectangle(this.positionX, this.positionY, this.sprite.getWidth(), this.sprite.getHeight());
            if (this.speed < 0) { // dan ban tu PlaneEnemy
                if (isHitPlaneByKey) {
                    PlaneManager.getInstance().getPlaneMoveByKey().setHp(PlaneManager.getInstance().getPlaneMoveByKey().getHp() - 3);
                }
                if (isHitPlaneByMouse) {
                    PlaneManager.getInstance().getPlaneMoveByMouse().setHp(PlaneManager.getInstance().getPlaneMoveByMouse().getHp() - 3);
                }
            } else { // dan ban tu Plane
                PlaneEnemyManager.getInstance().getVectorPlaneEnemy().remove(destroyedPlaneEnemy);
            }
        }
    }
    public boolean checkCollision(){
        Rectangle rectBullet = new Rectangle(this.positionX, this.positionY, this.sprite.getWidth(), this.sprite.getHeight());
        if (this.speed < 0) { // dan ban tu PlaneEnemy
            Rectangle rectPlaneKey =
                    new Rectangle(PlaneManager.getInstance().getPlaneMoveByKey().getPositionX()
                            ,PlaneManager.getInstance().getPlaneMoveByKey().getPositionY()
                            ,PlaneManager.getInstance().getPlaneMoveByKey().getWidth()
                            ,PlaneManager.getInstance().getPlaneMoveByKey().getHeight());
            Rectangle rectPlaneMouse =
                    new Rectangle(PlaneManager.getInstance().getPlaneMoveByMouse().getPositionX()
                            ,PlaneManager.getInstance().getPlaneMoveByMouse().getPositionY()
                            ,PlaneManager.getInstance().getPlaneMoveByMouse().getWidth()
                            ,PlaneManager.getInstance().getPlaneMoveByMouse().getHeight());
            isHitPlaneByKey = rectBullet.intersects(rectPlaneKey);
            isHitPlaneByMouse = rectBullet.intersects(rectPlaneMouse);
            return isHitPlaneByMouse | isHitPlaneByKey;
        } else { // dan ban tu Plane
            Vector<PlaneEnemy> planeEnemyVector = PlaneEnemyManager.getInstance().getVectorPlaneEnemy();
            for (PlaneEnemy planeEnemy : planeEnemyVector) {
                Rectangle rectPlaneEnemy = new Rectangle(planeEnemy.getPositionX(), planeEnemy.getPositionY(),
                        planeEnemy.getSprite().getWidth(), planeEnemy.getSprite().getHeight());
                if (rectBullet.intersects(rectPlaneEnemy)) {
                    destroyedPlaneEnemy = planeEnemy;
                    return true;
                }
            }
        }
        return false;
    }
    //Lay toa do cua 2 may bay
    //PlaneManager.getInstance()....


    public void draw(Graphics g){
        g.drawImage(sprite,positionX,positionY,null);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
