package main;


// creator-rolle i factory pattern
public abstract class SudokuBoardFactory {
    public abstract SudokuBoard createBoard(); // tvinger factory til at returnere et SudokuBoard bræt
}