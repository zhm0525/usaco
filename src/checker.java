/*
ID: zhm05251
LANG: JAVA
TASK: checker
*/

import java.io.*;
import java.util.*;

public class checker {

    private static final String INPUT_FILE_NAME = "checker.in";
    private static final String OUTPUT_FILE_NAME = "checker.out";

    private static int n, result = 0;
    private static int[] a, s;
    private static boolean[] x, y, z;

    private static String[] result3 = new String[3];

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());

        a = new int[n];
        s = new int[n];
        x = new boolean[n];
        y = new boolean[2 * n - 1];
        z = new boolean[2 * n - 1];

        search(0);

        for (int i = 0; i < 3; i++) {
            out.println(result3[i]);
        }

        result = 0;
        for (int i = 0; i < n; i++) {
            result += s[i];
        }
        out.println(String.valueOf(result));
        out.close();
    }

    private static void search(int r) {
        if (r >= n) {
            if (result < 3) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < n; i++) {
                    if (i == 0) {
                        stringBuffer.append("" + a[i]);
                    } else {
                        stringBuffer.append(" " + a[i]);
                    }
                }
                result3[result] = stringBuffer.toString();
            }
            result++;
            s[a[0] - 1]++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (r == 0 && result >= 3 && s[n - 1 - i] > 0) {
                s[i] = s[n - 1 - i];
                continue;
            }
            if (!x[i] && !y[r + i] && !z[r - i + (n - 1)]) {
                x[i] = true;
                y[r + i] = true;
                z[r - i + (n - 1)] = true;
                a[r] = i + 1;
                search(r + 1);
                x[i] = false;
                y[r + i] = false;
                z[r - i + (n - 1)] = false;
            }
        }
    }
}
