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
    BufferedImage backGround;
    BufferedImage plane1;
    int planeX = 150;
    int planeY = 550;
    int direction = 0;

    public GameWindow() {
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
            plane1 = ImageIO.read(new File("Resouces/PLANE1.png"));
        } catch (IOException e) {
        }
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backGround, 0, 0, null);
        g.drawImage(plane1, planeX, planeY, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            direction = 1;
        } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            direction = 2;
        } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            direction = 3;
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            direction = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        direction = 0;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            if (direction == 1) {
                planeY -= 5;
            } else if (direction == 2) {
                planeY += 5;
            } else if (direction == 3) {
                planeX -= 5;
            } else if (direction == 4) {
                planeX += 5;
            }
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
        planeX = e.getX();
        planeY = e.getY();
    }
}
