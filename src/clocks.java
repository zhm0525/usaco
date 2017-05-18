/*
ID: zhm05251
LANG: JAVA
TASK: clocks
*/

import java.io.*;
import java.util.*;

public class clocks {

    private static final String INPUT_FILE_NAME = "clocks.in";
    private static final String OUTPUT_FILE_NAME = "clocks.out";

    private static final int MAX = 262144; // 4 ^ 9

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        final int[][] operation = new int[][]{
                {1, 1, 0, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 1, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 0, 1, 1},
        };

        int[] a = new int[9];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < 9; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            a[i] = Integer.parseInt(st.nextToken()) / 3 % 4;
        }

        boolean[] flag = new boolean[MAX];
        int[][] queue = new int[MAX][9];
        int[] parent = new int[MAX];
        int[] method = new int[MAX];

        flag[convert(a)] = true;
        queue[0] = a;
        method[0] = 0;
        int m = 1, x = 0;

        while (!flag[0] && x < m) {
            for (int i = method[x]; i < 9; i++) {
                for (int j = 1; j <= 3; j++) {
                    int[] b = new int[9];
                    for (int k = 0; k < 9; k++) {
                        b[k] = (queue[x][k] + operation[i][k] * j) % 4;
                    }
                    int c = convert(b);
                    if (!flag[c]) {
                        flag[c] = true;
                        queue[m] = b;
                        parent[m] = j == 1 ? x : m - 1;
                        method[m] = i + 1;
                        m++;
                    }
                    if (flag[0]) {
                        break;
                    }
                }
                if (flag[0]) {
                    break;
                }
            }
            x++;
        }

        List<Integer> mResults = new ArrayList<>();
        int p = m - 1;
        while (p != 0) {
            mResults.add(method[p]);
            p = parent[p];
        }

        for (int i = mResults.size() - 1; i >= 0; i--) {
            if (i < mResults.size() - 1) {
                out.print(" ");
            }
            out.print(String.valueOf(mResults.get(i)));
        }
        out.println();
        out.close();
    }

    private static int convert(int[] x) {
        int s = 0, r = 1;
        for (int i = 0; i < 9; i++) {
            s += x[i] * r;
            r = r * 4;
        }
        return s;
    }

}
