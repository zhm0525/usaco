/*
ID: zhm05251
LANG: JAVA
TASK: fact4
*/

import java.io.*;
import java.util.*;

public class fact4 {
    private static final String INPUT_FILE_NAME = "fact4.in";
    private static final String OUTPUT_FILE_NAME = "fact4.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = i;
        }
        int x = 2, y = 5;
        while (x <= n && y <= n) {
            boolean flag = false;
            while (y <= n && a[y] % 5 != 0) {
                y = y + 5;
            }
            if (y <= n) {
                a[y] = a[y] / 5;
                flag = true;
            }

            while (x <= n && a[x] % 2 != 0) {
                x = x + 2;
            }
            if (x <= n && flag) {
                a[x] = a[x] / 2;
            }
        }
        int s = 1;
        for (int i = 1; i <= n; i++) {
            s = s * a[i] % 10;
        }
        out.println(s);
        out.close();
    }
}
