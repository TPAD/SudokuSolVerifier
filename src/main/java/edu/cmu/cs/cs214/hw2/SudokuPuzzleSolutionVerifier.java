package edu.cmu.cs.cs214.hw2;

import java.util.ArrayList;
import java.util.List;
/**
 * SudokuSolutionVerifierInterface - blueprint for a sudoku solution verifier
 * Contains methods to verify the correctness of a board, i.e. that a board meets the required criteria for
 * the version of sudoku that require distinct entries row-wise, column-wise, and in each 3x3 subgrid
 */
abstract class SudokuPuzzleSolutionVerifier {

    private static final int MAX_INDEX = 8;
    private static final int MAX_SUB_INDEX = 2;
    private SudokuPuzzleGrid puzzle;
    private SudokuPuzzleGrid solution;

    /**
     * Initializes the puzzle and the solution grids for use in calculations. Though the intention
     * is that the first parameter should be an unsolved sudoku grid and the second parameter should
     * a solved sudoku grid, neither of those are guaranteed
     * @param puzzle unsolved sudoku puzzle grid
     * @param solution solved sudoku puzzle grid
     */
    SudokuPuzzleSolutionVerifier(SudokuPuzzleGrid puzzle, SudokuPuzzleGrid solution) {
        this.puzzle = puzzle;
        this.solution = solution;
    }

    /**
     * Determines whether the solution grid is a valid solution for the puzzleGrid
     * @return true if solution is a valid solution, false if not
     */
     abstract boolean isValidSolution();

    /**
     * Determines the validity of a sudoku puzzle grid entry-wise i.e. checks that there are no repetions row-wise,
     * column-wise, and grid wise
     * @param puzzle sudoku puzzle grid
     * @return true if puzzle contains collisions, false otherwise
     */
     boolean checkCollisions(SudokuPuzzleGrid puzzle) {
        for (int i = 0; i <= MAX_INDEX; i++) {
            List<Integer> row = getRow(i, puzzle);
            List<Integer> col = getColumn(i, puzzle);
            if (!(listFreeOfCollision(row) && listFreeOfCollision(col))) {
                return false;
            }
        }
        for (int i = 0; i <= MAX_SUB_INDEX; i++) {
            for (int j = 0; j <= MAX_SUB_INDEX; j++) {
                List<Integer> subGrid = getSubGrid(i, j, puzzle);
                if (!listFreeOfCollision(subGrid)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the 3x3 subgrid of the sudoku puzzle at index or an empty List if the index is invalid.
     * So it is up to the verifier to decide what to do in that case.
     * @param i row index of which grid to get 0-2
     * @param j column index of which grid to get 0-2
     * @param puzzle the puzzle from which the subgrid is extracted
     * @return the desired subgrid from puzzle
     */
    private List<Integer> getSubGrid(int i, int j, SudokuPuzzleGrid puzzle) {
        List<List<Integer>> elems;
        List<Integer> grid = new ArrayList<>();
        int[] start = {0, MAX_SUB_INDEX + 1, 2*MAX_SUB_INDEX + 2};              // 0, 3, 6
        int[] end = {MAX_SUB_INDEX, 2*MAX_SUB_INDEX + 1, 2*2*MAX_SUB_INDEX};    // 2, 5, 8
        elems = puzzle.getElements();
        for (int k = start[i]; k <= end[i]; k++) {
            List<Integer> row = elems.get(k);
            for (int l = start[j]; l<= end[j]; l++) {
                grid.add(row.get(l));
            }
        }
        return grid;
    }

    /**
     * Returns the row of the sudoku at index or an empty List if the index is invalid.
     * It is up to the verifier to decide what to do in that case.
     * @param index row index, valid 0-8
     * @param puzzle the puzzle from which the row is extracted
     * @return row at index
     */
    private List<Integer> getRow(int index, SudokuPuzzleGrid puzzle) {
        List<List<Integer>> grid = puzzle.getElements();
        return grid.get(index);
    }

    /**
     * Returns the column of the sudoku at index or an empty List if the index is invalid.
     * It is up to the verifier to decide what to do in that case.
     * @param index column index, valid: 0-8
     * @param puzzle the puzzle from which the column is extracted
     * @return
     */
    private List<Integer> getColumn(int index, SudokuPuzzleGrid puzzle) {
        List<List<Integer>> grid = puzzle.getElements();
        List<Integer> result = new ArrayList<>();
        for(List<Integer> row: grid) {
            result.add(row.get(index));
        }
        return result;
    }

    /**
     * checks if the list contains duplicate elements, ignoring 0 using brute force;
     * @param list list to check for duplicates
     * @return true if list contains duplicates, false otherwise
     */
    boolean listFreeOfCollision(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(i) != 0) && (list.get(j) != 0) && (list.get(i) == list.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifies that the filled entries in the unsolved sudoku puzzle grid match those of the solution grid.
     * @return false if they don't match or if the solution puzzle grid contains an empty space
     */
    boolean matchingEntries() {
        List<List<Integer>> puzzleElements = this.puzzle.getElements();
        List<List<Integer>> solutionElements = this.solution.getElements();
        for (int i = 0; i <= MAX_INDEX; i++) {
            List<Integer> puzzleRow = puzzleElements.get(i);
            List<Integer> solutionRow = solutionElements.get(i);
            for (int j = 0; j <= MAX_INDEX; j++) {
                int puzzleVal = puzzleRow.get(j);
                int solutionVal = solutionRow.get(j);
                if (solutionVal == 0) {
                    return false;
                }
                if ((puzzleVal != 0) && (puzzleVal != solutionVal)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the "supposedly" unsolved sudoku puzzle grid
     * @return the sudoku puzzle passed in as unsolved in the constructor
     */
    public SudokuPuzzleGrid getUnsolvedSudokuGrid() {
        return this.puzzle;
    }

    /**
     * Gets the proposed solution for the unsolved puzzle grid
     * @return the sudoku puzzle passed in as the solution in the constructor
     */
    public SudokuPuzzleGrid getSolutionSudokuPuzzleGrid() {
        return this.solution;
    }
}
