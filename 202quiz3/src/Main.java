import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {


        int[][] maze = null;
        int[] sizes = new int[2];
        int[] goal = new int[2];
        sizes[0]= 5;
        sizes[1] = 3;

        maze = new int[sizes[0]][sizes[1]];
        maze[0][0] = 0;
        maze[0][1] = 0;
        maze[0][2] = 0;
        maze[1][0] = 0;

        maze[1][1] = 0;
        maze[1][2] = 0;
        maze[2][0] = 0;
        maze[2][1] = 0;
        maze[2][2] = 0;
        maze[3][0] = 0;
        maze[3][1] = 0;
        maze[3][2] = 0;
        maze[4][0] = 0;
        maze[4][1] = 0;
        maze[4][2] = 0;

        goal[0] = 4;
        goal[1] = 2;




        System.out.println(solve(maze,goal,sizes));



    }

    public static int solve(int[][] maze, int[] goal, int[] sizes) {

        // TODO fill this function
        int row = sizes[0];
        int column = sizes[1];

        if (maze[0][0] == 1) {
            return 0;
        }

        maze[0][0] = 1;


        for (int i = 1; i < row; i++) {
            maze[i][0] = (maze[i][0] == 0 && maze[i - 1][0] == 1) ? 1 : 0;
        }
        
        for (int i = 1; i < column; i++) {
            maze[0][i] = (maze[0][i] == 0 && maze[0][i - 1] == 1) ? 1 : 0;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (maze[i][j] == 0) {
                    maze[i][j] = maze[i - 1][j] + maze[i][j - 1];
                } else {
                    maze[i][j] = 0;
                }
            }
        }
        return maze[goal[0]][goal[1]];

    }
}
