/*
ID: zhm05251
LANG: JAVA
TASK: milk4
*/

import java.io.*;
import java.util.*;

public class milk4 {

    private static final String INPUT_FILE_NAME = "milk4.in";
    private static final String OUTPUT_FILE_NAME = "milk4.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);

        int[] s = new int[m + 1];
        int[] p = new int[m + 1];
        int[] q = new int[m + 1];
        for (int i = 0; i < a.length; i++) {
            int t = a[i];
            for (int j = 0; j <= t - 1; j++) {
                int from = j == 0 ? 0 : -1;
                int k = 0;
                while (j + k * t <= m) {
                    int r = j + k * t;

                    if (s[r] > 0) {
                        if (from < 0 || s[r] < s[from]) {
                            from = r;
                        }
                    }

                    if (from >= 0 && r > 0) {
                        if (s[r] == 0 || s[from] + 1 < s[r] ||
                                (s[from] + 1 == s[r] && q[from] < q[p[r]])) {
                            s[r] = s[from] + 1;
                            p[r] = from;
                            q[r] = t;
                        }
                    }
                    k++;
                }
            }
        }

        Set<Integer> set = new TreeSet<>();
        int t = m;
        while (p[t] != t) {
            set.add(q[t]);
            t = p[t];
        }

        out.print(String.valueOf(set.size()));
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            out.print(" " + String.valueOf(it.next()));
        }
        out.println();
        out.close();
    }
}
