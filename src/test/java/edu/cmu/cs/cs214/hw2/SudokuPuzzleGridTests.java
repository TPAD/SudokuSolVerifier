package edu.cmu.cs.cs214.hw2;

import org.junit.Test;
import org.junit.Before;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SudokuPuzzleGridTests {

    private SudokuPuzzleGrid grid;
    private List<String> testPaths;

    @Before
    public void setUp() {
        testPaths = new ArrayList<>();
        testPaths.add("src/main/resources/sudoku-problem-1.txt");
        testPaths.add("src/main/resources/sudoku-proble-1.txt");
        testPaths.add("src/main/resources/sudoku-problem-1-bad.txt");
        testPaths.add("src/main/resources/sudoku-problem-2-bad.txt");
        testPaths.add("src/main/resources/sudoku-invalid-token.txt");
        testPaths.add("src/main/resources/sudoku-invalid-number-big.txt");
    }

    /**
     * The sudoku puzzle class is designed as a 9x9 List of Integers. This tests that those
     * invariants are enforced
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testSudokuPuzzleGridDimensions() throws FileNotFoundException {
        grid = new SudokuPuzzleGrid(testPaths.get(0));
        List<List<Integer>> elements = grid.getElements();
        assertEquals(9, elements.size());
        for (List<Integer> element: elements) {
            assertEquals(9, element.size());
        }
    }

    /**
     * testing file not found exception
     * @throws FileNotFoundException as expected since the input is bad
     */
    @Test (expected = FileNotFoundException.class)
    public void testSudokuPuzzleGridFileNotFound() throws FileNotFoundException {
        new SudokuPuzzleGrid(testPaths.get(1));
    }

    /**
     * testing invalid entry in column
     * @throws ArrayIndexOutOfBoundsException as expected because invalid entry in column
     * @throws FileNotFoundException just in case
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testSudokuPuzzleGridMalformedInput1() throws ArrayIndexOutOfBoundsException, FileNotFoundException {
        new SudokuPuzzleGrid(testPaths.get(2));
    }

    /**
     * testing too many colums on the textual representation of the sudoku puzzle
     * @throws ArrayIndexOutOfBoundsException as expected because the file is empty
     * @throws FileNotFoundException just in case
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testSudokuPuzzleGridMalformedInput2() throws ArrayIndexOutOfBoundsException, FileNotFoundException {
        new SudokuPuzzleGrid(testPaths.get(3));
    }

    /**
     * testing invalid token in the sudoku puzzle textual representation
     * @throws FileNotFoundException just in case
     * @throws NumberFormatException as expected bc the tested file contains a letter
     */
    @Test (expected = NumberFormatException.class)
    public void testSudokuPuzzleGridInvalidToken() throws FileNotFoundException, NumberFormatException {
        new SudokuPuzzleGrid(testPaths.get(4));
    }

    /**
     * testing toString method
     * @throws FileNotFoundException just in case
     */
    @Test
    public void testSudokuPuzzleGridToString() throws FileNotFoundException {
        grid = new SudokuPuzzleGrid(testPaths.get(0));
        String actual = "0 0 0 0 0 0 1 0 0 \n" +
                        "0 0 9 1 0 7 6 0 3 \n" +
                        "0 6 0 0 3 4 0 0 7 \n" +
                        "0 0 0 0 0 1 4 0 2 \n" +
                        "0 3 4 8 0 6 5 7 0 \n" +
                        "2 0 7 3 0 0 0 0 0 \n" +
                        "5 0 0 7 1 0 0 8 0 \n" +
                        "7 0 1 5 0 9 3 0 0 \n" +
                        "0 0 0 0 0 0 0 0 0 \n";
        assertEquals(actual, grid.toString());
    }

    /**
     * tests to see if exception is thrown on tokenizing a number that does not belong in 9x9
     * sudoku
     * @throws FileNotFoundException just in case
     * @throws ArrayIndexOutOfBoundsException if a tokenized value is > 9 or < 0
     */

}
