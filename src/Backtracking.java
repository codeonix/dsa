public class Backtracking {
    public static boolean ratInMaze(int[][] maze) {
      int n = maze.length;
      int[][] path = new int[n][n];
      return ratInMazeHelper(maze,0,0,path);
    }

    public static boolean ratInMazeHelper(int[][] maze , int i , int j, int[][] path) {
        int n = maze.length;
        //Check if the cell is valid or not :
        if(i < 0 || j < 0 || i >= n || j >= n || maze[i][j] == 0 || path[i][j] == 1) {
         return false;
       }

        //Include the cell in current path :
        path[i][j] = 1;

        //If we have reached destination :
        if(i == n-1 && j == n-1) {
            path[i][j] = 0;
            return true;
        }

        //Explore in all directions :
        if(ratInMazeHelper(maze,i-1,j,path)) {
            return true; //top
        }
        if ( ratInMazeHelper(maze, i , j+1 , path)) {
            return true; //right
        }
        if (ratInMazeHelper(maze,i+1,j, path)) {
            return true; //down
        }
        if (ratInMazeHelper(maze,i,j-1, path)) {
            return true; //left
        }

        return false;
    }

    public static void ratInMazeAllPath(int[][] maze) {
        int n = maze.length;
        int[][] path = new int[n][n];
        printAllPath(maze, 0,0,path);
    }

    private static void printAllPath(int[][] maze, int i, int j, int[][] path) {
        int n = maze.length;
        //check if it is valid cell or not :
        if(i < 0 || j < 0 || i >= n || j >= n || maze[i][j] ==0 || path[i][j] == 1) {
            return;
        }

        //Include into path:
        path[i][j] = 1;

        //check if the cell is destination cell : print and clear for another solution possible :
        if(i == n-1 && j == n-1) {
            for (int k = 0; k < path.length; k++) {
                for (int l = 0; l < path[k].length; l++) {
                    System.out.print(path[k][l]+ " ");
                }
            }
            System.out.println();
            path[i][j] = 0;
            return;
        }

        printAllPath(maze,i-1,j,path);
        printAllPath(maze,i,j+1,path);
        printAllPath(maze,i+1,j,path);
        printAllPath(maze,i,j-1,path);

        path[i][j] = 0;
    }


    public static void placeNQueen(int n) {
        int[][] board = new int[n][n];
        placeQueen(board,0, n);
    }

    private static void placeQueen(int[][] board, int i, int n) {
        if(i == n) {

        }
    }


    public static void main(String[] args) {
     int[][] maze = {{1,1,0}, {1,1,0}, {1,1,1}};
     boolean isPathPossible = ratInMaze(maze);
        System.out.println(isPathPossible);
        ratInMazeAllPath(maze);
    }

}
