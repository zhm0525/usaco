/*
ID: zhm05251
LANG: JAVA
TASK: skidesign
*/

import java.io.*;
import java.util.*;

public class skidesign {
    private static final String INPUT_FILE_NAME = "skidesign.in";
    private static final String OUTPUT_FILE_NAME = "skidesign.out";

    private static final int MIN_HILL_HEIGHT = 0;
    private static final int MAX_HILL_HEIGHT = 100;
    private static final int MAX_HEIGHT_DIFFERENCE = 17;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Integer> mHillHeights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int height = Integer.parseInt(st.nextToken());
            mHillHeights.add(height);
        }

        Collections.sort(mHillHeights);

        int result = Integer.MAX_VALUE;
        for (int i = MIN_HILL_HEIGHT; i <= MAX_HILL_HEIGHT - MAX_HEIGHT_DIFFERENCE; i++) {
            int count = 0;
            int minHeight = i;
            int maxHeight = minHeight + MAX_HEIGHT_DIFFERENCE;
            for (int j = 0; j < n; j++) {
                int height = mHillHeights.get(j);
                if (height < minHeight) {
                    count = count + (minHeight - height) * (minHeight - height);
                } else if (height > maxHeight) {
                    count = count + (height - maxHeight) * (height - maxHeight);
                }
            }
            if (count < result) {
                result = count;
            }
        }
        out.println(result);
        out.close();
    }
}
