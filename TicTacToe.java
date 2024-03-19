/* Exercise 1: Edit the size of the grid to be a 4 by 4 (16 cells), make sure you edit the corresponding logic
 * Exercise 2: Create a hover event. When a mouse hovers over a particular button, highlight it.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[16];
    private boolean player1Turn = true;

    public TicTacToe() {
        super("Tic Tac Toe");
        setLayout(new GridLayout(4, 4));
        initializeGameBoard();
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeGameBoard() {
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(this);
            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setBackground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setBackground(null);
                }
            });
            add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (player1Turn && buttonClicked.getText().equals("")) {
            buttonClicked.setText("X");
            player1Turn = false;
        } else if (!player1Turn && buttonClicked.getText().equals("")) {
            buttonClicked.setText("O");
            player1Turn = true;
        }
        checkForWin();
    }

    private void checkForWin() {
        for (int i = 0; i < 15; i++) {
            String line = null;
            switch (i) {
                case 0:
                    line = buttons[0].getText() + buttons[1].getText() + buttons[2].getText() + buttons[3].getText();
                    break;
                case 1:
                    line = buttons[4].getText() + buttons[5].getText() + buttons[6].getText() + buttons[7].getText();
                    break;
                case 2:
                    line = buttons[8].getText() + buttons[9].getText() + buttons[10].getText() + buttons[11].getText();
                    break;
                case 3:
                    line = buttons[12].getText() + buttons[13].getText() + buttons[14].getText() + buttons[15].getText();
                    break;
                case 4:
                    line = buttons[0].getText() + buttons[4].getText() + buttons[8].getText() + buttons[12].getText();
                    break;
                case 5:
                    line = buttons[1].getText() + buttons[5].getText() + buttons[9].getText() + buttons[13].getText();
                    break;
                case 6:
                    line = buttons[2].getText() + buttons[6].getText() + buttons[10].getText() + buttons[14].getText();
                    break;
                case 7:
                    line = buttons[3].getText() + buttons[7].getText() + buttons[11].getText() + buttons[15].getText();
                    break;
                case 8:
                    line = buttons[0].getText() + buttons[5].getText() + buttons[10].getText() + buttons[15].getText();
                    break;
                case 9:
                    line = buttons[3].getText() + buttons[6].getText() + buttons[9].getText() + buttons[12].getText();
                    break;
            }
            if (line.equals("XXXX")) {
                JOptionPane.showMessageDialog(this, "Player 1 wins!");
                resetGame();
                break;
            } else if (line.equals("OOOO")) {
                JOptionPane.showMessageDialog(this, "Player 2 wins!");
                resetGame();
                break;
            } else checkForDraw();
        }
    }

    private void checkForDraw() {
        for (int i = 0; i < 16; i++) {
            if (buttons[i].getText().equals("")) {
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "It's a draw!");
        resetGame();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}