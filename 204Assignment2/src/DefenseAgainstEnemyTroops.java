import java.util.ArrayList;
import java.util.List;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){

        ArrayList<Integer> sol = new ArrayList<>();
        sol.add(0);
        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();
        ArrayList<Integer> emptyList = new ArrayList<>();
        hours.add(emptyList);
        for (int j=1;j<numberOfEnemiesArrivingPerHour.size();j++){
            int max = 0;
            int maxIndex = 0;
            for (int i =0;i<j;i++){
                if (sol.get(i)+Math.min(numberOfEnemiesArrivingPerHour.get(j-1),getRechargedWeaponPower(j-i)) > max){
                    max = sol.get(i)+Math.min(numberOfEnemiesArrivingPerHour.get(j-1),getRechargedWeaponPower(j-i));
                    maxIndex = i;
                }
            }
            sol.add(max);
            ArrayList<Integer> temp = new ArrayList<>();
            for (int k =0;k<hours.get(maxIndex).size();k++){
                temp.add(hours.get(maxIndex).get(k));
            }
            temp.add(j);
            hours.add(temp);
        }
        int maxNumberOfKilledEnemies = 0;
        int hoursIndex = 0;
        for(int i = 0;i<sol.size();i++){
            if(sol.get(i)+Math.min(numberOfEnemiesArrivingPerHour.get(sol.size()-1),getRechargedWeaponPower(sol.size()-i)) > maxNumberOfKilledEnemies){
                maxNumberOfKilledEnemies = sol.get(i)+Math.min(numberOfEnemiesArrivingPerHour.get(sol.size()-1),getRechargedWeaponPower(sol.size()-i));
                hoursIndex=i;
            }
        }
        hours.get(hoursIndex).add(numberOfEnemiesArrivingPerHour.size());
        OptimalEnemyDefenseSolution optimalEnemyDefenseSolution = new OptimalEnemyDefenseSolution(maxNumberOfKilledEnemies,hours.get(hoursIndex));
        return optimalEnemyDefenseSolution;
        // TODO: YOUR CODE HERE
    }
}
