import com.sun.deploy.config.VerboseDefaultConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by hungtran on 3/1/16.
 */
public class Plane extends GameObject implements Subject{
    private Vector<Observer> vecTai = new Vector<Observer>();

    public Plane(){
        //khong co kieu tra ve
        //ten ham giong het ten Class
        this.positionX = 300;
        this.positionY = 300;
        this.speed = 4;
        try {
            this.sprite = ImageIO.read(new File("Resources/PLANE1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //new Plane(1,1,1,ImageIO.read(new FIle(adasdsa)))
    public Plane(int positionX, int positionY, int speed, int planeType){
        //co the co nhieu ham khoi tao co tham so
        //chi can khac nhau cac tham so truyen vao
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        switch (planeType){
            case 1:
                try {
                    this.sprite = ImageIO.read(new File("Resources/PLANE1.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    this.sprite = ImageIO.read(new File("Resources/PLANE2.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    this.sprite = ImageIO.read(new File("Resources/PLANE3.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    this.sprite = ImageIO.read(new File("Resources/PLANE4.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void shot(){
        Bullet bul = new Bullet(this.positionX + 30, this.positionY, 10);
        vecBul.add(bul);
    }
    public int getWidth(){
        Rectangle rec = new Rectangle();
        return sprite.getWidth();
    }

    public int getHeight(){
        return sprite.getHeight();
    }
    private Vector<Bullet> vecBul = new Vector<Bullet>();
    private static int totalHp = 100;
    private int hp = totalHp;
    private int planeType;
    private int dam;
    private int speed;

    private int direction;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPlaneType() {
        return planeType;
    }

    public void setPlaneType(int planeType) {
        this.planeType = planeType;
    }

    public int getDam() {
        return dam;
    }

    public void setDam(int dam) {
        this.dam = dam;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;

    }
    public void  move(int x, int y){
        this.positionX = x;
        this.positionY = y;
    }
    private void move(){
        switch (direction){
            case 0:
                //dung im
                break;
            case 1:
                this.positionY -= this.speed;
                break;
            case 2:
                this.positionY += this.speed;
                break;
            case 3:
                this.positionX -= this.speed;
                break;
            case 4:
                this.positionX += this.speed;
                break;
        }
    }

    public void update(){
        Vector<Bullet> tempVecBul = new Vector<>();
        for (Bullet bul : vecBul){
            if (bul.checkCollision()) {
                System.out.println("ban trung");
                tempVecBul.add(bul);
            }
            bul.update();
        }
        for (Bullet bul : tempVecBul) {
            vecBul.remove(bul);
        }
        move();

        Gift gift = GiftManager.getInstance().getGift();
        Rectangle rectPlane = new Rectangle(this.getPositionX(), this.getPositionY(), this.getSprite().getWidth(), this.getSprite().getHeight());
        if (GiftManager.getInstance().getGift().gotGift(rectPlane)) {
            notifiObserver();
        }
    }
    public void draw(Graphics g){
        g.drawImage(sprite,positionX,positionY,null);

        /* Ve thanh mau */
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        if (this.sprite.getWidth() > totalHp) {
            g2.drawRect(this.getPositionX() + (this.sprite.getWidth() - totalHp) / 2, this.getPositionY() - 10, this.hp, 5);
            g2.fillRect(this.getPositionX() + (this.sprite.getWidth() - totalHp) / 2, this.getPositionY() - 10, this.hp, 5);
        } else {
            g2.drawRect(this.getPositionX() - (totalHp - this.sprite.getWidth()) / 2, this.getPositionY() - 10, this.hp, 5);
            g2.fillRect(this.getPositionX() - (totalHp - this.sprite.getWidth()) / 2, this.getPositionY() - 10, this.hp, 5);
        }
        /* */

        for (Bullet bul : vecBul){
            bul.draw(g);
        }
    }

    @Override
    public void addObserver(Observer ob) {
        vecTai.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        vecTai.remove(ob);
    }

    @Override
    public void notifiObserver() {
        for (Observer ob : vecTai){
            ob.update("Bo Vua An Duoc Qua");
        }
    }
}
