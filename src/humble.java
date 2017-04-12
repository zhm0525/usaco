/*
ID: zhm05251
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.*;

public class humble {
    private static final String INPUT_FILE_NAME = "humble.in";
    private static final String OUTPUT_FILE_NAME = "humble.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[k];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < k; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] x = new int[k];
        List<Integer> list = new ArrayList<>();
        list.add(1);

        for (int i = 0; i < n; i++) {
            int t = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (list.get(x[j]) * a[j] < t) {
                    t = list.get(x[j]) * a[j];
                }
            }
            for (int j = 0; j < k; j++) {
                if (list.get(x[j]) * a[j] == t) {
                    x[j]++;
                }
            }
            list.add(t);
        }
        out.println(list.get(n));
        out.close();
    }
}
