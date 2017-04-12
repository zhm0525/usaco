/*
ID: zhm05251
LANG: JAVA
TASK: inflate
*/

import java.io.*;
import java.util.*;

public class inflate {

    private static final String INPUT_FILE_NAME = "inflate.in";
    private static final String OUTPUT_FILE_NAME = "inflate.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] s = new int[m + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int point = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            for (int j = cost; j <= m; j++)
                s[j] = Math.max(s[j], s[j - cost] + point);
        }
        out.println(s[m]);
        out.close();
    }
}
