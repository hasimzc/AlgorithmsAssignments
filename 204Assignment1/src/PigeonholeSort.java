
public class PigeonholeSort {
    public void pigeonholeSort(int[] arr){
        int n = arr.length;
        int min = arr[0];
        int max = arr[0];
        for (int k : arr) {
            if (k < min) {
                min = k;
            }
            if (k > max) {
                max = k;
            }
        }
        int range = max - min +1;
        int[] holes = new int[range];
        for (int k : arr) holes[k - min]++;

        int ct = 0;
        for (int j = 0; j < range; j++){
            while (holes[j]-- > 0)
                arr[ct++] = j + min;
        }
    }

}
