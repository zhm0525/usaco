/*
ID: zhm05251
LANG: JAVA
TASK: crypt1
*/

import java.io.*;
import java.util.*;

public class crypt1 {
    private static final String INPUT_FILE_NAME = "crypt1.in";
    private static final String OUTPUT_FILE_NAME = "crypt1.out";

    private static boolean[] DIGITALS = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++) {
            int digital = Integer.parseInt(st.nextToken());
            DIGITALS[digital] = true;
        }

        int result = 0;
        for (int i1 = 0; i1 < 10; i1++) {
            if (!DIGITALS[i1]) {
                continue;
            }
            for (int i2 = 0; i2 < 10; i2++) {
                if (!DIGITALS[i2]) {
                    continue;
                }
                for (int i3 = 0; i3 < 10; i3++) {
                    if (!DIGITALS[i3]) {
                        continue;
                    }
                    for (int j1 = 0; j1 < 10; j1++) {
                        if (!DIGITALS[j1]) {
                            continue;
                        }
                        for (int j2 = 0; j2 < 10; j2++) {
                            if (!DIGITALS[j2]) {
                                continue;
                            }
                            int s1 = (i1 * 100 + i2 * 10 + i3) * j1;
                            int s2 = (i1 * 100 + i2 * 10 + i3) * j2;
                            int s = (i1 * 100 + i2 * 10 + i3) * (j1 * 10 + j2);

                            if (s1 < 100 || s1 > 999) {
                                continue;
                            }
                            if (s2 < 100 || s2 > 999) {
                                continue;
                            }
                            if (s < 1000 || s > 9999) {
                                continue;
                            }

                            if (!DIGITALS[s1 % 10] || !DIGITALS[s1 / 10 % 10] || !DIGITALS[s1 / 100 % 10]) {
                                continue;
                            }

                            if (!DIGITALS[s2 % 10] || !DIGITALS[s2 / 10 % 10] || !DIGITALS[s2 / 100 % 10]) {
                                continue;
                            }

                            if (!DIGITALS[s % 10] || !DIGITALS[s / 10 % 10] ||
                                    !DIGITALS[s / 100 % 10] || !DIGITALS[s / 1000 % 10]) {
                                continue;
                            }
                            result++;
                        }
                    }
                }
            }
        }
        out.println(result);
        out.close();
    }
}
