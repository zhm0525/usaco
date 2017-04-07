/*
ID: zhm05251
LANG: JAVA
TASK: ride
*/

import java.io.*;
import java.util.*;

class ride {
    private static final String INPUT_FILE_NAME = "ride.in";
    private static final String OUTPUT_FILE_NAME = "ride.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        String group1 = st.nextToken();

        st = new StringTokenizer(f.readLine());
        String group2 = st.nextToken();

        int product1 = calculate(group1);
        int product2 = calculate(group2);

        String result = "";
        if (product1 == product2) {
            result = "GO";
        } else {
            result = "STAY";
        }

        out.println(result);
        out.close();
    }

    private static int calculate(String group) {
        int result = 1;
        for (int i = 0, length = group.length(); i < length; i++) {
            result = result * (group.charAt(i) - 'A' + 1) % 47;
        }
        return result;
    }
}