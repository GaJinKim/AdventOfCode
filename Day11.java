/**
 * Naive Solution, but gets the job done.
 * 
 * https://adventofcode.com/2020/day/11
 */
public class Day11 {
    public static void main(String args[]) {
        char[][] grid = new char[10][10];
        grid[0] = new char[]{'L', '.', 'L', 'L', '.', 'L','L','.','L','L'};
        grid[1] = new char[]{'L', 'L', 'L', 'L', 'L', 'L','L','.','L','L'};
        grid[2] = new char[]{'L', '.', 'L', '.', 'L', '.','.','L','.','.'};
        grid[3] = new char[]{'L', 'L', 'L', 'L', 'L', 'L','L','.','L','L'};
        grid[4] = new char[]{'L', '.', 'L', 'L', '.', 'L','L','.','L','L'};
        grid[5] = new char[]{'L', '.', 'L', 'L', '.', 'L','L','.','L','L'};
        grid[6] = new char[]{'.', '.', 'L', '.', 'L', '.','.','.','.','.'};
        grid[7] = new char[]{'L', 'L', 'L', 'L', 'L', 'L','L','L','L','L'};
        grid[8] = new char[]{'L', '.', 'L', 'L', 'L', 'L','L','L','.','L'};
        grid[9] = new char[]{'L', '.', 'L', 'L', 'L', 'L','L','.','L','L'};
        int runs = 5;

        System.out.println("Default");
        printGrid(grid);

        for (int i = 0; i < runs; i++) {
            System.out.println("Iteration: " + (i + 1));
            grid = updateFloor(grid);
            printGrid(grid);
        }
    }
    
    private static char[][] updateFloor(char[][] grid) {
        char[][] tempGrid = deepCopyGrid(grid);
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int filledSeats = 0;
                switch (grid[i][j]) {
                    case 'L':
                        // top left corner
                        if (i == 0 && j == 0) {
                            if (tempGrid[i][j + 1] != '#' && tempGrid[i + 1][j] != '#' && tempGrid[i + 1][j + 1] != '#')
                                grid[i][j] = '#';
                        }
                        // top right corner
                        else if (i == 0 && j == grid[0].length - 1) {
                            if (tempGrid[i][j - 1] != '#' && tempGrid[i + 1][j - 1] != '#' && tempGrid[i + 1][j] != '#')
                                grid[i][j] = '#';
                        }
                        // bottom left corner
                        else if (i == grid.length - 1 && j == 0) {
                            if (tempGrid[i][j + 1] != '#' && tempGrid[i - 1][j + 1] != '#' && tempGrid[i - 1][j] != '#')
                                grid[i][j] = '#';
                        }
                        // bottom right corner
                        else if (i == grid.length - 1 && j == grid[0].length - 1) {
                            if (tempGrid[i][j - 1] != '#' && tempGrid[i - 1][j - 1] != '#' && tempGrid[i - 1][j] != '#')
                                grid[i][j] = '#';
                        }
                        // top side (non corner)
                        else if (i == 0) {
                            if (tempGrid[i + 1][j - 1] != '#' && tempGrid[i + 1][j] != '#' && tempGrid[i + 1][j + 1] != '#'  && tempGrid[i][j - 1] != '#' && tempGrid[i][j + 1] != '#')
                                grid[i][j] = '#';
                        } 
                        // bottom side (non corner)
                        else if (i == grid.length - 1) {
                            if (tempGrid[i - 1][j - 1] != '#' && tempGrid[i - 1][j] != '#' && tempGrid[i - 1][j + 1] != '#' && tempGrid[i][j - 1] != '#' && tempGrid[i][j + 1] != '#')
                                grid[i][j] = '#';
                        }     
                        // right side (non corner)
                        else if (j == grid[0].length - 1) {
                            if (tempGrid[i - 1][j - 1] != '#' && tempGrid[i][j - 1] != '#' && tempGrid[i + 1][j - 1] != '#' && tempGrid[i - 1][j] != '#' && tempGrid[i + 1][j] != '#')
                                grid[i][j] = '#';
                        }     
                        // left side (non corner)
                        else if (j == 0) {
                            if (tempGrid[i - 1][j + 1] != '#' && tempGrid[i][j + 1] != '#' && tempGrid[i + 1][j + 1] != '#' && tempGrid[i - 1][j] != '#' && tempGrid[i + 1][j] != '#')
                                grid[i][j] = '#';
                        } 
                        // all other
                        else {
                            if (tempGrid[i - 1][j - 1] != '#' && tempGrid[i - 1][j] != '#' && tempGrid[i - 1][j + 1] != '#' && 
                                tempGrid[i][j + 1] != '#' && tempGrid[i][j - 1] != '#' && tempGrid[i + 1][j - 1] != '#' && tempGrid[i + 1][j] != '#' && tempGrid[i + 1][j + 1] != '#') {
                                grid[i][j] = '#';
                            }
                        }
                    case '#':
                        if (i != 0 && i != grid.length - 1 && j != 0 && j != grid[0].length - 1) {
                            if (tempGrid[i - 1][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i - 1][j] == '#')
                                filledSeats++;
                            if (tempGrid[i - 1][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i][j - 1] == '#')
                                filledSeats++;
                                
                            if (filledSeats >= 4)
                                grid[i][j] = 'L';
                        }
                        // top side (non corner)
                        else if (i == 0 && j != 0 && j != grid[0].length - 1) {
                            if (tempGrid[i][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j + 1] == '#')
                                filledSeats++;
                                
                            if (filledSeats >= 4)
                                grid[i][j] = 'L';
                        }
                        // bottom side (non corner)
                        else if (i == grid.length - 1 && j != 0 && j != grid[0].length - 1) {
                            if (tempGrid[i][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i - 1][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i - 1][j] == '#')
                                filledSeats++;
                            if (tempGrid[i - 1][j + 1] == '#')
                                filledSeats++;
                                
                            if (filledSeats >= 4)
                                grid[i][j] = 'L';
                        }
                        // left side (non corner)
                        else if (j == 0 && i != 0 && i != grid.length - 1) {
                            if (tempGrid[i - 1][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j + 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j] == '#')
                                filledSeats++;
                            if (tempGrid[i - 1][j] == '#')
                                filledSeats++;
                                
                            if (filledSeats >= 4)
                                grid[i][j] = 'L';
                        }
                        // right side (non corner)
                        else if (j == grid[0].length - 1 && i != 0 && i != grid.length - 1) {
                            if (tempGrid[i - 1][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j - 1] == '#')
                                filledSeats++;
                            if (tempGrid[i + 1][j] == '#')
                                filledSeats++;
                            if (tempGrid[i - 1][j] == '#')
                                filledSeats++;
                                
                            if (filledSeats >= 4)
                                grid[i][j] = 'L';
                        }
                }
            }
        }
        return grid;
    }
    
    private static char[][] deepCopyGrid(char[][] grid) {
        char[][] newGrid = new char[grid.length][grid[0].length];
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        return newGrid;
    }
    
    private static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}