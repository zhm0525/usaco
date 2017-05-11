/*
ID: zhm05251
LANG: JAVA
TASK: vans
*/

import java.io.*;
import java.util.*;

public class vans {

    private static final String INPUT_FILE_NAME = "vans.in";
    private static final String OUTPUT_FILE_NAME = "vans.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int s = 0;
        for (int i = 1; i < n; i++) {
            s += leftTop2leftBottom4(i);
        }

        out.println(String.valueOf(s * 2));
        out.close();
    }

    private static int leftTop2leftBottom4(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        int s = leftTop2rightBottom3(n);
        for (int i = 1; i < n - 1; i++) {
            s += leftTop2leftBottom4(i) * (n - 1 - i);
        }

        // from 2
        s += leftTop2leftBottom4(n - 2);

        return s;
    }

    private static int leftTop2rightBottom3(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        int s = 1;
        for (int i = 1; i < n - 1; i++) {
            s += leftTop2rightBottom3(i) * (n - 1 - i);
        }

        return s;
    }

}