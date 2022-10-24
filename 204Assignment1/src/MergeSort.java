
public class MergeSort {

    void mergeSort(int[] arr, int left, int right)
    {
        if (left < right) {
            int mid =left+ (right-left)/2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }


    void merge(int[] arr, int left, int mid, int right)
    {
        int leftN = mid - left + 1;
        int rightN = right - mid;

        int[] L = new int[leftN];
        int[] R = new int[rightN];

        for (int i = 0; i < leftN; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < rightN; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0;
        int j = 0;
        int k = left;
        while (i < leftN && j < rightN) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            }
            else {
                arr[k++] = R[j++];
            }
        }

        while (i < leftN) {
            arr[k++] = L[i++];
        }

        while (j < rightN) {
            arr[k++] = R[j++];
        }
    }

}
