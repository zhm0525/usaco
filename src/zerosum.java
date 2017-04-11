/*
ID: zhm05251
LANG: JAVA
TASK: zerosum
*/

import java.io.*;
import java.util.*;

public class zerosum {
    private static final String INPUT_FILE_NAME = "zerosum.in";
    private static final String OUTPUT_FILE_NAME = "zerosum.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] op = new int[n - 1];
        int index = 0;
        while (index >= 0) {
            if (index == n - 1) {
                int s = 0, r = 1, t = 1;
                for (int i = 0; i < n - 1; i++) {
                    if (op[i] == 1) {
                        t = t * 10 + (i + 2);
                    } else if (op[i] == 2) {
                        s = s + r * t;
                        r = 1;
                        t = i + 2;
                    } else if (op[i] == 3) {
                        s = s + r * t;
                        r = -1;
                        t = i + 2;
                    }
                }
                s = s + r * t;
                if (s == 0) {
                    for (int i = 0; i < n - 1; i++) {
                        out.print(i + 1);
                        if (op[i] == 1) {
                            out.print(" ");
                        } else if (op[i] == 2) {
                            out.print("+");
                        } else if (op[i] == 3) {
                            out.print("-");
                        }

                    }
                    out.println(n);
                }
                index--;
            } else {
                if (op[index] < 3) {
                    op[index]++;
                    index++;
                } else {
                    op[index] = 0;
                    index--;
                }
            }
        }
        out.close();
    }
}
