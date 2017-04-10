/*
ID: zhm05251
LANG: JAVA
TASK: runround
*/

import java.io.*;
import java.util.*;

public class runround {
    private static final String INPUT_FILE_NAME = "runround.in";
    private static final String OUTPUT_FILE_NAME = "runround.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());

        int x = m + 1;

        while (!isRunRound(x)) {
            x++;
        }
        out.println(x);
        out.close();
    }

    private static boolean isRunRound(int x) {
        int l = 0, t = x;
        while (t > 0) {
            l++;
            t = t / 10;
        }
        int[] a = new int[l];
        t = x;
        for (int i = 0; i < l; i++) {
            a[l - 1 - i] = t % 10;
            t = t / 10;
        }

        for (int i = 0; i < l; i++) {
            if (a[i] == 0) {
                return false;
            }
        }

        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (a[i] == a[j]) {
                    return false;
                }
            }
        }

        boolean flag = false;
        t = 0;
        for (int i = 0; i < l; i++) {
            t = (t + a[t]) % l;
            if (t == 0 && i != l - 1) {
                return false;
            }
        }
        return t == 0;
    }
}
