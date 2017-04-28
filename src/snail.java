/*
ID: zhm05251
LANG: JAVA
TASK: snail
*/

import java.io.*;
import java.util.*;

public class snail {

    private static final String INPUT_FILE_NAME = "snail.in";
    private static final String OUTPUT_FILE_NAME = "snail.out";

    private static final int[][] DIRECTION = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int n;
    private static int[][] map;
    private static int maxStep = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            String input = f.readLine();
            int x = Integer.parseInt(input.substring(1)) - 1;
            int y = input.charAt(0) - 'A';
            map[x][y] = -1;
        }
        map[0][0] = 1;
        search(0, 0, 1, 0);
        search(0, 0, 1, 1);
        out.println(String.valueOf(maxStep));
        out.close();
    }

    private static void search(int x, int y, int step, int d) {
        if (step > maxStep) {
            maxStep = step;
        }
        int xn = x + DIRECTION[d][0];
        int yn = y + DIRECTION[d][1];

        if (xn < 0 || xn >= n || yn < 0 || yn >= n || map[xn][yn] < 0) {
            int d1 = (d + 1) % 4;
            int x1 = x + DIRECTION[d1][0];
            int y1 = y + DIRECTION[d1][1];
            if (0 <= x1 && x1 < n && 0 <= y1 && y1 < n && map[x1][y1] == 0) {
                map[x1][y1] = 1;
                search(x1, y1, step + 1, d1);
                map[x1][y1] = 0;
            }

            int d3 = (d + 3) % 4;
            int x3 = x + DIRECTION[d3][0];
            int y3 = y + DIRECTION[d3][1];
            if (0 <= x3 && x3 < n && 0 <= y3 && y3 < n && map[x3][y3] == 0) {
                map[x3][y3] = 1;
                search(x3, y3, step + 1, d3);
                map[x3][y3] = 0;
            }
        } else if (map[xn][yn] == 0) {
            map[xn][yn] = 1;
            search(xn, yn, step + 1, d);
            map[xn][yn] = 0;
        }
    }
}
