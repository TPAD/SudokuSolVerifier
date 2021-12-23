package edu.cmu.cs.cs214.hw2;

import org.junit.Test;
import org.junit.Before;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TraditionalSudokuSolutionVerifierTests {

    private SudokuPuzzleSolutionVerifier verifier;
    private List<String> testPaths;

    @Before
    public void setUp() {
        testPaths = new ArrayList<>();
        testPaths.add("src/main/resources/sudoku-problem-1.txt");
        testPaths.add("src/main/resources/sudoku-solution-1.txt");
        testPaths.add("src/main/resources/sudoku-problem-2.txt");
        testPaths.add("src/main/resources/sudoku-solution-2.txt");
        testPaths.add("src/main/resources/sudoku-prob-collision.txt");
        testPaths.add("src/main/resources/sudoku-sol-invalid.txt");
        testPaths.add("src/main/resources/hypersudoku-problem-1.txt");
        testPaths.add("src/main/resources/sudoku-prob-collision-row.txt");
        testPaths.add("src/main/resources/sudoku-prob-collision-sub.txt");
    }

    /**
     * tests a valid solution grid against it's puzzle grid
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testValidSolution() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle = new SudokuPuzzleGrid(testPaths.get(0));
        SudokuPuzzleGrid solution = new  SudokuPuzzleGrid(testPaths.get(1));
        verifier = new TraditionalSudokuSolutionVerifier(puzzle, solution);
        assertTrue(verifier.isValidSolution());
    }

    /**
     * tests a solution grid against a puzzle grid for which it is not a solution
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testInvalidSolution() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle = new SudokuPuzzleGrid(testPaths.get(0));
        SudokuPuzzleGrid solution = new  SudokuPuzzleGrid(testPaths.get(2));
        verifier = new TraditionalSudokuSolutionVerifier(puzzle, solution);
        assertFalse(verifier.isValidSolution());
    }

    /**
     * tests a dublicate entry column-wise in the puzzle grid
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testCollision() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle = new SudokuPuzzleGrid(testPaths.get(4));
        SudokuPuzzleGrid solution = new  SudokuPuzzleGrid(testPaths.get(3));
        verifier = new TraditionalSudokuSolutionVerifier(puzzle, solution);
        assertFalse(verifier.isValidSolution());
        assertFalse(verifier.checkCollisions(puzzle));
    }

    /**
     * tests an invalid solution grid
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testEmptyEntrySolution() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle = new SudokuPuzzleGrid(testPaths.get(6));
        SudokuPuzzleGrid solution = new  SudokuPuzzleGrid(testPaths.get(5));
        verifier = new TraditionalSudokuSolutionVerifier(puzzle, solution);
        assertFalse(verifier.isValidSolution());
    }

    /**
     * checks for collisions column-wise in the puzzle and subgrid-wise in the solution
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testCollisionRowSub() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle = new SudokuPuzzleGrid(testPaths.get(7));
        SudokuPuzzleGrid solution = new  SudokuPuzzleGrid(testPaths.get(8));
        verifier = new TraditionalSudokuSolutionVerifier(puzzle, solution);
        assertFalse(verifier.isValidSolution());
        assertFalse(verifier.checkCollisions(puzzle));
        assertFalse(verifier.checkCollisions(solution));
    }


}
