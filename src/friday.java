/*
ID: zhm05251
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.*;

public class friday {
    private static final String INPUT_FILE_NAME = "friday.in";
    private static final String OUTPUT_FILE_NAME = "friday.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        Calendar calendar = Calendar.getInstance();
        int[] days = new int[7];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 12; j++) {
                calendar.set(1900 + i, j, 13);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                days[dayOfWeek - 1]++;
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(days[6]));
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(" " + days[i]);
        }

        out.println(stringBuffer.toString());
        out.close();
    }

}