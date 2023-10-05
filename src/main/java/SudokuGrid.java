import java.awt.Point;

public class SudokuGrid {
    private static final int SIZE = 9;
    private static final int SUBGRIDSIZE = 3;
    private static final int DIGIT_RANGE = 9;

    private int[][] grid;
    private int rEmpty, cEmpty; // Coordinates of the last found empty cell

    public SudokuGrid() {
        // Initialize the grid and set rEmpty and cEmpty to -1
        this.grid = new int[SIZE][SIZE];
        this.rEmpty = -1;
        this.cEmpty = -1;
    }

    public SudokuGrid(int[][] initialGrid) {
        // Initialize the grid and set rEmpty and cEmpty to -1
        this.grid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                this.grid[i][j] = initialGrid[i][j];
            }
        }
        this.rEmpty = -1;
        this.cEmpty = -1;
    }

    public SudokuGrid copy() {
        // Create a copy of the SudokuGrid and return it
        int[][] gridCopy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                gridCopy[i][j] = grid[i][j];

            }
        }
        return new SudokuGrid(gridCopy);
    }

    public Point findEmptyCell() {
        // Find the next empty cell in reading order and return its coordinates as a Point
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
               if (grid[i][j] == 0){
                rEmpty = i; 
                cEmpty = j;
                return new Point(rEmpty, cEmpty);
               } 
            }
        }
        return null;
    }

    public void print() {
        // Print the Sudoku grid
        for (int r = 0; r < SIZE; r++){
            if (r % SUBGRIDSIZE == 0 && r != 0){
                System.out.println("-".repeat(SIZE * 2 + SUBGRIDSIZE - 1));
            }
            for (int d = 0; d < SIZE; d++){
                if (d % SUBGRIDSIZE == 0 && d != 0){
                    System.out.print("|");
                }
                System.out.print(grid[r][d] + " ");
            }
            System.out.println();
        }
    }

    public void fillCell(int r, int c, int value) {
        // Fill the cell at row r and column c
        grid[r][c] = value;
    }

    public boolean givesConflict(int r, int c, int d) {
        // Check if filling the number d in the cell at row r and column c causes a conflict
        if (rowConflict(r, d) || colConflict(c, d) || boxConflict(r, c, d))  return true;
             else{
        return false;
    }
    }

    private boolean rowConflict(int r, int d) {
        // Check if there is a conflict in the row r when filling the number d
        for (int i = 0; i < SIZE; i++){
            if (grid[r][i] == d)  return true;
        }
        return false;
    }

    private boolean colConflict(int c, int d) {
        // Check if there is a conflict in the column c when filling the number d
        for (int i = 0; i < SIZE; i++){
            if (grid[i][c] == d)  return true;
        }
        return false;
    }

    private boolean boxConflict(int r, int c, int d) {
        // Check if there is a conflict in the 3x3 box containing the cell at row r and column c
        // when filling the number d
        int startRow = r - r % SUBGRIDSIZE;
        int startCol = c - c % SUBGRIDSIZE;
        for (int i = startRow; i < startRow + SUBGRIDSIZE; i++){
        for (int j = startCol; j < startCol + SUBGRIDSIZE; j++ ){
            if (grid[i][j] == d) return true;
        } 
    }

        return false;
    }
}