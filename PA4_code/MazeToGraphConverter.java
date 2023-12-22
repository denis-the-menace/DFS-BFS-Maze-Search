import java.util.ArrayList;
import java.util.Arrays;

public class MazeToGraphConverter {

    private final int lengthX, lengthY;
    private final ArrayList<ArrayList<Integer>> adjacencyList;

    MazeToGraphConverter(Maze maze) {
        lengthX = (maze.getLengthX() - 1) / 2;
        lengthY = (maze.getLengthY() - 1) / 2;
        adjacencyList = new ArrayList<>();

        for (int i = 0; i < lengthX * lengthY; i++)
            adjacencyList.add(new ArrayList<Integer>());

        for (int y = 0; y < lengthY; y++)
            for (int x = 0; x < lengthX; x++)
                if (maze.getMaze()[(y * 2) + 1][(x * 2) + 1] == 0)
                    checkAdjacentVertices(maze, (x * 2) + 1, (y * 2) + 1);
    }

    public void checkAdjacentVertices(Maze maze, int x, int y) {
        int vertex = getVertexNumber(x, y);
        try {
            if (maze.getMaze()[y + 1][x] == 0)
                addVertexEdge(vertex, getVertexNumber(x, (y + 2)));
            if (maze.getMaze()[y - 1][x] == 0)
                addVertexEdge(vertex, getVertexNumber(x, (y - 2)));
            if (maze.getMaze()[y][x + 1] == 0)
                addVertexEdge(vertex, getVertexNumber((x + 2), y));
            if (maze.getMaze()[y][x - 1] == 0)
                addVertexEdge(vertex, getVertexNumber((x - 2), y));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Vertices are numbered from left to right and top to bottom (0, 1, 2, 3, 4,
    //                                                            5, 6, 7, 8, 9 etc.)
    public int getVertexNumber(int x, int y) {
        y = (y - 1) / 2;
        x = (x - 1) / 2;
        return (y * lengthX) + x;
    }

    public void addVertexEdge(int u, int v) {
        adjacencyList.get(u).add(v);
    }

    public int getLengthX() {
        return lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public ArrayList<ArrayList<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < adjacencyList.size(); i++) {
            stringBuilder.append("\nAdjacency list of vertex ").append(i);
            for (int j = 0; j < adjacencyList.get(i).size(); j++)
                stringBuilder.append(" -> ").append(adjacencyList.get(i).get(j));
        }
        return stringBuilder.toString();
    }
}
