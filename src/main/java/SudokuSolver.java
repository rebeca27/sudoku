import java.awt.Point;

public class SudokuSolver {

    private SudokuGrid grid;

    public SudokuGrid getGrid() {
        return this.grid;
    }
    public SudokuSolver(SudokuGrid initialGrid) {
        // Initialize the SudokuSolver with the provided SudokuGrid
        if ( initialGrid != null){
            this.grid = initialGrid.copy();
        }else { System.err.println("error initialGrid cannot be null");
    }
    }

    public boolean solve() {
        Point emptyCell = grid.findEmptyCell();
        if (emptyCell == null) return true;
        for (int i = 1; i <= 9; i++) {
            if (!grid.givesConflict(emptyCell.x, emptyCell.y, i)){
                grid.fillCell(emptyCell.x, emptyCell.y, i);
                System.out.println("Trying value " + i + " at (" + emptyCell.x + ", " + emptyCell.y + "):");
                grid.print();
                if (solve()) return true;
                System.out.println("Backtracking from value " + i + " at (" + emptyCell.x + ", " + emptyCell.y + "):");
                grid.fillCell(emptyCell.x, emptyCell.y, 0);
                grid.print();
            }
        }
        return false;
    }


    public static void main(String[] args) {
        // Create a SudokuGrid and a SudokuSolver
        // Call solve() to solve the puzzle and print the solution or a message if no solution is found
        int[][] initialPuzzle = {
            {1, 0, 0, 7, 0, 0, 4, 0, 0},
            {2, 6, 0, 0, 0, 0, 5, 7, 0},
            {3, 7, 0, 5, 0, 0, 9, 0, 8},
            {7, 0, 1, 3, 0, 2, 6, 0, 9},
            {4, 0, 0, 0, 0, 0, 0, 0, 7},
            {8, 2, 6, 0, 0, 0, 3, 5, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 6},
            {9, 4, 7, 0, 0, 0, 0, 0, 5},
            {6, 1, 3, 2, 7, 5, 8, 9, 4}
            
        };
        SudokuGrid grid = new SudokuGrid(initialPuzzle);
        SudokuSolver solver = new SudokuSolver(grid);
        if (solver.solve()){
            System.out.println("Solution found:");
            solver.getGrid().print();
        }
        else {
            System.out.println("No solution found");
        }
    }
}