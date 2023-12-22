import java.util.*;

public class BFS {
    private final ArrayList<ArrayList<Integer>> adjacencyList;
    private final ArrayList<Integer> path;

    public BFS(ArrayList<ArrayList<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        path = new ArrayList<>();

        search();
    }

    //Method to perform BFS.
    public void search() {
        boolean[] visited = new boolean[adjacencyList.size()];
        visited[0] = true;
        Queue<Integer> frontier = new LinkedList<>();
        frontier.add(0);

        while (!frontier.isEmpty()) {
            Queue<Integer> next = new LinkedList<>();
            for (int vertex : frontier) {
                path.add(vertex);
                for (int adjacentVertex : adjacencyList.get(vertex)) {
                    if (!visited[adjacentVertex]) {
                        visited[adjacentVertex] = true;
                        next.add(adjacentVertex);
                    }
                }
            }
            frontier = next;
        }
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Integer point : path) {
            stringBuilder.append(point).append(" -> ");
        }

        stringBuilder.setLength(stringBuilder.length() - 4);
        return stringBuilder.toString();
    }
}
