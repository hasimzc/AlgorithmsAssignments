import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Boolean hasLoop; // DO NOT change or delete this line! USED FOR AUTOGRADING!
    public static int totalSteps; // DO NOT change or delete this line! USED FOR AUTOGRADING!
    public static int loopSteps; // DO NOT change or delete this line! USED FOR AUTOGRADING!

    public static void main(String args[]) throws IOException{

        // Your code here
        hasLoop = false;
        totalSteps =1;
        loopSteps = 1;
        File file = new File(args[0]);
        Scanner scan = new Scanner(file);
        int row = scan.nextInt();
        int column = scan.nextInt();
        int selectedColumn = scan.nextInt()-1;
        int selectedRow = 0;
        String array[][] = new String[row][column];
        for( int i=0;i<row;i++){
            String line = scan.next();
            for(int j = 0;j<column;j++){
                array[i][j] = String.valueOf(line.charAt(j));
            }
        }
        int visited[][] = new int[row*column][2];
        while (true){
            if (array[selectedRow][selectedColumn].equals("L")){
                if (selectedColumn !=0 && 1 != visited[selectedRow*column+selectedColumn][0]){
                    visited[selectedRow*column+selectedColumn][0] = 1;
                    visited[selectedRow*column+selectedColumn][1] = totalSteps;
                    selectedColumn--;
                    totalSteps++;
                    loopSteps++;
                }
                else if (selectedColumn==0){
                    loopSteps=0;
                    System.out.println(totalSteps+ " step(s) to freedom. Yay!");
                    break;
                }
                else{
                    hasLoop= true;
                    loopSteps = loopSteps-visited[selectedRow*column+selectedColumn][1];
                    totalSteps--;
                    System.out.println( totalSteps-loopSteps+ " step(s) before getting stuck in a loop of "+loopSteps  +" step(s).");
                    break;
                }
            }
            else if (array[selectedRow][selectedColumn].equals("R")){
                if (selectedColumn !=column-1 && 1 != visited[selectedRow*column+selectedColumn][0]){
                    visited[selectedRow*column+selectedColumn][0] = 1;
                    visited[selectedRow*column+selectedColumn][1] = totalSteps;
                    selectedColumn++;
                    totalSteps++;
                    loopSteps++;
                }
                else if (selectedColumn==column-1){
                    loopSteps=0;
                    System.out.println(totalSteps+ " step(s) to freedom. Yay!");
                    break;
                }
                else{
                    hasLoop = true;
                    loopSteps = loopSteps-visited[selectedRow*column+selectedColumn][1];
                    totalSteps--;
                    System.out.println( totalSteps-loopSteps+ " step(s) before getting stuck in a loop of "+loopSteps  +" step(s).");
                    break;
                }
            }
            else if (array[selectedRow][selectedColumn].equals("U")){
                if (selectedRow !=0 && 1 != visited[selectedRow*column+selectedColumn][0]){
                    visited[selectedRow*column+selectedColumn][0] = 1;
                    visited[selectedRow*column+selectedColumn][1] = totalSteps;
                    selectedRow--;
                    totalSteps++;
                    loopSteps++;
                }
                else if (selectedRow==0){
                    loopSteps=0;
                    System.out.println(totalSteps+ " step(s) to freedom. Yay!");
                    break;
                }
                else{
                    hasLoop=true;
                    loopSteps = loopSteps-visited[selectedRow*column+selectedColumn][1];
                    totalSteps--;
                    System.out.println( totalSteps-loopSteps+ " step(s) before getting stuck in a loop of "+loopSteps  +" step(s).");
                    break;
                }
            }
            else if (array[selectedRow][selectedColumn].equals("D")){
                if (selectedRow !=row-1 && 1 != visited[selectedRow*column+selectedColumn][0]){
                    visited[selectedRow*column+selectedColumn][0] = 1;
                    visited[selectedRow*column+selectedColumn][1] = totalSteps;
                    selectedRow++;
                    totalSteps++;
                    loopSteps++;
                }
                else if (selectedColumn==row-1){
                    loopSteps=0;
                    System.out.println(totalSteps+ " step(s) to freedom. Yay!");
                    break;
                }
                else{
                    hasLoop=true;
                    loopSteps = loopSteps-visited[selectedRow*column+selectedColumn][1];
                    totalSteps--;
                    System.out.println( totalSteps-loopSteps+ " step(s) before getting stuck in a loop of "+loopSteps  +" step(s).");
                    break;
                }
            }
        }


        // TODO: Implement the problem here such that the member variables
        // TODO: hasLoop, totalSteps, and loopSteps declared above will be
        // TODO: assigned the proper values when your code executes
        // TODO: and the expected output will be given to STDOUT
    }

}
