/*
ID: zhm05251
LANG: JAVA
TASK: maze1
*/

import java.io.*;
import java.util.*;

public class maze1 {
    private static final String INPUT_FILE_NAME = "maze1.in";
    private static final String OUTPUT_FILE_NAME = "maze1.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[m * 2 + 1][n * 2 + 1];
        for (int i = 0; i < m * 2 + 1; i++) {
            for (int j = 0; j < n * 2 + 1; j++) {
                map[i][j] = ' ';
            }
        }
        for (int i = 0; i < m * 2 + 1; i++) {
            String line = f.readLine();
            for (int j = 0, length = line.length(); j < length; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int[][] steps = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                steps[i][j] = Integer.MAX_VALUE;
            }
        }

        List<Position> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = false;
                if ((i == 0 && map[0][j * 2 + 1] == ' ') ||
                        (i == m - 1 && map[m * 2][j * 2 + 1] == ' ') ||
                        (j == 0 && map[i * 2 + 1][0] == ' ') ||
                        (j == n - 1 && map[i * 2 + 1][n * 2] == ' ')) {
                    flag = true;
                }
                if (flag) {
                    list.add(new Position(i, j));
                    steps[i][j] = 1;
                }
            }
        }
        int index = 0;
        while (index < list.size()) {
            Position position = list.get(index);
            int x = position.getX();
            int y = position.getY();

            if (x > 0 && map[x * 2][y * 2 + 1] == ' ') {
                if (steps[x][y] + 1 < steps[x - 1][y]) {
                    steps[x - 1][y] = steps[x][y] + 1;
                    list.add(new Position(x - 1, y));
                }
            }
            if (y > 0 && map[x * 2 + 1][y * 2] == ' ') {
                if (steps[x][y] + 1 < steps[x][y - 1]) {
                    steps[x][y - 1] = steps[x][y] + 1;
                    list.add(new Position(x, y - 1));
                }
            }
            if (x < m - 1 && map[x * 2 + 2][y * 2 + 1] == ' ') {
                if (steps[x][y] + 1 < steps[x + 1][y]) {
                    steps[x + 1][y] = steps[x][y] + 1;
                    list.add(new Position(x + 1, y));
                }
            }
            if (y < n - 1 && map[x * 2 + 1][y * 2 + 2] == ' ') {
                if (steps[x][y] + 1 < steps[x][y + 1]) {
                    steps[x][y + 1] = steps[x][y] + 1;
                    list.add(new Position(x, y + 1));
                }
            }
            index++;
        }
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (steps[i][j] > result) {
                    result = steps[i][j];
                }
            }
        }
        out.println(result);
        out.close();
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
