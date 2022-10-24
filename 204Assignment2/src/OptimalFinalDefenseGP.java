import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     *
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        Collections.sort(bombWeights, Collections.reverseOrder());
        int[] spaces = new int[maxNumberOfAvailableAUAVs];
        for (int i=0;i<maxNumberOfAvailableAUAVs;i++){
            spaces[i] = maxAUAVCapacity;
        }
        while (bombWeights.size() != 0){
            if (bombWeights.get(0) > 10){
                return -1;
            }
            if (spaces[maxNumberOfAvailableAUAVs-1] ==0){
                return -1;
            }
            for (int i =0;i<maxNumberOfAvailableAUAVs;i++){
                if (spaces[i] >= bombWeights.get(0)){
                    spaces[i] -= bombWeights.get(0);
                    bombWeights.remove(0);
                    break;
                }
            }
        }
        int counter = 0;
        for (int i =0;i<spaces.length;i++){
            if (spaces[i]!=maxAUAVCapacity){
                counter++;
            }
        }
        if (counter!=spaces.length){
            return counter;
        }
        // First sort all weights in decreasing order
        // Initialize result (Count of AUAVs)
        // Create an array to store remaining space in AUAVs, there can be at most maxNumberOfAvailableAUAVs AUAVs
        // Place items one by one
        return -1;
    }
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}
