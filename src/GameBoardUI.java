import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardUI {
    private JFrame frame;
    private JButton[][] buttons;
    private GameLogic gameLogic;

    public GameBoardUI() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        buttons = new JButton[3][3];
        gameLogic = new GameLogic();
    }

    public void createAndShowGUI() {
        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.BLACK);

                int row = i;
                int col = j;

                // Add click listener
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameLogic.makeMove(row, col)) {
                            buttons[row][col].setText(String.valueOf(gameLogic.getCurrentPlayer()));

                            if (gameLogic.checkWin()) {
                                JOptionPane.showMessageDialog(frame, "Player " + gameLogic.getCurrentPlayer() + " wins!");
                                resetGame();
                            } else if (gameLogic.checkDraw()) {
                                JOptionPane.showMessageDialog(frame, "It's a draw!");
                                resetGame();
                            } else {
                                gameLogic.switchPlayer();
                            }
                        }
                    }
                });

                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private void resetGame() {
        gameLogic.resetBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
}
