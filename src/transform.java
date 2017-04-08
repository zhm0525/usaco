/*
ID: zhm05251
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.*;

public class transform {
    private static final String INPUT_FILE_NAME = "transform.in";
    private static final String OUTPUT_FILE_NAME = "transform.out";

    private static char[][] mBeforePattern;
    private static char[][] mAfterPattern;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        mBeforePattern = new char[n][n];
        mAfterPattern = new char[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            String input = st.nextToken();

            for (int j = 0, length = input.length(); j < length; j++) {
                mBeforePattern[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            String input = st.nextToken();

            for (int j = 0, length = input.length(); j < length; j++) {
                mAfterPattern[i][j] = input.charAt(j);
            }
        }

        char[][] transform90 = clockwise90(n, mBeforePattern);
        char[][] transform180 = clockwise90(n, transform90);
        char[][] transform270 = clockwise90(n, transform180);

        char[][] reflection = reflection(n, mBeforePattern);
        char[][] reflection90 = clockwise90(n, reflection);
        char[][] reflection180 = clockwise90(n, reflection90);
        char[][] reflection270 = clockwise90(n, reflection180);

        if (isSamePattern(n, mAfterPattern, transform90)) {
            out.println("1");
        } else if (isSamePattern(n, mAfterPattern, transform180)) {
            out.println("2");
        } else if (isSamePattern(n, mAfterPattern, transform270)) {
            out.println("3");
        } else if (isSamePattern(n, mAfterPattern, reflection)) {
            out.println("4");
        } else if (isSamePattern(n, mAfterPattern, reflection90) ||
                isSamePattern(n, mAfterPattern, reflection180) ||
                isSamePattern(n, mAfterPattern, reflection270)) {
            out.println("5");
        } else if (isSamePattern(n, mAfterPattern, mBeforePattern)) {
            out.println("6");
        } else {
            out.println("7");
        }
        out.close();
    }

    private static char[][] clockwise90(int n, char[][] pattern) {
        char[][] transformPattern = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transformPattern[i][j] = pattern[n - 1 - j][i];
            }
        }
        return transformPattern;
    }

    private static char[][] reflection(int n, char[][] pattern) {
        char[][] transformPattern = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transformPattern[i][j] = pattern[i][n - 1 - j];
            }
        }
        return transformPattern;
    }

    private static boolean isSamePattern(int n, char[][] pattern1, char[][] pattern2) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (pattern1[i][j] != pattern2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
