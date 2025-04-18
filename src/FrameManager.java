import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FrameManager {
    Main Main;
    GameLogic GameLogic;

    //Window
    JFrame frame = new JFrame("Tic-Tac-Toe");

    //Game Board
    JPanel boardPanel = new JPanel();
    JButton[] button = new JButton[9];

    //Game Menu Bar
    JPanel barPanel = new JPanel();
    JLabel xLabel = new JLabel("X's Turn");
    JLabel oLabel = new JLabel("O's Turn");
    JLabel turnCountLabel = new JLabel();
    JButton menuButton = new JButton("Menu");

    //Title
    JPanel titlePanel = new JPanel();
    JLabel titleLabel = new JLabel("Tic-Tac-Toe");
    JButton playButton = new JButton("Play");
    JButton quitButton = new JButton("Quit");

    //Winner
    JPanel winnerPanel = new JPanel();
    JLabel winnerLabel = new JLabel();
    JButton menuButton2 = new JButton("Menu");

    public FrameManager(Main Main, GameLogic GameLogic) {
        this.Main = Main;
        this.GameLogic = GameLogic;
    }

    public void init(){
        //Game Menu Bar
        barPanel.add(menuButton);
        barPanel.add(xLabel);
        barPanel.add(turnCountLabel);
        turnCountLabel.setText("Turns: " + Main.turns);
        menuButton.addActionListener(this::menuButtonActionPerformed);
        barPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        barPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        //Game Board
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button[i].setFont(new Font("Font", Font.BOLD, 55));
            button[i].addActionListener(this::gameButtonActionPerformed);
            boardPanel.add(button[i]);
        }

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));

        //Title
        titlePanel.setLayout(new GridLayout(3,1));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        titleLabel.setFont(new Font("Font", Font.BOLD, 70));
        titlePanel.add(titleLabel);
        titlePanel.add(playButton);
        titlePanel.add(quitButton);
        playButton.setFont(new Font("Font", Font.BOLD, 75));
        quitButton.setFont(new Font("Font", Font.BOLD, 75));
        playButton.addActionListener(this::playButtonActionPerformed);
        quitButton.addActionListener(this::quitButtonActionPerformed);
        frame.add(titlePanel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Winner
        winnerPanel.add(winnerLabel);
        winnerPanel.add(menuButton2);
        menuButton2.addActionListener(this::menuButtonActionPerformed);
        menuButton2.setFont(new Font("Font", Font.BOLD, 75));
        winnerLabel.setFont(new Font("Font", Font.BOLD, 75));
        winnerPanel.setLayout(new GridLayout(2, 1));
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        //Window
        frame.setVisible(true);
        frame.setSize(720, 820);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void menuButtonActionPerformed(ActionEvent e) {
        frame.remove(boardPanel);
        frame.remove(barPanel);
        frame.remove(winnerPanel);
        frame.add(titlePanel);
        frame.revalidate();
        frame.repaint();
        clearBoard();
    }

    public void gameButtonActionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (Main.turns % 2 == 0) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        button.setEnabled(false);
        Main.turns++;
        turnCountLabel.setText("Turns: " + Main.turns);
        GameLogic.gameoverCheck(this.button);
    }

    public void playButtonActionPerformed(ActionEvent e) {
        frame.remove(titlePanel);
        frame.setLayout(new BorderLayout());
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(barPanel, BorderLayout.NORTH);
        frame.revalidate();
        frame.repaint();
        clearBoard();
    }

    public void quitButtonActionPerformed(ActionEvent e) {
        frame.setVisible(false);
    }

    public void clearBoard() {
        Main.turns = 0;
        turnCountLabel.setText("Turns: " + Main.turns);
        Main.gameover = false;
        for (JButton b : button) {
            b.setText("");
            b.setEnabled(true);
        }
    }

    public void gameLoop(){
        if(!Main.gameover) {
            if (Main.turns % 2 == 0) {
                barPanel.remove(oLabel);
                barPanel.add(xLabel);
            } else {
                barPanel.remove(xLabel);
                barPanel.add(oLabel);
            }
            frame.revalidate();
            frame.repaint();
        } else {
            clearBoard();
            frame.remove(boardPanel);
            frame.remove(barPanel);
            frame.add(winnerPanel);

            if (Main.winner == 0) {
                winnerLabel.setText("Winner: X");
            } else if (Main.winner == 1) {
                winnerLabel.setText("Winner: O");
            } else {
                winnerLabel.setText("It's a Draw!");
            }

            frame.revalidate();
            frame.repaint();
        }
    }
}
