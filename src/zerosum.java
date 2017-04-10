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



        out.println();
        out.close();
    }
}
