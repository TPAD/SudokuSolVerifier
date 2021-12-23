package edu.cmu.cs.cs214.hw2;

import java.io.FileNotFoundException;

/**
 * Main program that runs the Sudoku solution verifier.
 */
public class SudokuSolutionVerifier {
    /**
     * The main method to run the Sudoku solution verifier.
     * @param args    program arguments
     */
    public static void main(String[] args) {
        try {
            SudokuPuzzleGrid puzzle = new SudokuPuzzleGrid(args[0]);
            SudokuPuzzleGrid solution = new SudokuPuzzleGrid(args[1]);
            SudokuPuzzleSolutionVerifier verifier = new AsteriskSudokuSolutionVerifier(puzzle, solution);
            System.out.println(verifier.isValidSolution());
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
