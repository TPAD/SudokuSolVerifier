package edu.cmu.cs.cs214.hw2;

import org.junit.Test;
import org.junit.Before;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SudokuXSolutionVerifierTests {

    private SudokuPuzzleSolutionVerifier verifier;
    private List<String> testPaths;

    @Before
    public void setUp() {
        testPaths = new ArrayList<>();
        testPaths.add("src/main/resources/sudoku-x-problem-1.txt");
        testPaths.add("src/main/resources/sudoku-x-problem-2.txt");
        testPaths.add("src/main/resources/sudoku-x-solution-1.txt");
        testPaths.add("src/main/resources/sudoku-x-solution-2.txt");
        testPaths.add("src/main/resources/hypersudoku-problem-1.txt");
        testPaths.add("src/main/resources/sudoku-prob-collision-sub.txt");
        testPaths.add("src/main/resources/sudoku-x-prob-fail-1.txt");
    }

    /**
     * tests a valid solution grid against it's puzzle grid
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testValidSolution() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle1 = new SudokuPuzzleGrid(testPaths.get(0));
        SudokuPuzzleGrid solution1 = new  SudokuPuzzleGrid(testPaths.get(2));
        SudokuPuzzleGrid puzzle2 = new SudokuPuzzleGrid(testPaths.get(1));
        SudokuPuzzleGrid solution2 = new  SudokuPuzzleGrid(testPaths.get(3));
        verifier = new SudokuXSolutionVerifier(puzzle1, solution1);
        assertTrue(verifier.isValidSolution());
        verifier = new SudokuXSolutionVerifier(puzzle2, solution2);
        assertTrue(verifier.isValidSolution());
    }

    /**
     * tests an invalid solution grid against it's puzzle grid
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testInvalidSolution() throws FileNotFoundException {
        SudokuPuzzleGrid puzzle1 = new SudokuPuzzleGrid(testPaths.get(0));
        SudokuPuzzleGrid solution1 = new  SudokuPuzzleGrid(testPaths.get(4));
        SudokuPuzzleGrid puzzle2 = new SudokuPuzzleGrid(testPaths.get(1));
        SudokuPuzzleGrid solution2 = new  SudokuPuzzleGrid(testPaths.get(4));
        SudokuPuzzleGrid solution3 = new  SudokuPuzzleGrid(testPaths.get(5));
        SudokuPuzzleGrid puzzle3 = new SudokuPuzzleGrid(testPaths.get(6));
        verifier = new SudokuXSolutionVerifier(puzzle1, solution1);
        assertFalse(verifier.isValidSolution());
        verifier = new SudokuXSolutionVerifier(puzzle2, solution2);
        assertFalse(verifier.isValidSolution());
        verifier = new SudokuXSolutionVerifier(puzzle2, solution3);
        assertFalse(verifier.isValidSolution());
        verifier = new SudokuXSolutionVerifier(puzzle3, solution3);
        assertFalse(verifier.isValidSolution());
    }

}
