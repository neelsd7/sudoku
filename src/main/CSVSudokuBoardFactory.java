package main;

import cellstates.SudokuCell;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random; 

// konkret creator-rolle i factory pattern
public class CSVSudokuBoardFactory extends SudokuBoardFactory{
    private static final String FILE_PATH = "/sudoku.csv"; // CSV-fil med Sudoku-brætter
    private static final int MAX_BOARDS = 9000000; // antal brætter i filen
    //private static final int MAX_BOARDS = 1; // debug

    @Override
    public SudokuBoard createBoard(){
        System.out.println("filen er her: " + getClass().getResource(FILE_PATH)); // debug: tjekker om filen findes
        int randomLine = new Random().nextInt(MAX_BOARDS); // vælg et tilfældigt bræt
        SudokuCell[][] board = new SudokuCell[9][9]; // matrix der indeholder selve brættet

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_PATH)))) { // try() lukker automatisk filen, når koden er færdig (ellers br.close() for at gøre det manuelt)
            if (br == null) {
                throw new NullPointerException("File not found: " + FILE_PATH);
            }
            
            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine == randomLine) { // når man rammer den tilfældige linje ...
                    board = parseCSVLine(line); // parses den til en 9x9 array
                    break;
                }
                currentLine++;
            }
        } catch (IOException | NullPointerException e) { // IOException fanger fejl ved filhåndtering, fx hvis filen ikke findes
            // e.printStackTrace();
            System.out.println("Fejl ved indlæsning af Sudoku-bræt fra CSV!");
        }

        return new SudokuBoard(board);
    }
    
    private SudokuCell[][] parseCSVLine(String line) { // parser linjen med brættet til en 9x9 matrix        
        // da csv filen er i formatet: puzzle,solution, separerer jeg puzzle fra solution med .split()
        String[] parts = line.split(",");
        String puzzle = parts[0];
        // String solution = parts[1]; // denne skal nok ikke bruges

        SudokuCell[][] board = new SudokuCell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // værdi 0 betyder tom celle
                // int puzzleValue = Character.getNumericValue(puzzle.charAt(i * 9 + j));
                int value = Character.getNumericValue(puzzle.charAt(i * 9 + j));

                // opretter en SudokuCell baseret baseret på om cellen på ændres eller ej
                if (value != 0){ // cellen betrages som original
                    board[i][j] = new SudokuCell(value, true); 
                } else { // HVIS FEJL: PRÆCISERES MED IF. celles betragtes ikke som original og må godt ændres
                    board[i][j] = new SudokuCell(value, false);
                }
            }
        }
        return board;
    }
}