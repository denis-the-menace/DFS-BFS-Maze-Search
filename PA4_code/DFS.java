import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DFS {
    private final boolean[] visitedVertices;
    private final ArrayList<ArrayList<Integer>> adjacencyList;
    //    private final StringBuilder path;
    private final ArrayList<Integer> path;

    public DFS(ArrayList<ArrayList<Integer>> adjacencyList) {
        visitedVertices = new boolean[adjacencyList.size()];
        this.adjacencyList = adjacencyList;
        path = new ArrayList<>();

        search(0);
    }

    public void search(int vertex) {
        visitedVertices[vertex] = true;
        path.add(vertex);

        for (int adjacentVertex : adjacencyList.get(vertex))
            if (!visitedVertices[adjacentVertex])
                search(adjacentVertex);
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
