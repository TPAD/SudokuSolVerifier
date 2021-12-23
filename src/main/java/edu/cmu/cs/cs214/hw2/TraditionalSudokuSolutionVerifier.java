package edu.cmu.cs.cs214.hw2;

/**
 * TraditionalSudokuSolutionVerifier - This is a solution verifier for the traditional 9x9 sudoku puzzle.
 * It takes two SudokuPuzzleGrids in the constructor, the first being an unsolved puzzle and the second being
 * the proposed solution
 */
public class TraditionalSudokuSolutionVerifier extends SudokuPuzzleSolutionVerifier {

    /**
     * Sets the puzzle and solution for solution verification
     * @param puzzle the not solved puzzle
     * @param solution the solution to the puzzle
     */
    TraditionalSudokuSolutionVerifier(SudokuPuzzleGrid puzzle, SudokuPuzzleGrid solution) {
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
        boolean freeOfCollisions = super.checkCollisions(puzzle) && super.checkCollisions(solution);
        boolean matchingEntries = super.matchingEntries();
        return freeOfCollisions && matchingEntries;
    }

}
