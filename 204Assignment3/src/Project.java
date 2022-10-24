import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Project {
    private final String name;
    private final List<Task> tasks;

    public Project(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    /**
     * Schedule all tasks within this project such that they will be completed as early as possible.
     *
     * @return An integer array consisting of the earliest start days for each task.
     */
    public int[] getEarliestSchedule() {
        int[] earliestSchedule = new int[tasks.size()];
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        Stack<Integer> stack = new Stack<Integer>();
        boolean visited[] = new boolean[tasks.size()];
        for (int i = 0;i<tasks.size();i++){
            adjList.add(new ArrayList<Integer>());
        }
        for(int i = 0;i<tasks.size();i++){
            for(int j = 0;j<tasks.get(i).getDependencies().size();j++){
                int startVertex = tasks.get(i).getDependencies().get(j);
                int endVertex = tasks.get(i).getTaskID();
                adjList.get(startVertex).add(endVertex);
            }

        }
        int counter =0;
        for (int i = 0; i < tasks.size(); i++){
            if (!visited[i]) {
                topologicalSort(i,adjList, visited, stack);
            }
        }
        int[] sortedProjectsByStartTime = new int[tasks.size()];
        while (!stack.empty()) {
            sortedProjectsByStartTime[counter] = stack.pop();
            counter++;
        }
        int projectDuration = 0;
        for (int i=0;i<earliestSchedule.length;i++){
            earliestSchedule[i] = tasks.get(sortedProjectsByStartTime[i]).getDuration();
        }
        for (int i= 1;i<tasks.size();i++){
            for (int j=i-1;j>=0;j--){
                if (tasks.get(sortedProjectsByStartTime[i]).getDependencies().contains(sortedProjectsByStartTime[j])){
                    if (earliestSchedule[j]+tasks.get(sortedProjectsByStartTime[i]).getDuration()>earliestSchedule[i]){
                        earliestSchedule[i] = earliestSchedule[j]+tasks.get(sortedProjectsByStartTime[i]).getDuration();
                    }
                }
            }
            if (earliestSchedule[i] > projectDuration){
                projectDuration = earliestSchedule[i];
            }
        }
        for (int i=0;i<earliestSchedule.length;i++){
            earliestSchedule[i] -= tasks.get(sortedProjectsByStartTime[i]).getDuration();
        }
        int[] temp = new int[earliestSchedule.length];
        for (int i=0;i<earliestSchedule.length;i++){
            temp[sortedProjectsByStartTime[i]] = earliestSchedule[i];
        }
        for (int i=0;i<temp.length;i++){
            earliestSchedule[i]= temp[i];
        }
        return earliestSchedule;
    }

    /**
     * @return the total duration of the project in days
     */
    public int getProjectDuration() {
        int projectDuration = 0;
        int[] earliestSchedule = getEarliestSchedule();
        for (int i = 0;i<earliestSchedule.length;i++){
            if (earliestSchedule[i] > projectDuration){
                projectDuration = earliestSchedule[i];
            }
        }
        // TODO: YOUR CODE HERE
        projectDuration+= tasks.get(tasks.size()-1).getDuration();
        return projectDuration;
    }

    public static void printlnDash(int limit, char symbol) {
        for (int i = 0; i < limit; i++) System.out.print(symbol);
        System.out.println();
    }

    public void printSchedule(int[] schedule) {
        int limit = 65;
        char symbol = '-';
        printlnDash(limit, symbol);
        System.out.println(String.format("Project name: %s", name));
        printlnDash(limit, symbol);

        // Print header
        System.out.println(String.format("%-10s%-45s%-7s%-5s", "Task ID", "Description", "Start", "End"));
        printlnDash(limit, symbol);
        for (int i = 0; i < schedule.length; i++) {
            Task t = tasks.get(i);
            System.out.println(String.format("%-10d%-45s%-7d%-5d", i, t.getDescription(), schedule[i], schedule[i] + t.getDuration()));
        }
        printlnDash(limit, symbol);
        System.out.println(String.format("Project will be completed in %d days.", tasks.get(schedule.length - 1).getDuration() + schedule[schedule.length - 1]));
        printlnDash(limit, symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;

        int equal = 0;

        for (Task otherTask : ((Project) o).tasks) {
            if (tasks.stream().anyMatch(t -> t.equals(otherTask))) {
                equal++;
            }
        }

        return name.equals(project.name) && equal == tasks.size();
    }
    public static void topologicalSort(int vertex, ArrayList<ArrayList<Integer>> adjList, boolean visited[], Stack<Integer> stack){
        visited[vertex] = true;
        for (int i: adjList.get(vertex)){
            if(!visited[i]){
                topologicalSort(i,adjList,visited,stack);
            }
        }
        stack.push(vertex);
    }

}
