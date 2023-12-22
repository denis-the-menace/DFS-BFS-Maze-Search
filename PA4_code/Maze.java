import java.util.ArrayList;
import java.util.Arrays;

public class Maze {
    final private int lengthX, lengthY;
    private final int[][] maze;

    Maze(int graphLengthX, int graphLengthY, int[][] horizontalWalls, int[][] verticalWalls) {
        lengthX = (graphLengthX * 2) + 1;
        lengthY = (graphLengthY * 2) + 1;
        maze = new int[lengthY][lengthX];

        //Below for loop concatenates horizontal and vertical 2D arrays.
        //It places a 1 for horizontal rows' indexes which are not given in the horizontalWalls,
        //and it places a 0 for vertical rows which are not given in the verticalWalls.
        for (int y = 0; y < lengthY; y++)
            for (int x = 0; x < lengthX; x++) {
                if (y % 2 == 0) { //Check if the index is horizontal.
                    if (x % 2 == 0) //Check if the index is a filler wall.
                        maze[y][x] = 1;
                    else
                        maze[y][x] = horizontalWalls[y / 2][(x - 1) / 2];
                } else { //Check if the index is vertical.
                    if (x % 2 == 0) //Check if the index is a filler empty block.
                        maze[y][x] = verticalWalls[(y - 1) / 2][x / 2];
                    else
                        maze[y][x] = 0;
                }
            }
    }

    Maze(Maze maze) {
        this.lengthY = maze.lengthY;
        this.lengthX = maze.lengthX;
        this.maze = new int[lengthY][lengthX];
        for (int y = 0; y < lengthY; y++) {
            System.arraycopy(maze.maze[y], 0, this.maze[y], 0, lengthY);
        }
    }
    
        //Places a "+" for horizontal rows' indexes which are not given in the horizontalWalls,
        //" --- " or "     " for horizontal rows' indexes which are given in the horizontalWalls,
        //"|", " " or the number of vertex for vertical rows' indexes which are given in the verticalWalls.
    private StringBuilder createVisualRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean zeroFlag = false;
        for (int y = 0; y < lengthY; y++) {
            for (int x = 0; x < lengthX; x++) {
                if (y % 2 == 0) {
                    if (x % 2 == 0)
                        stringBuilder.append("+");
                    else {
                        if (maze[y][x] == 1)
                            stringBuilder.append(" --- ");
                        else
                            stringBuilder.append("     ");
                    }
                } else {
                    if (x % 2 == 0) {
                        if (maze[y][x] == 1)
                            stringBuilder.append("|");
                        else
                            stringBuilder.append(" ");
                    } else if (maze[y][x] != 1) {
                        if (maze[y][x] == 0 && !zeroFlag) {
                            stringBuilder.append("  ").append(maze[y][x]).append("  ");
                            zeroFlag = true;
                        } else if (maze[y][x] == 0 && zeroFlag)
                            stringBuilder.append("     ");
                        else {
                            if (maze[y][x] == -1)
                                maze[y][x] = 1;
                            if (maze[y][x] / 10 == 0)
                                stringBuilder.append("  ").append(maze[y][x]).append("  ");
                            else if (maze[y][x] / 100 == 0)
                                stringBuilder.append("  ").append(maze[y][x]).append(" ");
                            else
                                stringBuilder.append(" ").append(maze[y][x]).append(" ");
                        }
                    }
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    public String getGraphVisualRepresentation() {
        Maze extendedMaze = new Maze(this);
        int counter = 0;
        for (int y = 0; y < lengthY; y++) {
            for (int x = 0; x < lengthX; x++) {
                if (y % 2 != 0) {
                    if (x % 2 != 0) {
                        //Because the walls are represented with 1, assign a different value.
                        if (counter == 1)
                            extendedMaze.maze[y][x] = -1;
                        else
                            extendedMaze.maze[y][x] = counter;
                        counter++;
                    }
                }
            }
        }
        return extendedMaze.createVisualRepresentation().toString();
    }

    public String getDFSVisualRepresentation(ArrayList<Integer> path) {
        Maze dfsMaze = new Maze(this);
        for (int i = 0; i < path.size(); i++) {
            //We wrote an explanation at the bottom of the paper (Explanation.jpeg)
            //about how the below calculation works.
            int x = (path.get(i) % (lengthX / 2)) * 2 + 1;
            int y = (path.get(i) / (lengthY / 2)) * 2 + 1;

            //Because the walls are represented with 1, assign a different value.
            if (i == 1)
                dfsMaze.maze[y][x] = -1;
            else
                dfsMaze.maze[y][x] = i;
        }
        return dfsMaze.createVisualRepresentation().toString();
    }

    public String getBFSVisualRepresentation(ArrayList<Integer> path) {
        Maze bfsMaze = new Maze(this);
        for (int i = 0; i < path.size(); i++) {
            int x = (path.get(i) % (lengthX / 2)) * 2 + 1;
            int y = (path.get(i) / (lengthY / 2)) * 2 + 1;
            //Because the walls are represented with 1, and blank spaces
            //are represented with 0, assign a different value.
            if (i == 1)
                bfsMaze.maze[y][x] = -1;
            else
                bfsMaze.maze[y][x] = i;
        }
        return bfsMaze.createVisualRepresentation().toString();
    }

    public int getLengthX() {
        return lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public int[][] getMaze() {
        return maze;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < lengthY; y++) {
            stringBuilder.append(Arrays.toString(maze[y])).append("\n");
        }

        return stringBuilder.toString();
    }
}
