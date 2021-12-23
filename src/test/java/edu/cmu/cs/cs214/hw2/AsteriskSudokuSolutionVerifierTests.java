package edu.cmu.cs.cs214.hw2;

import org.junit.Test;
import org.junit.Before;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AsteriskSudokuSolutionVerifierTests {

    private SudokuPuzzleSolutionVerifier verifier;
    private List<String> testPaths;

    @Before
    public void setUp() {
        testPaths = new ArrayList<>();
        testPaths.add("src/main/resources/asterisksudoku-problem-1.txt");
        testPaths.add("src/main/resources/asterisksudoku-solution-1.txt");
        testPaths.add("src/main/resources/asterisksudoku-problem-2.txt");
        testPaths.add("src/main/resources/asterisksudoku-solution-2.txt");
        testPaths.add("src/main/resources/sudoku-problem-1.txt");
        testPaths.add("src/main/resources/sudoku-prob-collision-sub.txt");
        testPaths.add("src/main/resources/asterisksudoku-prob-1-fail.txt");
        testPaths.add("src/main/resources/asterisksudoku-sol-1-fail.txt");
    }

    /**
     * tests a valid solution grid against it's puzzle grid (asterisksudoku)
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testValidSolution() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle1 = new SudokuPuzzleGrid(testPaths.get(0));
        SudokuPuzzleGrid solution1 = new  SudokuPuzzleGrid(testPaths.get(1));
        SudokuPuzzleGrid puzzle2 = new SudokuPuzzleGrid(testPaths.get(2));
        SudokuPuzzleGrid solution2 = new  SudokuPuzzleGrid(testPaths.get(3));
        verifier = new AsteriskSudokuSolutionVerifier(puzzle1, solution1);
        assertTrue(verifier.isValidSolution());
        verifier = new AsteriskSudokuSolutionVerifier(puzzle2, solution2);
        assertTrue(verifier.isValidSolution());
    }

    /**
     * tests invalid solution and problem grids (asterisksudoku)
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testInvalidSolution() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle1 = new SudokuPuzzleGrid(testPaths.get(0));
        SudokuPuzzleGrid solution1 = new  SudokuPuzzleGrid(testPaths.get(3));
        SudokuPuzzleGrid puzzle2 = new SudokuPuzzleGrid(testPaths.get(2));
        SudokuPuzzleGrid solution2 = new  SudokuPuzzleGrid(testPaths.get(1));
        SudokuPuzzleGrid solution3 = new  SudokuPuzzleGrid(testPaths.get(7));
        SudokuPuzzleGrid puzzle3 = new SudokuPuzzleGrid(testPaths.get(1));
        verifier = new HyperSudokuSolutionVerifier(puzzle1, solution1);
        assertFalse(verifier.isValidSolution());
        verifier = new HyperSudokuSolutionVerifier(puzzle2, solution2);
        assertFalse(verifier.isValidSolution());
        verifier = new HyperSudokuSolutionVerifier(puzzle2, solution3);
        assertFalse(verifier.isValidSolution());
        verifier = new HyperSudokuSolutionVerifier(puzzle3, solution3);
        assertFalse(verifier.isValidSolution());
    }
}
