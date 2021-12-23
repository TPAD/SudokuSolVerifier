package edu.cmu.cs.cs214.hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * SudokuXSolutionVerifier - This is a solution verifier for the 9x9 sudoku X puzzle.
 * It takes two SudokuPuzzleGrids in the constructor, the first being an unsolved puzzle and the second being
 * the proposed solution. The solution requires that the two diagonals also contain entries 1-9.
 */
public class SudokuXSolutionVerifier extends SudokuPuzzleSolutionVerifier {

    /**
     * Sets the puzzle and the solution puzzle grid for verification
     * @param puzzle the unsolved puzzle
     * @param solution the solution to the unsolved puzzle
     */
    SudokuXSolutionVerifier(SudokuPuzzleGrid puzzle, SudokuPuzzleGrid solution) {
        super(puzzle, solution);
    }

    /**
     * Checks whether the solution to puzzle grid is a valid solution
     * @return true if solution is verified, false otherwise
     */
    @Override
    boolean isValidSolution() {
        SudokuPuzzleGrid puzzle = getSolutionSudokuPuzzleGrid();
        SudokuPuzzleGrid solution = getUnsolvedSudokuGrid();
        boolean noCollisions = super.checkCollisions(puzzle) && super.checkCollisions(solution);
        boolean matchingEntries = super.matchingEntries();
        return solutionHasDistinctDiagonals() && noCollisions && matchingEntries;
    }

    /**
     * Verifies that the sudoku puzzle solution has distinct entries along both diagonals
     * @return true if both diagonals in the solution puzzle contain entries 1-9
     */
    private boolean solutionHasDistinctDiagonals() {
        List<List<Integer>> solGrid = this.getSolutionSudokuPuzzleGrid().getElements();
        List<Integer> diagOne = new ArrayList<>();
        List<Integer> diagTwo = new ArrayList<>();
        for (int i = 0; i < solGrid.size(); i++) {
            List<Integer> row = solGrid.get(i);
            diagOne.add(row.get(i));
            diagTwo.add(row.get(row.size() - (i + 1)));
        }
        return this.listFreeOfCollision(diagOne) && this.listFreeOfCollision(diagTwo);
    }

}
