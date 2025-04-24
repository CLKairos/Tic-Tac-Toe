import javax.swing.*;

public class GameLogic {
    Main main;

    public GameLogic(Main main) {
        this.main = main;
    }

    public void gameoverCheck(JButton[] buttons) {
        String[][] winCombos = {
                { "0", "1", "2" }, { "3", "4", "5" }, { "6", "7", "8" },
                { "0", "3", "6" }, { "1", "4", "7" }, { "2", "5", "8" },
                { "0", "4", "8" }, { "2", "4", "6" }
        };

        for (String[] combo : winCombos) {
            int a = Integer.parseInt(combo[0]);
            int b = Integer.parseInt(combo[1]);
            int c = Integer.parseInt(combo[2]);

            String t1 = buttons[a].getText();
            String t2 = buttons[b].getText();
            String t3 = buttons[c].getText();

            if (t1.equals("X") && t2.equals("X") && t3.equals("X")) {
                main.gameover = true;
                main.winner = 0;
                return;
            }

            if (t1.equals("O") && t2.equals("O") && t3.equals("O")) {
                main.gameover = true;
                main.winner = 1;
                return;
            }
        }

        if (main.turns >= 9) {
            main.gameover = true;
            main.winner = -1;
        }
    }
}
