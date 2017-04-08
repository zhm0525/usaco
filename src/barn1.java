/*
ID: zhm05251
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.*;

public class barn1 {
    private static final String INPUT_FILE_NAME = "barn1.in";
    private static final String OUTPUT_FILE_NAME = "barn1.out";

    private static boolean[] mBarnHasCow;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        mBarnHasCow = new boolean[s];
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(f.readLine());
            int stall = Integer.parseInt(st.nextToken());
            mBarnHasCow[stall - 1] = true;
        }

        int start = 0;
        int end = s;

        while (!mBarnHasCow[start]) {
            start++;
        }

        while (!mBarnHasCow[end - 1]) {
            end--;
        }

        int result = end - start;
        for (int i = 0; i < m - 1; i++) {
            int k = start;
            while (k < end && mBarnHasCow[k]) {
                k++;
            }
            if (k >= end) {
                continue;
            }

            int longestStart = 0;
            int longestEnd = 0;

            for (int j = k + 1; j < end; j++) {
                if (mBarnHasCow[j] && !mBarnHasCow[j - 1]) {
                    if (j - k > longestEnd - longestStart) {
                        longestStart = k;
                        longestEnd = j;
                    }
                } else if (mBarnHasCow[j - 1]) {
                    k = j;
                }
            }

            for (int j = longestStart; j < longestEnd; j++) {
                mBarnHasCow[j] = true;
            }
            result = result - (longestEnd - longestStart);
        }

        out.println(result);
        out.close();
    }

}
