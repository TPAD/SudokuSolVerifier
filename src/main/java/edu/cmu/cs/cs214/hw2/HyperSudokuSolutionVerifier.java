package edu.cmu.cs.cs214.hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * HyperSudokuSolutionVerifier - This is a solution verifier for the 9x9 hyper sudoku puzzle.
 * It takes two SudokuPuzzleGrids in the constructor, the first being an unsolved puzzle
 * and the second being a proposed solution. The solution requires four middle squares to
 * also contain the entries 1-9.
 */
public class HyperSudokuSolutionVerifier extends SudokuPuzzleSolutionVerifier {

    private final List<Integer> gridOne;
    private final List<Integer> gridTwo;
    private final List<Integer> gridThree;
    private final List<Integer> gridFour;


    /**
     * Sets the puzzle and the solution puzzle grid for verification given hypersudoku solution
     * @param puzzle the unsolved puzzle
     * @param solution the hypersudoku solution to the unsolved puzzle
     */
    HyperSudokuSolutionVerifier(SudokuPuzzleGrid puzzle, SudokuPuzzleGrid solution) {
        super(puzzle, solution);
        gridOne = new ArrayList<>();
        gridTwo = new ArrayList<>();
        gridThree = new ArrayList<>();
        gridFour = new ArrayList<>();
    }

    /**
     * Checks the that the proposed solution to the unsolved sudoku puzzle grid is indeed a solution
     * @return true if the solution grid is a solution to unsolved puzzle with the hypersudoku rules, false otherwise
     */
    @Override
    boolean isValidSolution() {
        SudokuPuzzleGrid puzzle = getSolutionSudokuPuzzleGrid();
        SudokuPuzzleGrid solution = getUnsolvedSudokuGrid();
        boolean noCollisions = super.checkCollisions(puzzle) && super.checkCollisions(solution);
        boolean matchingEntries = super.matchingEntries();
        return gridsContainNoCollisions() && noCollisions && matchingEntries;
    }

    /**
     * checks if the required subgrids of a hyper sudoku puzzle contain no collisions
     * @return true if all subgrids contain distinct values
     */
    private boolean gridsContainNoCollisions() {
        List<List<Integer>> solGrid = this.getSolutionSudokuPuzzleGrid().getElements();
        getAltSolGrids(solGrid);
        boolean firstIsValid = this.listFreeOfCollision(this.gridOne);
        boolean secondIsValid = this.listFreeOfCollision(this.gridTwo);
        boolean thirdIsValid = this.listFreeOfCollision(this.gridThree);
        boolean fourthIsValid= this.listFreeOfCollision(this.gridFour);
        return firstIsValid && secondIsValid && thirdIsValid && fourthIsValid;
    }

    /**
     * Loads the subgrid arrays with their corresponding values from the solution board
     * @param superGrid the solution grid
     */
    private void getAltSolGrids(List<List<Integer>> superGrid) {
        int size = superGrid.size();
        int endIndex = ((size) - 1)/2;
        for (int i = 1; i < endIndex; i++) {
            List<Integer> row = superGrid.get(i);
            for (int j = 1; j < endIndex; j++) {
                this.gridOne.add(row.get(j));
            }
            for (int j = endIndex + 1; j < size - 1; j++) {
                this.gridTwo.add(row.get(j));
            }
        }
        for (int i = endIndex + 1; i < superGrid.size() - 1; i++) {
            List<Integer> row = superGrid.get(i);
            for (int j = 1; j <  endIndex; j++) {
                this.gridThree.add(row.get(j));
            }
            for (int j = endIndex + 1; j < size - 1; j++) {
                this.gridFour.add(row.get(j));
            }
        }
    }

}
