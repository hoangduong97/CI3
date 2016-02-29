/**
 * Created by Hoang Duong on 27/02/2016.
 */
public class Main {
    public static void main(String[] args) {
        GameWindow game = new GameWindow();
        Thread thread = new Thread(game);
        thread.start();
    }
}


/*
    - Hidden cursor
    - Sua class Plane: 1 plane chay = chuot, 1 plane chay = phim
    - Xem lai tat ca thuoc tinh, dat access modifier tuong ung (public, private) (them getter, setter)
    - Ban dan
 */