/*
ID: zhm05251
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.*;

public class comehome {
    private static final String INPUT_FILE_NAME = "comehome.in";
    private static final String OUTPUT_FILE_NAME = "comehome.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        int[][] map = new int[52][52];
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                map[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            int x = 0;
            if ('A' <= a && a <= 'Z') {
                x = a - 'A';
            } else if ('a' <= a && a <= 'z') {
                x = a - 'a' + 26;
            }
            int y = 0;
            if ('A' <= b && b <= 'Z') {
                y = b - 'A';
            } else if ('a' <= b && b <= 'z') {
                y = b - 'a' + 26;
            }
            int d = Integer.parseInt(st.nextToken());
            if (d < map[x][y]) {
                map[x][y] = d;
                map[y][x] = d;
            }
        }
        for (int k = 0; k < 52; k++) {
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 52; j++) {
                    if (map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int k = 0;
        for (int i = 1; i < 25; i++) {
            if (map[i][25] < map[k][25]) {
                k = i;
            }
        }
        char result = (char) ('A' + k);
        out.println(result + " " + map[k][25]);
        out.close();
    }

}
