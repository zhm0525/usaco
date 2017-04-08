/*
ID: zhm05251
LANG: JAVA
TASK: beads
*/

import java.io.*;
import java.util.*;

public class beads {
    private static final String INPUT_FILE_NAME = "beads.in";
    private static final String OUTPUT_FILE_NAME = "beads.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        String beads = st.nextToken();

        int result = 1;
        for (int i = 0, length = beads.length(); i < length; i++) {
            // TODO
        }

        out.println(String.valueOf(result));
        out.close();
    }
}
