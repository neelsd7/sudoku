package main;

import cellstates.SudokuCell;
import gui.BoardObserver;
import java.util.ArrayList;
import java.util.List;

// produkt-rolle i factory pattern
// model og subjekt rolle i observer pattern


public class SudokuBoard {
    private final SudokuCell[][] board;
    private final List<BoardObserver> observers = new ArrayList<>();
    // private SudokuBoardFactory factory;

    // package-private konstruktør modtager et 9x9 board fra SudokuBoardFactory
    SudokuBoard (SudokuCell[][] board){
        this.board = board;
    }

    // metode til at ændre en celle på brættet
    public boolean setCellValue(int row, int col, int value){
    if (!isValidPosition(row, col)) {
        System.out.println("Ugyldig position");
        return false;
    }
    if (!board[row][col].isEditable()) {
        System.out.println("Kan ikke ændre en original celle!");
        return false;
    }
    board[row][col].setValue(value);
    notifyObservers(); // hvis du bruger observer pattern
    return true;
}


    public boolean isEditable(int row, int col){
        // return board[row,col].isEditable();
        return true;
    }

    // evt: validering af hele brættet: implementering af strategy pattern
    public boolean isValidBoard() {
        // implementere strategy pattern til at tjekke rækker, kolonner og bokse
        return true; // placeholder
    }

    // debug: tjekker om position er gyldig
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 9 && col >= 0 && col < 9;
    }   

    // debug: print brættet
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (BoardObserver observer : observers) {
            observer.boardUpdated();
        }
    }

    public SudokuCell[][] getBoard() {
        return board;
    }

    public SudokuCell getCell(int row, int col) {
        return board[row][col];
    }
}