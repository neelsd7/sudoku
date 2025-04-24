package gui;


import cellstates.SudokuCell;
import java.awt.*;
import javax.swing.*;
import main.SudokuBoard;


// implementerer BoardObserver og er view.

// reagerer automatisk på ændringer i SudokuBoard (observer pattern)
public class SudokuBoardView extends JPanel implements BoardObserver {
    private final SudokuBoard model;
    private final JTextField[][] fields = new JTextField[9][9];

    public SudokuBoardView(SudokuBoard model) {
        this.model = model;
        setLayout(new GridLayout(9, 9));
        initBoard();
    }

    public void initializeObserver() {
        model.addObserver(this); // Observer pattern
    }

    private void initBoard() {
        SudokuCell[][] board = model.getBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("SansSerif", Font.BOLD, 20));

                SudokuCell sudokuCell = board[i][j];
                int value = sudokuCell.getValue();
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.setForeground(Color.BLACK); // original farve
                } else {
                    cell.setText("");
                    cell.setEditable(true);
                    cell.setForeground(Color.BLUE); // bruger farve
                }

                fields[i][j] = cell;
                add(cell);
            }
        }
    }

    // kaldt fra Observer pattern
    @Override
    public void boardUpdated() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = model.getCell(i, j).getValue();
                if (value != 0) {
                    fields[i][j].setText(String.valueOf(value));
                } else {
                    fields[i][j].setText("");
                }
            }
        }
    }
}
