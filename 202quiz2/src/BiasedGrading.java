import java.util.HashMap;
import java.util.Map;

public class BiasedGrading {
    private final StudentGrade[] grades;
    private final int dayOfTheWeek;
    private final double pointsToAlter;

    private final StudentGrade[] maxHeap;
    private final StudentGrade[] minHeap;

    public BiasedGrading(StudentGrade[] grades, int dayOfTheWeek, double pointsToAlter) {
        this.grades = grades;
        this.dayOfTheWeek = dayOfTheWeek;
        this.pointsToAlter = pointsToAlter;

        // Allocate the max and min heap arrays
        minHeap = new StudentGrade[grades.length];
        maxHeap = new StudentGrade[grades.length];

        // Clone the default array, shallow copy
        System.arraycopy(grades, 0, minHeap, 0, grades.length);
        System.arraycopy(grades, 0, maxHeap, 0, grades.length);
    }

    // Do changes on the map
    public void gradeInAnEvilManner() {
        // Calculate the number of students to reward 😇
        int numberOfStudentsToReward = dayOfTheWeek;

        // Calculate the number of students to punish 😈
        int numberOfStudentsToPunish = 8-dayOfTheWeek;

        rewardStudents(numberOfStudentsToReward);
        punishStudents(numberOfStudentsToPunish);
    }

    public void rewardStudents(int numberOfStudentsToReward) {
        // Build a max heap using the maxHeap variable, which contains a shallow copy of the student grades
        for (int i = maxHeap.length / 2 - 1; i >= 0; i--) {
            Heap.maxHeapify(maxHeap, maxHeap.length, i);
        }

        // TODO: Increase the grade of the top <numberOfStudentsToReward> students
        // Alter the grade of the necessary StudentGrade instances

        /* NOTE: Acknowledge that System.arraycopy() method used in the constructor shallow copies the array. So if
           you were to change the grade of a student in maxHeap array (which actually is an array of pointers), the
           same student will have their grade changed in both grades array and the minHeap array */

        // YOUR CODE HERE
        for (int i=0;i<numberOfStudentsToReward;i++){
            maxHeap[i].setGrade(maxHeap[i].getGrade() + pointsToAlter);
        }
    }

    public void punishStudents(int numberOfStudentsToPunish) {
        // Build a min heap using the minHeap variable, which contains a shallow copy of the student grades
        for (int i = minHeap.length / 2 - 1; i >= 0; i--) {
            Heap.minHeapify(minHeap, minHeap.length, i);
            if(maxHeap[i].getGrade() > 100){
                minHeap[i].setGrade(100);
            }
        }

        // TODO: Decrease the grade of the top <numberOfStudentsToPunish> students
        // Alter the grade of the necessary StudentGrade instances

        /* NOTE: Acknowledge that System.arraycopy() method used in the constructor shallow copies the array. So if
           you were to change the grade of a student in maxHeap array (which actually is an array of pointers), the
           same student will have their grade changed in both grades array and the minHeap array */

        // YOUR CODE HERE
        for (int i=0;i<numberOfStudentsToPunish;i++){
            minHeap[i].setGrade(minHeap[i].getGrade() - pointsToAlter);
            if(minHeap[i].getGrade() < 0){
                minHeap[i].setGrade(0);
            }
        }
    }
}
