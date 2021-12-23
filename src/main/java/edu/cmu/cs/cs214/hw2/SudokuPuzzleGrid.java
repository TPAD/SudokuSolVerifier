package edu.cmu.cs.cs214.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SudokuPuzzleGrid - represents a 9x9 sudoku puzzle. The 9x9 invariants are enforced but the text representation must
 * contain integers, not characters or floating point numbers.
 * This class was purposely made non extensible and immutable. If you want to represent a variation of sudoku
 * that is not 9x9, ¯\_(ツ)_/¯
 */
public final class SudokuPuzzleGrid  {

    private static final int NUM_ROWS = 9;
    private static final int NUM_COLS = 9;
    private static final int MAX_ENTRY_NUM = 9;
    private final List<List<Integer>> grid;
    private final String stringRepresentation;


    /**
     * Initializer for a Sudoku Puzzle Grid
     * @param path a path to local text file
     * @throws FileNotFoundException if the parsing fails due to path failure
     * @throws NumberFormatException if the integer tokenizer finds a non integer
     */
    public SudokuPuzzleGrid(String path) throws FileNotFoundException, NumberFormatException {
        this.grid = new ArrayList<>();
        this.stringRepresentation = parseTextFile(path);
    }

    /**
     * Parses a text file assuming it is a valid text representation of a 9x9 sudoku puzzle.
     * Propagates an exception outward if the textfile contains an invalid sudoku text puzzle.
     * @param path The path of the file to parse
     * @throws FileNotFoundException if the file path is malformed
     * @throws NumberFormatException if the integer tokenizer finds a non integer.
     * @return a string representation of the sudoku puzzle containedd in the text file
     */
    private String parseTextFile(String path) throws FileNotFoundException, NumberFormatException {
        StringBuilder rep = new StringBuilder();
        File file = new File(path);
        int colCount, rowCount = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                List<Integer> col = new ArrayList<>();
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                colCount = st.countTokens();
                if (colCount != NUM_COLS) {
                    throw new ArrayIndexOutOfBoundsException("Error: invalid number of entries column-wise");
                }
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    try {
                        Integer num = Integer.parseInt(token);
                        rep.append(token);
                        rep.append(" ");
                        col.add(num);
                    } catch (NumberFormatException ex) {
                        System.out.println(" ");
                        throw ex;
                    }
                }
                if (rowCount <= NUM_ROWS - 1) {
                    rep.append("\n");
                }
                grid.add(col);
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please enter a valid text path");
            throw e;
        }
        if (rowCount != NUM_ROWS) {
            throw new ArrayIndexOutOfBoundsException("Error: Invalid number of entries row-wise");
        }
        return rep.toString();
    }

    /**
     * getter the 9x9 List of elements. this is the only method required for the client
     * @return elements for testing purposes
     */
    public List<List<Integer>> getElements() {
        return this.grid;
    }

    // visual representations of the puzzle is nice
    @Override
    public String toString() {
        return this.stringRepresentation;
    }
}
