import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;

/**
 * Created by Hoang Duong on 27/02/2016.
 */
public class GameWindow extends Frame implements KeyListener, Runnable, MouseMotionListener {
    //start
    Graphics seconds;
    Image image;

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            seconds = image.getGraphics();
        }
        seconds.setColor(getBackground());
        seconds.fillRect(0, 0, getWidth(), getHeight());
        seconds.setColor(getForeground());
        paint(seconds);
        g.drawImage(image, 0, 0, null);
    }

    //end
    BufferedImage backGround;
    Plane plane1;
    Plane plane2;

    public GameWindow() {
        /* Hide mouse cursor */
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "cursor"));
        /* */

        plane1 = new Plane();
        plane1.setPositionX(150);
        plane1.setPositionY(300);
        plane1.setSpeed(3);
        plane1.setDirection(0);
        plane1.setType(1);

        plane2 = new Plane();
        plane2.setPositionX(150);
        plane2.setPositionY(150);
        plane2.setSpeed(5);
        plane2.setDirection(0);
        plane2.setType(2);

        this.setTitle("Game 1945");
        this.setSize(400, 640);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        try {
            backGround = ImageIO.read(new File("Resouces/Background.png"));
        } catch (IOException e) {
        }
        try {
            plane1.setSprite(ImageIO.read(new File("Resouces/plane1.png")));
        } catch (IOException e) {
        }
        try {
            plane2.setSprite(ImageIO.read(new File("Resouces/PLANE2.png")));
        } catch (IOException e) {
        }
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backGround, 0, 0, null);
        plane1.draw(g);
        plane2.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            plane1.setDirection(1);
        } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            plane1.setDirection(2);
        } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            plane1.setDirection(3);
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            plane1.setDirection(4);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        plane1.setDirection(0);
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            plane1.update();
            plane2.update();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        plane2.setPositionX(e.getX() - plane2.getSprite().getWidth() / 2);
        plane2.setPositionY(e.getY());
    }
}
