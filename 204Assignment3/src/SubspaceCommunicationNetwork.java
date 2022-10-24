import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubspaceCommunicationNetwork {

    private List<SolarSystem> solarSystems;

    /**
     * Perform initializations regarding your implementation if necessary
     * @param solarSystems a list of SolarSystem objects
     */
    public SubspaceCommunicationNetwork(List<SolarSystem> solarSystems) {
        // TODO: YOUR CODE HERE
        this.solarSystems = solarSystems;
    }

    /**
     * Using the solar systems of the network, generates a list of HyperChannel objects that constitute the minimum cost communication network.
     * @return A list HyperChannel objects that constitute the minimum cost communication network.
     */
    public List<HyperChannel> getMinimumCostCommunicationNetwork() {
        List<HyperChannel> minimumCostCommunicationNetwork = new ArrayList<>();
        HashMap<Integer, HashMap <Integer,Integer>> maxTechnologies = new HashMap<>();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        boolean visited[] = new boolean[solarSystems.size()];
        for (int i = 0;i<solarSystems.size();i++){
            adjList.add(new ArrayList<Integer>());
            visited[i] = false;
        }
        for (int i =0;i<solarSystems.size();i++){
            int max = 0;
            int maxIndex = 0;
            for(int j=0;j<solarSystems.get(i).getPlanets().size();j++){
                if (solarSystems.get(i).getPlanets().get(j).getTechnologyLevel()> max){
                    max = solarSystems.get(i).getPlanets().get(j).getTechnologyLevel();
                    maxIndex = j;
                }
            }
            HashMap<Integer,Integer> a = new HashMap<>();
            a.put(max,maxIndex);
            maxTechnologies.put(i,a);
        }
        int maxSolarSystemIndex = 0;
        int maxPlanetTechnology = 0;
        int maxPlanetIndex = 0;
        for (int i=0;i<solarSystems.size();i++){
            for (int key: maxTechnologies.get(i).keySet()){
                if (key > maxPlanetTechnology){
                    maxPlanetTechnology = key;
                    maxSolarSystemIndex = i;
                    maxPlanetIndex = maxTechnologies.get(i).get(key);
                }
            }
        }
        for (int key: maxTechnologies.keySet()){
            if (key != maxSolarSystemIndex){
                for (int key2: maxTechnologies.get(key).keySet()){
                    HyperChannel hyperChannel = new HyperChannel(solarSystems.get(key).getPlanets().get(maxTechnologies.get(key).get(key2)),solarSystems.get(maxSolarSystemIndex).getPlanets().get(maxPlanetIndex),Constants.SUBSPACE_COMMUNICATION_CONSTANT/(((double)key2+(double) maxPlanetTechnology)/2));
                    minimumCostCommunicationNetwork.add(hyperChannel);
                }

            }
        }
        // TODO: YOUR CODE HERE
        return minimumCostCommunicationNetwork;
    }

    public void printMinimumCostCommunicationNetwork(List<HyperChannel> network) {
        double sum = 0;
        for (HyperChannel channel : network) {
            Planet[] planets = {channel.getFrom(), channel.getTo()};
            Arrays.sort(planets);
            System.out.printf("Hyperchannel between %s - %s with cost %f", planets[0], planets[1], channel.getWeight());
            System.out.println();
            sum += channel.getWeight();
        }
        System.out.printf("The total cost of the subspace communication network is %f.", sum);
        System.out.println();
    }
}
