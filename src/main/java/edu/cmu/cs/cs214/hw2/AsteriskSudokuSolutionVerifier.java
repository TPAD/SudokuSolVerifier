package edu.cmu.cs.cs214.hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * AsteriskSudokuSolutionVerifier - This is a solution verifier for the 9x9 asterisk sudoku puzzle.
 * It takes two SudokuPuzzleGrids in the constructor, the first intended as an unsolved puzzle and
 * the second intended to be the solution puzzle to the unsolved one using asterisk sudoku rules.
 */
public class AsteriskSudokuSolutionVerifier extends SudokuPuzzleSolutionVerifier {

    private final List<Integer> asterisk;

    /**
     * Sets the solution puzzle and the unsolved puzzle to be used in the verification process with the
     * asterisk sudoku rules
     * @param puzzle unsolved puzzle
     * @param solution the solution to the unsolved puzzle
     */
    AsteriskSudokuSolutionVerifier(SudokuPuzzleGrid puzzle, SudokuPuzzleGrid solution) {
        super(puzzle, solution);
        asterisk = new ArrayList<>();
    }

    /**
     * Verifies that the intended solution puzzle is indeed a solution to the unsolved puzzle grid given
     * in the constructor using the rules of asterisk sudoku
     * @return true if the solution grid is indeed a solution to the unsolved grid, false otherwise
     */
    @Override
    boolean isValidSolution() {
        SudokuPuzzleGrid puzzle = getSolutionSudokuPuzzleGrid();
        SudokuPuzzleGrid solution = getUnsolvedSudokuGrid();
        boolean noCollisions = super.checkCollisions(puzzle) && super.checkCollisions(solution);
        boolean matchingEntries = super.matchingEntries();
        return asteriskCompliance(solution.getElements()) && noCollisions && matchingEntries;
    }

    /**
     * Check for the special rules required of the asterisk sudoku
     * @param grid the proposed solution grid to the unsolved grid
     * @return true if the asterisk requirement is met, false otherwise
     */
    private boolean asteriskCompliance(List<List<Integer>> grid) {
        int size = grid.size();
        int half = (size - 1)/2;
        for (int i = 0; i < size; i++) {
            List<Integer> row = grid.get(i);
            if ((i == 1) || (i == (size - 2))) {
                this.asterisk.add(row.get(half));
            }
            if ((i == 2) || (i == half + 2)) {
                this.asterisk.add(row.get(i));
            }
            if (i == half) {
                this.asterisk.add(row.get(1));
                this.asterisk.add(row.get(half));
                this.asterisk.add(row.get(size - 2));
            }
        }
        return this.listFreeOfCollision(this.asterisk);
    }

}
