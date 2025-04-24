import javax.swing.*;

public class Main {
    //Variables
    boolean gameover = false;
    int winner;
    int turns = 0;

    FrameManager FrameManager;
    GameLogic GameLogic;

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    public void init() {
        GameLogic = new GameLogic(this);
        FrameManager = new FrameManager(this, GameLogic);

        FrameManager.init();
        new Timer(100, e -> FrameManager.gameLoop()).start();
    }

}
