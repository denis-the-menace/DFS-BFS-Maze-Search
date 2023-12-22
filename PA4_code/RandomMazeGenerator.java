public class RandomMazeGenerator {
    private final int lengthx, lengthy;

    private final int[][] horizontalWalls, verticalWalls;

    RandomMazeGenerator() {
        lengthx = (int) (Math.random() * (15 - 5)) + 5;
        lengthy = lengthx;
        horizontalWalls = new int[lengthy + 1][lengthx];
        verticalWalls = new int[lengthy][lengthx + 1];

        for (int y = 0; y < lengthy + 1; y++) {
            for (int x = 0; x < lengthx; x++) {
                if (y == 0 || y == lengthy)
                    horizontalWalls[y][x] = 1;
                else
                    horizontalWalls[y][x] = (int) Math.round(Math.random());
            }
        }

        for (int y = 0; y < lengthy; y++) {
            for (int x = 0; x < lengthx + 1; x++) {
                if (x == 0 || x == lengthx)
                    verticalWalls[y][x] = 1;
                else
                    verticalWalls[y][x] = (int) Math.round(Math.random());
            }
        }
    }

    public int getLengthx() {
        return lengthx;
    }

    public int getLengthy() {
        return lengthy;
    }

    public int[][] getHorizontalWalls() {
        return horizontalWalls;
    }

    public int[][] getVerticalWalls() {
        return verticalWalls;
    }
}
