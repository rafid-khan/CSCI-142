import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Represents a single configuration in the skyscraper puzzle.
 *
 * @author RIT CS
 * @author Rafid Khan
 *
 */
public class SkyscraperConfig implements Configuration {
    /** empty cell value */
    public final static int EMPTY = 0;

    /** empty cell value display */
    public final static char EMPTY_CELL = '.';

    /**  current board */
    private final int[][] board;

    /** a hashmap of each 'direction' with their keys as their respective values */
    private final HashMap<String, ArrayList<Integer>> directions;

    /** dimensions of the board */
    private final int dimension;

    /** current row being checked */
    private int currentRow;

    /** current column being checked */
    private int currentColumn;

    /**
     * Constructor
     *
     * @param filename the filename
     *  <p>
     *  Read the board file.  It is organized as follows:
     *  DIM     # square DIMension of board (1-9)
     *  lookNS   # DIM values (1-DIM) left to right
     *  lookEW   # DIM values (1-DIM) top to bottom
     *  lookSN   # DIM values (1-DIM) left to right
     *  lookWE   # DIM values (1-DIM) top to bottom
     *  row 1 values    # 0 for empty, (1-DIM) otherwise
     *  row 2 values    # 0 for empty, (1-DIM) otherwise
     *  ...
     *
     *  @throws FileNotFoundException if file not found
     */
    SkyscraperConfig(String filename) throws FileNotFoundException {
        this.currentColumn = -1;
        this.currentRow = 0;
        this.directions = new HashMap<>();
        this.directions.put("N", new ArrayList<Integer>());
        this.directions.put("E", new ArrayList<Integer>());
        this.directions.put("S", new ArrayList<Integer>());
        this.directions.put("W", new ArrayList<Integer>());
        List<String> direction = Arrays.asList("N", "E", "S", "W");

        Scanner f = new Scanner(new File(filename));
        this.dimension = f.nextInt();
        this.board = new int[dimension][dimension];
        int index = 0;
        while (f.hasNextInt()) {
            if (index < 4){
                if (directions.get(direction.get(index)).size() < dimension)
                    this.directions.get(direction.get(index)).add(f.nextInt());
                else {
                    index++;
                }
            }
            else{
                int row;
                int column;
                for (row = 0; row < dimension; row++){
                    for (column = 0; column < dimension; column++){
                        this.board[row][column] = f.nextInt();
                    }
                }
            }
        }
        f.close();
    }

    /**
     * SkyscraperConfig() (copy)
     * copy constructor
     *
     * @param copy SkyscraperConfig instance
     */
    public SkyscraperConfig(SkyscraperConfig copy) {
        this.dimension = copy.dimension;
        this.currentColumn = copy.currentColumn;
        this.currentRow = copy.currentRow;
        this.board = new int[copy.dimension][copy.dimension];
        this.directions = copy.directions;
        int row = 0;
        while (row < this.dimension) {
            System.arraycopy(copy.board[row], 0, this.board[row], 0,
                    this.dimension);
            row++;
        }
    }

    /**
     * isGoal()
     * checks whether a config is a goal or not
     *
     * @return true if config is goal, otherwise false
     */
    @Override
    public boolean isGoal() {
        int row = 0;
        while (row < dimension) {
            for (int column = 0; column < dimension; column ++){
                switch (board[row][column]) {
                    case EMPTY, EMPTY_CELL -> {
                        this.currentRow = row;
                        this.currentColumn = column;
                        return false;
                    }
                }
            }
            row++;
        }
        return true;
    }

    /**
     * getSuccessors()
     * Utilizes LinkedList to store the successors of the current
     * board. By using the LinkedList we can store them with respect
     * to their current placement.
     *
     * @returns Collection of Configurations
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        isGoal();
        List<Configuration> successor = new LinkedList<Configuration>();
        int val = 1;
        while (val <= this.dimension) {
            if (inTable(val)) {
                SkyscraperConfig child = new SkyscraperConfig(this);
                child.board[currentRow][currentColumn] = val;
                successor.add(child);
            }
            val++;
        }
        return successor;
    }

    /**
     * isValid()
     * checks if current config is valid
     *
     * @returns true if config is valid, false otherwise
     */
    @Override
    public boolean isValid() {
        if (directions.get("W").get(currentRow) == 1 &&
                (this.board[currentRow][0] != dimension) &&
                (this.board[currentRow][0] != EMPTY)) {
            return false;
        }
        if (directions.get("E").get(currentRow) == 1 &&
                (this.board[currentRow][dimension - 1] != dimension) &&
                (this.board[currentRow][dimension - 1] != EMPTY)) {
            return false;
        }
        if (directions.get("N").get(currentColumn) == 1) {
            if ((this.board[0][currentColumn] != dimension) &&
                    (this.board[0][currentColumn] != EMPTY)) {
                return false;
            }
        }
        if (directions.get("S").get(currentColumn) == 1 &&
                (this.board[dimension - 1][currentColumn] != dimension) &&
                (this.board[dimension - 1][currentColumn] != EMPTY)) {
            return false;
        }
        if (!isEmptyRow()){
            return directions.get("W").get(currentRow) == getWest() &&
                    directions.get("E").get(currentRow) == getEast();
        }
        if (!isEmptyColumn()){
            return directions.get("N").get(currentColumn) == getNorth() &&
                    directions.get("S").get(currentColumn) == getSouth();
        }
        return true;
    }

    /**
     * toString() method
     *
     * @return String representing configuration board & grid w/ look values.
     * The format of the output for the problem-solving initial config is:
     *
     *   1 2 4 2
     *   --------
     * 1|. . . .|3
     * 2|. . . .|3
     * 3|. . . .|1
     * 3|. . . .|2
     *   --------
     *   4 2 1 2
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("  ");
        for (int x = 0; x < dimension; x ++){
            result.append(directions.get("N").get(x)).append(" ");
        }
        result.append("\n  ");
        result.append("-".repeat(Math.max(0, dimension * 2)));
        for (int row = 0; row < dimension; row++){
            result.append("\n").append(directions.get("W").get(row)).append("|");
            for (int column = 0; column < dimension; column ++){
                int b = board[row][column];
                if (b == EMPTY){
                    result.append(EMPTY_CELL);
                }
                else {
                    result.append(board[row][column]);
                }
                if (column != dimension - 1) {
                    result.append(" ");
                }
            }
            result.append("|").append(directions.get("E").get(row));
        }
        result.append("\n  ");
        result.append("-".repeat(Math.max(0, dimension * 2)));
        result.append("\n  ");
        for (int c = 0; c < dimension; c++){
            result.append(directions.get("S").get(c)).append(" ");
        }
        return result + "\n";
    }

    /*
                  ** HELPER METHODS / FUNCTIONS BELOW **
     */

    /**
     * inTable()
     * checks to see whether the number is in the current row or current column
     *
     * @param num: the number being checked
     * @return true if the number isn't in the current spot
     */
    private boolean inTable(int num){
        int row = 0;
        while (row < dimension) {
            if (board[row][currentColumn] == num) return false;
            row++;
        }
        return IntStream.range(0, dimension).noneMatch(col ->
                board[currentRow][col] == num);
    }

    /**
     * isEmptyRow()
     * checks to see whether the row is empty
     *
     * @return true if position is empty, false if taken
     */
    private boolean isEmptyRow(){
        return IntStream.range(0, dimension).anyMatch(column ->
                board[currentRow][column] == EMPTY);
    }

    /**
     * isEmptyColumn()
     * checks to see whether the column is empty
     *
     * @return true if position is empty, false if taken
     */
    private boolean isEmptyColumn(){
        return IntStream.range(0, dimension).anyMatch(row ->
                board[row][currentColumn] == EMPTY);
    }

    /**
     * getNorth()
     * Traverses through the current column (north direction)
     * to accumulate how many times a maximum value was encountered
     *
     * @return no. of maximum values encountered
     */
    private int getNorth() {
        int x = 0;
        int y = 0;
        int row = 0;
        while (row < dimension) {
            if (board[row][currentColumn] > x) {
                y++;
                x = board[row][currentColumn];
            }
            row++;
        }
        return y;
    }

    /**
     * getEast()
     * Traverses through the current row (east direction)
     * to accumulate how many times a maximum value was encountered
     *
     * @return no. of maximum values encountered
     */
    private int getEast(){
        int x = 0;
        int y = 0;
        int z = dimension - 1;
        while (z >= 0) {
            if (board[currentRow][z] > x){
                y++;
                x = board[currentRow][z];
            }
            z--;
        }
        return y;
    }

    /**
     * getWest()
     * Traverses through the current row (west direction)
     * to accumulate how many times a maximum value was encountered
     *
     * @return no. of maximum values encountered
     */
    private int getWest(){
        int x = 0;
        int y = 0;
        for (int z : board[currentRow]){
            if (z > x){
                y ++;
                x = z;
            }
        }
        return y;
    }

    /**
     * getSouth()
     * Traverses through the current column (south direction)
     * to accumulate how many times a maximum value was encountered
     *
     * @return no. of maximum values encountered
     */
    private int getSouth(){
        int x = 0;
        int y = 0;
        int z = dimension - 1;
        while (z >= 0) {
            if (board[z][currentColumn] > x){
                y ++;
                x = board[z][currentColumn];
            }
            z--;
        }
        return y;
    }
}
