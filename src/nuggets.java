/*
ID: zhm05251
LANG: JAVA
TASK: nuggets
*/

import java.io.*;
import java.util.*;

public class nuggets {

    private static final String INPUT_FILE_NAME = "nuggets.in";
    private static final String OUTPUT_FILE_NAME = "nuggets.out";

    private static final int MAX = 256 * 256 + 256;
    private static final int ANSWER_LIMIT = 256 * 256;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] s = new boolean[MAX + 1];
        s[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= MAX; j++) {
                if (s[j] && j + a[i] <= MAX) {
                    s[j + a[i]] = true;
                }
            }
        }

        int result = 0;
        for (int i = MAX; i >= 1; i--) {
            if (!s[i]) {
                result = i;
                break;
            }
        }

        if (result > ANSWER_LIMIT) {
            out.println("0");
        } else {
            out.println(String.valueOf(result));
        }
        out.close();
    }
}
