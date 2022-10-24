
public class Heap {
    /*
        → Comparable interface allows you to use the compareTo() method which is implemented in StudentGrade.java
        → You can easily call the compareTo() method to develop a more flexible code.
        → The compare to function is explained below:
        public int compareTo(Object obj): It is used to compare the current object with the specified object. It returns
            • positive integer, if the current object is greater than the specified object.
            • negative integer, if the current object is less than the specified object.
            • zero, if the current object is equal to the specified object.
    */
    public static void maxHeapify(Comparable[] maxHeap, int n, int i) {
        // TODO: Recursively create a max heap
        // YOUR CODE HERE


        int max = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(n > left && maxHeap[left].compareTo(maxHeap[max]) == 1){
            max = left;
        }
        if(n > right && maxHeap[right].compareTo(maxHeap[max]) == 1){
            max = right;
        }
        if (max != i){
            Comparable temp = maxHeap[i];
            maxHeap[i] = maxHeap[max];
            maxHeap[max] = temp;
            maxHeapify(maxHeap,n,max);
        }
    }

    public static void minHeapify(Comparable[] minHeap, int n, int i) {
        // TODO: Recursively create a min heap
        int min = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(n > left && minHeap[left].compareTo(minHeap[min]) == -1){
            min = left;
        }
        if(n > right && minHeap[right].compareTo(minHeap[min]) == -1){
            min = right;
        }
        if (min != i){
            Comparable temp = minHeap[i];
            minHeap[i] = minHeap[min];
            minHeap[min] = temp;
            minHeapify(minHeap,n,min);
        }

    }
}
