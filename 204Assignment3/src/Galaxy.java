import java.util.*;

public class Galaxy {

    private final List<Planet> planets;
    private List<SolarSystem> solarSystems;
    SolarSystem solarSystem = new SolarSystem();
    HashMap<String,Integer> planetsIndex = new HashMap<>();

    public Galaxy(List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Using the galaxy's list of Planet objects, explores all the solar systems in the galaxy.
     * Saves the result to the solarSystems instance variable and returns a shallow copy of it.
     *
     * @return List of SolarSystem objects.
     */
    public List<SolarSystem> exploreSolarSystems() {
        solarSystems = new ArrayList<>();
        int vertex = planets.size();
        HashMap<String, ArrayList<String>> adjMap = new HashMap<>();
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        for (int i = 0;i<vertex;i++){
            adjMap.put(planets.get(i).getId(),new ArrayList<String>());
            visited.put(planets.get(i).getId(),false);
        }
        for(int i = 0;i<vertex;i++){
            for (int j = 0;j<planets.get(i).getNeighbors().size();j++){
                String startVertex = planets.get(i).getNeighbors().get(j);
                String endVertex = planets.get(i).getId();
                ArrayList<String> startVertexList = adjMap.get(startVertex);
                startVertexList.add(endVertex);
                ArrayList<String> endVertexList = adjMap.get(endVertex);
                endVertexList.add(startVertex);
                adjMap.put(startVertex,startVertexList);
                adjMap.put(endVertex,endVertexList);
            }

        }
        for (int i=0 ;i<planets.size();i++){
            planetsIndex.put(planets.get(i).getId(),i);
        }
        connectedComponents(vertex, planets.get(vertex-1).getId(),adjMap,visited);
        // TODO: YOUR CODE HERE
        return new ArrayList<>(solarSystems);
    }

    public List<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    // FOR TESTING
    public List<Planet> getPlanets() {
        return planets;
    }

    // FOR TESTING
    public int getSolarSystemIndexByPlanetID(String planetId) {
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            if (solarSystem.hasPlanet(planetId)) {
                return i;
            }
        }
        return -1;
    }

    public void printSolarSystems(List<SolarSystem> solarSystems) {
        System.out.printf("%d solar systems have been discovered.%n", solarSystems.size());
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            List<Planet> planets = new ArrayList<>(solarSystem.getPlanets());
            Collections.sort(planets);
            System.out.printf("Planets in Solar System %d: %s", i + 1, planets);
            System.out.println();
        }
    }
    public void dfs(int index, String  vertex, HashMap<String, ArrayList<String>> adjMap, HashMap<String, Boolean> visited){
        visited.put(vertex,true);
        solarSystem.addPlanet(planets.get(index));
        for (String i: adjMap.get(vertex)){
            if(!visited.get(i)){
                dfs(planetsIndex.get(i),i,adjMap,visited);
            }
        }
    }
    public void connectedComponents(int index, String vertex,HashMap<String, ArrayList<String>> adjMap, HashMap<String, Boolean> visited){
        int counter = 0;
        while (! planets.get(counter).getId().equals(vertex)){
            counter++;
        }
        for (int v = 0; v < counter; ++v) {
            if (!visited.get(planets.get(v).getId())) {
                dfs(v,planets.get(v).getId(), adjMap, visited);
                solarSystems.add(solarSystem);
                solarSystem = new SolarSystem();
            }
        }
    }
}
