/*
ID: zhm05251
LANG: JAVA
TASK: betsy
*/

import java.io.*;
import java.util.*;

public class betsy {
    private static final String INPUT_FILE_NAME = "betsy.in";
    private static final String OUTPUT_FILE_NAME = "betsy.out";

    private static int n, s;
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new boolean[n][n];
        map[0][0] = true;
        s = 0;
        search(0, 0, 1);

        out.println(String.valueOf(s));
        out.close();
    }

    private static void search(int x, int y, int t) {
        if (x == n - 1 && y == 0) {
            if (t == n * n) {
                s++;
            }
            return;
        }

        if (x == 0 || x == n - 1) {
            if (0 <= y - 1 && y + 1 < n) {
                if (!map[x][y - 1] && !map[x][y + 1]) {
                    return;
                }
            }
        }

        if (y == 0 || y == n - 1) {
            if (0 <= x - 1 && x + 1 < n) {
                if (!map[x - 1][y] && !map[x + 1][y]) {
                    return;
                }
            }
        }

        if (0 <= x - 1 && x + 1 < n && 0 <= y - 1 && y + 1 < n) {
            if (map[x][y - 1] && map[x][y + 1] && !map[x - 1][y] && !map[x + 1][y]) {
                return;
            }
            if (!map[x][y - 1] && !map[x][y + 1] && map[x - 1][y] && map[x + 1][y]) {
                return;
            }
        }

        int flagCount = 0;
        if (x - 1 >= 0 && !map[x - 1][y]) {
            if (getAvailableRouteCount(x - 1, y) <= 1) {
                flagCount++;
            }
        }
        if (x + 1 < n && !map[x + 1][y]) {
            if (getAvailableRouteCount(x + 1, y) <= 1) {
                flagCount++;
            }
        }
        if (y - 1 >= 0 && !map[x][y - 1]) {
            if (getAvailableRouteCount(x, y - 1) <= 1) {
                flagCount++;
            }
        }
        if (y + 1 < n && !map[x][y + 1]) {
            if (getAvailableRouteCount(x, y + 1) <= 1) {
                flagCount++;
            }
        }
        if (flagCount >= 2) {
            return;
        } else if (flagCount == 1) {
            if (x - 1 >= 0 && !map[x - 1][y]) {
                if (getAvailableRouteCount(x - 1, y) <= 1) {
                    map[x - 1][y] = true;
                    search(x - 1, y, t + 1);
                    map[x - 1][y] = false;
                }
            }
            if (x + 1 < n && !map[x + 1][y]) {
                if (getAvailableRouteCount(x + 1, y) <= 1) {
                    map[x + 1][y] = true;
                    search(x + 1, y, t + 1);
                    map[x + 1][y] = false;
                }
            }
            if (y - 1 >= 0 && !map[x][y - 1]) {
                if (getAvailableRouteCount(x, y - 1) <= 1) {
                    map[x][y - 1] = true;
                    search(x, y - 1, t + 1);
                    map[x][y - 1] = false;
                }
            }
            if (y + 1 < n && !map[x][y + 1]) {
                if (getAvailableRouteCount(x, y + 1) <= 1) {
                    map[x][y + 1] = true;
                    search(x, y + 1, t + 1);
                    map[x][y + 1] = false;
                }
            }
            return;
        }

        if (x - 1 >= 0 && !map[x - 1][y]) {
            map[x - 1][y] = true;
            search(x - 1, y, t + 1);
            map[x - 1][y] = false;
        }

        if (x + 1 < n && !map[x + 1][y]) {
            map[x + 1][y] = true;
            search(x + 1, y, t + 1);
            map[x + 1][y] = false;
        }

        if (y - 1 >= 0 && !map[x][y - 1]) {
            map[x][y - 1] = true;
            search(x, y - 1, t + 1);
            map[x][y - 1] = false;
        }

        if (y + 1 < n && !map[x][y + 1]) {
            map[x][y + 1] = true;
            search(x, y + 1, t + 1);
            map[x][y + 1] = false;
        }
    }

    private static int getAvailableRouteCount(int x, int y) {
        int count = 0;
        if (x - 1 >= 0 && !map[x - 1][y]) {
            count++;
        }
        if (x + 1 < n && !map[x + 1][y]) {
            count++;
        }
        if (y - 1 >= 0 && !map[x][y - 1]) {
            count++;
        }
        if (y + 1 < n && !map[x][y + 1]) {
            count++;
        }
        if (x == n - 1 && y == 0) {
            count++;
        }
        return count;
    }
}
