import java.util.Scanner;

/**
 * Created by Hoang Duong on 27/02/2016.
 */
public class Main {
    public static void main (String[] args) {
        GameWindow game = new GameWindow();
        Thread thread = new Thread(game);
        thread.start();
    }
}
