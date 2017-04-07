/*
ID: zhm05251
LANG: JAVA
TASK: test
*/

import java.io.*;
import java.util.*;

class test {
    private static final String INPUT_FILE_NAME = "test.in";
    private static final String OUTPUT_FILE_NAME = "test.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int result = a + b;
        out.println(result);
        out.close();
    }
}