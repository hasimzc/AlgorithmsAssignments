public class CountingSort {
    public void countingSort(int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int j : arr) {
            if (j < min) {
                min = j;
            }
            if (j > max) {
                max = j;
            }
        }
        int range = max-min+1;
        int[] count = new int[range];
        for (int j : arr) {
            count[j - min]++;
        }
        int p = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] != 0){
                arr[p++] = i+min;
                count[i]--;
            }
        }
    }
}
