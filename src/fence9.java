/*
ID: zhm05251
LANG: JAVA
TASK: fence9
*/

import java.io.*;
import java.util.*;

public class fence9 {

    private static final String INPUT_FILE_NAME = "fence9.in";
    private static final String OUTPUT_FILE_NAME = "fence9.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());
        int s = 0;

        for (int i = 1; i < y; i++) {
            int x1 = i * x / y + 1;
            int x2 = (i * x - i * z + y * z) / y;
            if ((i * x - i * z + x * y) % y == 0) {
                x2--;
            }
            if (x2 >= x1) {
                s = s + x2 - x1 + 1;
            }
        }

        out.println(s);
        out.close();
    }
}
