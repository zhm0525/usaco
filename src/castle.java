/*
ID: zhm05251
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.util.*;

public class castle {
    private static final String INPUT_FILE_NAME = "castle.in";
    private static final String OUTPUT_FILE_NAME = "castle.out";

    private static int m, n;
    private static int[][] map;

    private static int[][] room;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        room = new int[m][n];
        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (room[i][j] == 0) {
                    num++;
                    room[i][j] = num;

                    List<Position> list = new ArrayList<>();
                    list.add(new Position(i, j));
                    int r = 0;
                    while (r < list.size()) {
                        Position position = list.get(r);
                        int x = position.getX();
                        int y = position.getY();
                        int walls = map[x][y];
                        if (!hasWestWall(walls) && isPositionAvailable(x, y - 1) && room[x][y - 1] == 0) {
                            room[x][y - 1] = num;
                            list.add(new Position(x, y - 1));
                        }
                        if (!hasNorthWall(walls) && isPositionAvailable(x - 1, y) && room[x - 1][y] == 0) {
                            room[x - 1][y] = num;
                            list.add(new Position(x - 1, y));
                        }
                        if (!hasEastWall(walls) && isPositionAvailable(x, y + 1) && room[x][y + 1] == 0) {
                            room[x][y + 1] = num;
                            list.add(new Position(x, y + 1));
                        }
                        if (!hasSouthWall(walls) && isPositionAvailable(x + 1, y) && room[x + 1][y] == 0) {
                            room[x + 1][y] = num;
                            list.add(new Position(x + 1, y));
                        }
                        r++;
                    }
                }
            }
        }
        out.println(num);

        int[] roomSize = new int[num + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                roomSize[room[i][j]]++;
            }
        }
        int largestRoomSize = 0;
        for (int i = 0; i <= num; i++) {
            if (roomSize[i] > largestRoomSize) {
                largestRoomSize = roomSize[i];
            }
        }
        out.println(largestRoomSize);

        int largestCreatableRoomSize = 0;
        int targetX = 0;
        int targetY = 0;
        int direction = 0; // 1 for N, 2 for E
        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if (i > 0 && hasNorthWall(map[i][j]) && room[i][j] != room[i - 1][j] &&
                        roomSize[room[i][j]] + roomSize[room[i - 1][j]] > largestCreatableRoomSize) {
                    largestCreatableRoomSize = roomSize[room[i][j]] + roomSize[room[i - 1][j]];
                    targetX = i + 1;
                    targetY = j + 1;
                    direction = 1;
                }
                if (j < n - 1 && hasEastWall(map[i][j]) && room[i][j] != room[i][j + 1] &&
                        roomSize[room[i][j]] + roomSize[room[i][j + 1]] > largestCreatableRoomSize) {
                    largestCreatableRoomSize = roomSize[room[i][j]] + roomSize[room[i][j + 1]];
                    targetX = i + 1;
                    targetY = j + 1;
                    direction = 2;
                }
            }
        }
        out.println(largestCreatableRoomSize);
        out.print(targetX + " " + targetY + " ");
        if (direction == 1) {
            out.println("N");
        } else if (direction == 2) {
            out.println("E");
        }
        out.close();
    }

    private static boolean isPositionAvailable(Position position) {
        return isPositionAvailable(position.getX(), position.getY());
    }

    private static boolean isPositionAvailable(int x, int y) {
        if (x < 0 || x >= m) {
            return false;
        }
        if (y < 0 || y >= n) {
            return false;
        }
        return true;
    }

    private static boolean hasWestWall(int walls) {
        walls = walls >= 8 ? walls - 8 : walls;
        walls = walls >= 4 ? walls - 4 : walls;
        walls = walls >= 2 ? walls - 2 : walls;
        return walls >= 1;
    }

    private static boolean hasNorthWall(int walls) {
        walls = walls >= 8 ? walls - 8 : walls;
        walls = walls >= 4 ? walls - 4 : walls;
        return walls >= 2;
    }

    private static boolean hasEastWall(int walls) {
        walls = walls >= 8 ? walls - 8 : walls;
        return walls >= 4;
    }

    private static boolean hasSouthWall(int walls) {
        return walls >= 8;
    }

    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}
