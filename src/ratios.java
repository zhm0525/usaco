/*
ID: zhm05251
LANG: JAVA
TASK: ratios
*/

import java.io.*;
import java.util.*;

public class ratios {
    private static final String INPUT_FILE_NAME = "ratios.in";
    private static final String OUTPUT_FILE_NAME = "ratios.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        int[] g = new int[3];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < 3; i++) {
            g[i] = Integer.parseInt(st.nextToken());
        }

        int[] a = new int[3];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < 3; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] b = new int[3];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < 3; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int[] c = new int[3];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < 3; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        int d = a[0] * b[1] * c[2] + b[0] * c[1] * a[2] + c[0] * a[1] * b[2]
                - a[0] * c[1] * b[2] - b[0] * a[1] * c[2] - c[0] * b[1] * a[2];
        int d1 = g[0] * b[1] * c[2] + b[0] * c[1] * g[2] + c[0] * g[1] * b[2]
                - g[0] * c[1] * b[2] - b[0] * g[1] * c[2] - c[0] * b[1] * g[2];
        int d2 = a[0] * g[1] * c[2] + g[0] * c[1] * a[2] + c[0] * a[1] * g[2]
                - a[0] * c[1] * g[2] - g[0] * a[1] * c[2] - c[0] * g[1] * a[2];
        int d3 = a[0] * b[1] * g[2] + b[0] * g[1] * a[2] + g[0] * a[1] * b[2]
                - a[0] * g[1] * b[2] - b[0] * a[1] * g[2] - g[0] * b[1] * a[2];

        if (d < 0) {
            d = -d;
            d1 = -d1;
            d2 = -d2;
            d3 = -d3;
        }

        if (d == 0) {
            out.println("NONE");
        } else if (d1 < 0 || d2 < 0 || d3 < 0) {
            out.println("NONE");
        } else {
            int t = gcd(gcd(gcd(d1, d2), d3), d);
            out.println(d1 / t + " " + d2 / t + " " + d3 / t + " " + d / t);
        }
        out.close();
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

}