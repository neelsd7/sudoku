package main;

import gui.SudokuBoardView;
import javax.swing.*;


// til at kompilere og køre programmet:

// .\run.bat

// alternativt:

// javac -d out -cp resources src/main/*.java src/main/cellstates/*.java
// java -cp out main.Main

public class Main {
    public static void main(String[] args) {
        // opretter en instans af fabrikken
        SudokuBoardFactory factory = new CSVSudokuBoardFactory();

        // lader fabrikken oprette et bræt
        SudokuBoard board = factory.createBoard();

        // debug: printer det oprettede bræt
        board.printBoard();

        /*// eksempel på at ændre en celle
        board.setCellValue(0, 0, 5);  // ændrer celle på (0,0) til værdi 5
        System.out.println("\nEfter ændring:");
        board.printBoard();*/

        SwingUtilities.invokeLater(() -> { 
            JFrame frame = new JFrame("Sudoku");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.add(new SudokuBoardView(board)); 
            frame.setVisible(true);
        });
    }
}
