import java.io.Serializable;
import java.util.*;

public class RoutingTable implements Serializable {

    static final long serialVersionUID = 99L;
    private final Router router;
    private final Network network;
    private final List<RoutingTableEntry> entryList;

    public RoutingTable(Router router) {
        this.router = router;
        this.network = router.getNetwork();
        this.entryList = new ArrayList<>();
    }

    /**
     * updateTable() should calculate routing information and then instantiate RoutingTableEntry objects, and finally add
     * them to RoutingTable object’s entryList.
     */
    public void updateTable() {

        if (entryList.size() == 0){
            for (int i = 0;i<network.getRouters().size();i++){
                if (network.getRouters().get(i) != router){
                    RoutingTableEntry routingTableEntry = new RoutingTableEntry(router.getIpAddress(),network.getRouters().get(i).getIpAddress(),pathTo(network.getRouters().get(i)));
                    entryList.add(routingTableEntry);
                }
            }
        }
        else {
            for (int i = entryList.size() + 1; i < network.getRouters().size(); i++) {
                if (network.getRouters().get(i) != router) {
                    RoutingTableEntry routingTableEntry = new RoutingTableEntry(router.getIpAddress(), network.getRouters().get(i).getIpAddress(), pathTo(network.getRouters().get(i)));
                    entryList.add(routingTableEntry);
                }
            }
        }
        for (int i = 1;i<network.getRouters().size();i++){
            if (network.getRouters().get(i) != router){
                RoutingTableEntry routingTableEntry = new RoutingTableEntry(router.getIpAddress(),network.getRouters().get(i).getIpAddress(),pathTo(network.getRouters().get(i)));
                entryList.set(i-1,routingTableEntry);
            }
        }
        // TODO: YOUR CODE HERE
    }

    /**
     * pathTo(Router destination) should return a Stack<Link> object which contains a stack of Link objects,
     * which represents a valid path from the owner Router to the destination Router.
     *
     * @param destination Destination router
     * @return Stack of links on the path to the destination router
     */
    public Stack<Link> pathTo(Router destination) {
        int routersCount = network.getRouters().size();
        double [][] adjList = new double[routersCount][routersCount];
        for (Link link1 : network.getLinks()){
            int firstIndex = -1;
            int secondIndex = -1;
            for (Router r: network.getRouters()){
                if (r.getIpAddress().equals(link1.getIpAddress1())){
                    firstIndex = network.getRouters().indexOf(r);
                    break;
                }
            }
            for (Router r: network.getRouters()){
                if (r.getIpAddress().equals(link1.getIpAddress2())){
                    secondIndex = network.getRouters().indexOf(r);
                    break;
                }
            }
            if (firstIndex != -1 && secondIndex!= -1){
                adjList[firstIndex][secondIndex] = link1.getCost();
                adjList[secondIndex][firstIndex] = link1.getCost();
            }

        }
        Stack<Link> linkStack = dijkstra(adjList,network.getRouters().indexOf(router),network.getRouters().indexOf(destination));
        // TODO: YOUR CODE
        return linkStack;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutingTable that = (RoutingTable) o;
        return router.equals(that.router) && entryList.equals(that.entryList);
    }

    public List<RoutingTableEntry> getEntryList() {
        return entryList;
    }
    int minimumDistance(double[] distance, boolean[] visited){
        int routersCount = network.getRouters().size();
        double minimum =Double.MAX_VALUE;
        int minimumIndex = 0;
        for (int i=0;i<routersCount;i++){
            if (! visited[i] && distance[i] <= minimum){
                minimum = distance[i];
                minimumIndex = i;
            }
        }
        return minimumIndex;
    }
    Stack<Link> dijkstra(double[][] adjList,int source,int destination){
        int routersCount = network.getRouters().size();
        Stack<Link>[] linksArray = new Stack[routersCount];
        for (int i = 0;i<routersCount;i++){
            linksArray[i] = new Stack<>();
        }
        double[] distance = new double[routersCount];
        boolean[] visited = new boolean[routersCount];
        for (int i = 0; i<routersCount;i++){
            distance[i] =Integer.MAX_VALUE;
            visited[i] = false;
        }
        distance[source] = 0;
        for (int i = 0;i<routersCount;i++){
            int minimumIndex =minimumDistance(distance,visited);
            visited[minimumIndex] = true;
            for (int j = 0; j <routersCount;j++){
                if (!visited[j] && adjList[minimumIndex][j]!=0 && distance[minimumIndex] != Integer.MAX_VALUE && distance[minimumIndex]+adjList[minimumIndex][j]<distance[j]){
                    distance[j] =distance[minimumIndex]+adjList[minimumIndex][j];
                    linksArray[j] = (Stack<Link>) linksArray[minimumIndex].clone();
                    linksArray[j].push(network.getLinkBetweenRouters(network.getRouters().get(minimumIndex).getIpAddress(),network.getRouters().get(j).getIpAddress()));
                }
            }
        }
        return linksArray[destination];
    }
}
