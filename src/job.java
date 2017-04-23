/*
ID: zhm05251
LANG: JAVA
TASK: job
*/

import java.io.*;
import java.util.*;

public class job {

    private static final String INPUT_FILE_NAME = "job.in";
    private static final String OUTPUT_FILE_NAME = "job.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());

        int[] t1 = new int[m1];
        int[] t2 = new int[m2];
        for (int i = 0; i < m1; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            t1[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < m2; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            t2[i] = Integer.parseInt(st.nextToken());
        }

        int[] a = new int[n];
        int[] x1 = new int[m1];
        for (int i = 0; i < n; i++) {
            int r = 0;
            for (int j = 1; j < m1; j++) {
                if (x1[j] + t1[j] < x1[r] + t1[r]) {
                    r = j;
                }
            }
            x1[r] = x1[r] + t1[r];
            a[i] = x1[r];
        }

        int s1 = 0;
        for (int i = 0; i < n; i++) {
            s1 = a[i] > s1 ? a[i] : s1;
        }

        int[] b = new int[n];
        int[] x2 = new int[m2];
        for (int i = 0; i < n; i++) {
            int r = 0;
            for (int j = 1; j < m2; j++) {
                if (x2[j] + t2[j] < x2[r] + t2[r]) {
                    r = j;
                }
            }
            x2[r] = x2[r] + t2[r];
            b[i] = x2[r];
        }

        int s2 = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] + b[n - 1 - i] > s2) {
                s2 = a[i] + b[n - 1 - i];
            }
        }

        out.println(String.valueOf(s1) + " " + String.valueOf(s2));
        out.close();
    }

}
