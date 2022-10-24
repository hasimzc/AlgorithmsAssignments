import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int N = -1;
        int M = -1;
        int[] a = null;
        int[] b = null;

        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);

            N = myReader.nextInt();
            M = myReader.nextInt();
            a = new int[M];
            b = new int[M];
            for(int i = 0 ; i < M; i++){
                a[i] = myReader.nextInt();
                b[i] = myReader.nextInt();
            }

            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(maximumGain(N, M, a, b));
    }

    public static int maximumGain(int N, int M, int[] a, int[] b) {

        // TODO fill this function
        int counter = 0;
        for (int i=0;i<N;i++){
            int maxIndex = 0;
            for(int j=0;j<b.length;j++){
                if (b[j] > b[maxIndex]){
                    maxIndex = j;
                }
            }
            if (a[maxIndex] !=1){
                counter+=b[maxIndex];
                a[maxIndex]--;
            }
            else{
                counter+=b[maxIndex];
                b[maxIndex] = 0;
            }
        }
        // Return the result
        return counter;
    }
}