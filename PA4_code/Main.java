import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        RandomMazeGenerator randomWalls = new RandomMazeGenerator();

        Maze maze = new Maze(randomWalls.getLengthx(),randomWalls.getLengthy(),randomWalls.getHorizontalWalls(), randomWalls.getVerticalWalls());

        System.out.println("Horizontal and vertical array concatenation.\n" + maze);
        System.out.println("Visual Representation\n" + maze.getGraphVisualRepresentation());

        MazeToGraphConverter mazeToGraphConverter = new MazeToGraphConverter(maze);
        System.out.println("Adjacency list of the graph:" + mazeToGraphConverter);

        LocalTime initialTime = LocalTime.now();

        DFS dfs = new DFS(mazeToGraphConverter.getAdjacencyList());
        System.out.println("DFS path:\n" + dfs);

        LocalTime finishedTime = LocalTime.now();
        long elapsedTime = ChronoUnit.NANOS.between(initialTime, finishedTime);
        double elapsedTimeInMilliseconds = (double) elapsedTime / 1000000;
        System.out.println("DFS took " + elapsedTimeInMilliseconds + " seconds to complete.");

        initialTime = LocalTime.now();

        BFS bfs = new BFS(mazeToGraphConverter.getAdjacencyList());
        System.out.println("BFS path:\n" + bfs);

        finishedTime = LocalTime.now();
        elapsedTime = ChronoUnit.NANOS.between(initialTime, finishedTime);
        elapsedTimeInMilliseconds = (double) elapsedTime / 1000000;
        System.out.println("BFS took " + elapsedTimeInMilliseconds + " seconds to complete.");

        System.out.println("Visual Representation of DFS\n" + maze.getDFSVisualRepresentation(dfs.getPath()));
        System.out.println("Visual Representation of BFS\n" + maze.getBFSVisualRepresentation(bfs.getPath()));

    }
}