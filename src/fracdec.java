/*
ID: zhm05251
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.*;

public class fracdec {
    private static final String INPUT_FILE_NAME = "fracdec.in";
    private static final String OUTPUT_FILE_NAME = "fracdec.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int s = m / n;
        out.print(String.valueOf(s) + ".");
        int lineCount = 0;
        if (s == 0) {
            lineCount = 2;
        } else {
            while (s > 0) {
                lineCount++;
                s = s / 10;
            }
            lineCount++;
        }

        m = m % n;
        boolean[] r = new boolean[n];
        int[] map = new int[n];
        List<Integer> list = new ArrayList<>();
        while (!r[m]) {
            r[m] = true;
            map[m] = list.size();
            list.add(m * 10 / n);
            m = m * 10 % n;
        }
        if (r[0]) {
            int t = map[m];
            if (t == 0) {
                out.println("0");
            } else {
                for (int i = 0; i < t; i++) {
                    out.print(String.valueOf(list.get(i)));
                    lineCount++;
                    if (lineCount >= 76) {
                        out.println();
                        lineCount = 0;
                    }
                }
                if (lineCount != 0) {
                    out.println();
                }
            }
        } else {
            int t = map[m];
            for (int i = 0; i < t; i++) {
                out.print(String.valueOf(list.get(i)));
                lineCount++;
                if (lineCount >= 76) {
                    out.println();
                    lineCount = 0;
                }
            }
            out.print("(");
            lineCount++;
            if (lineCount >= 76) {
                out.println();
                lineCount = 0;
            }
            for (int i = t, size = list.size(); i < size; i++) {
                out.print(String.valueOf(list.get(i)));
                lineCount++;
                if (lineCount >= 76) {
                    out.println();
                    lineCount = 0;
                }
            }
            out.println(")");
        }
        out.close();
    }

}
