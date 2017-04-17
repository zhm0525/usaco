/*
ID: zhm05251
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.*;

public class fence {

    private static final String INPUT_FILE_NAME = "fence.in";
    private static final String OUTPUT_FILE_NAME = "fence.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[][] a = new int[501][501];
        int[] d = new int[501];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            a[p][q]++;
            a[q][p]++;
            d[p]++;
            d[q]++;
        }
        int start = 0;
        for (int i = 1; i < 501; i++) {
            if (start == 0 && d[i] > 0) {
                start = i;
            }
            if (d[i] % 2 == 1) {
                start = i;
                break;
            }
        }

        int[] s = new int[501];
        s[0] = start;
        int index = 1;

        while (0 < index && index <= m) {
            if (s[index] > 0) {
                a[s[index - 1]][s[index]]++;
                a[s[index]][s[index - 1]]++;
            }
            s[index]++;

            while ((s[index] < 501) && (a[s[index - 1]][s[index]] <= 0)) {
                s[index]++;
            }
            if (s[index] >= 501) {
                s[index] = 0;
                index--;
            } else {
                a[s[index - 1]][s[index]]--;
                a[s[index]][s[index - 1]]--;
                index++;
            }

        }

//        for (int i = 0; i <= m; i++) {
//            for (int j = 1; j < 501; j++) {
//                if (a[index][j] > 0) {
//                    a[index][j]--;
//                    a[j][index]--;
//                    index = j;
//                    break;
//                }
//            }
//        }
        for (int i = 0; i <= m; i++) {
            out.println(String.valueOf(s[i]));
        }
        out.close();
    }
}
