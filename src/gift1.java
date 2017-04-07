/*
ID: zhm05251
LANG: JAVA
TASK: gift1
*/

import java.io.*;
import java.util.*;

public class gift1 {
    private static final String INPUT_FILE_NAME = "gift1.in";
    private static final String OUTPUT_FILE_NAME = "gift1.out";

    private static String[] names;
    private static int[] gifts;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        // read the number of persons
        StringTokenizer st = new StringTokenizer(f.readLine());
        int number = Integer.parseInt(st.nextToken());

        names = new String[number];
        gifts = new int[number];

        // read the name of persons
        for (int i = 0; i < number; i++) {
            st = new StringTokenizer(f.readLine());
            names[i] = st.nextToken();
            gifts[i] = 0;
        }

        // calculate the gifts
        for (int i = 0; i < number; i++) {
            st = new StringTokenizer(f.readLine());
            String owner = st.nextToken();

            st = new StringTokenizer(f.readLine());
            int total = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            if (count == 0) {
                continue;
            }

            for (int j = 0; j < count; j++) {
                st = new StringTokenizer(f.readLine());
                String receiver = st.nextToken();

                for (int k = 0; k < number; k++) {
                    if (receiver.equals(names[k])) {
                        gifts[k] += total / count;
                        break;
                    }
                }
            }

            for (int k = 0; k < number; k++) {
                if (owner.equals(names[k])) {
                    gifts[k] += total % count - total;
                    break;
                }
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < number; i++) {
            if (i > 0) {
                stringBuffer.append("\n");
            }
            stringBuffer.append(names[i] + " " + String.valueOf(gifts[i]));
        }

        out.println(stringBuffer.toString());
        out.close();
    }
}
