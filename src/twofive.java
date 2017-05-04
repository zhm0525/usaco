/*
ID: zhm05251
LANG: JAVA
TASK: twofive
*/

import java.io.*;
import java.util.*;

public class twofive {

    private static final String INPUT_FILE_NAME = "twofive.in";
    private static final String OUTPUT_FILE_NAME = "twofive.out";

    private static int[][][][][] g;
    private static int[][] a;
    private static boolean[] b;
    private static int[] s;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        g = new int[6][6][6][6][6];
        a = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                a[i][j] = -1;
            }
        }
        search(0);
        g[0][0][0][0][0] = 0;

        StringTokenizer st = new StringTokenizer(f.readLine());
        String op = st.nextToken();
        if ("N".equals(op)) {
            st = new StringTokenizer(f.readLine());
            int n = Integer.parseInt(st.nextToken());

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    a[i][j] = -1;
                }
            }
            b = new boolean[25];
            s = new int[25];
            int max = -1;
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < 25; j++) {
                    if (b[j]) {
                        continue;
                    }
                    if (i / 5 > 0 && a[i / 5 - 1][i % 5] > j) {
                        continue;
                    }
                    if (i % 5 > 0 && a[i / 5][i % 5 - 1] > j) {
                        continue;
                    }

                    a[i / 5][i % 5] = j;
                    b[j] = true;
                    int t = max;
                    if (j > t) {
                        t = j;
                    }
                    int count = count(0, t);
                    if (count >= n) {
                        s[i] = j;
                        if (j > max) {
                            max = j;
                        }
                        break;
                    }
                    n = n - count;
                    b[j] = false;
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 25; i++) {
                stringBuffer.append((char) ('A' + s[i]));
            }
            out.println(stringBuffer.toString());
        } else if ("W".equals(op)) {
            String input = f.readLine();
            // assert the length of input is 25

            b = new boolean[25];
            s = new int[25];
            for (int i = 0; i < 25; i++) {
                s[i] = input.charAt(i) - 'A';
            }
            int n = 0;
            int max = -1;
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < s[i]; j++) {
                    if (b[j]) {
                        continue;
                    }
                    if (i / 5 > 0 && a[i / 5 - 1][i % 5] > j) {
                        continue;
                    }
                    if (i % 5 > 0 && a[i / 5][i % 5 - 1] > j) {
                        continue;
                    }

                    a[i / 5][i % 5] = j;
                    b[j] = true;
                    int t = max;
                    if (j > t) {
                        t = j;
                    }
                    n = n + count(0, t);
                    b[j] = false;
                }
                a[i / 5][i % 5] = s[i];
                b[s[i]] = true;
                if (s[i] > max) {
                    max = s[i];
                }
            }
            out.println(String.valueOf(n + 1));
        }
        out.close();
    }

    private static int search(int index) {
        if (index >= 25) {
            return 1;
        }

        int[] r = new int[5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (a[i][j] < 0) {
                    break;
                }
                r[i]++;
            }
        }

        if (g[r[0]][r[1]][r[2]][r[3]][r[4]] > 0) {
            return g[r[0]][r[1]][r[2]][r[3]][r[4]];
        }

        int s = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i > 0 && a[i - 1][j] < 0) {
                    break;
                }
                if (j > 0 && a[i][j - 1] < 0) {
                    break;
                }
                if (a[i][j] >= 0) {
                    continue;
                }
                r[i]++;
                if (g[r[0]][r[1]][r[2]][r[3]][r[4]] == 0) {
                    a[i][j] = index;
                    g[r[0]][r[1]][r[2]][r[3]][r[4]] = search(index + 1);
                    a[i][j] = -1;
                }
                s += g[r[0]][r[1]][r[2]][r[3]][r[4]];
                r[i]--;
            }
        }
        return s;
    }

    private static int count(int index, int max) {
        int[] r = new int[5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (a[i][j] < 0) {
                    break;
                }
                r[i]++;
            }
        }

        if (index >= max) {
            return g[r[0]][r[1]][r[2]][r[3]][r[4]];
        }
        if (b[index]) {
            return count(index + 1, max);
        }

        int s = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i > 0 && a[i - 1][j] < 0) {
                    break;
                }
                if (j > 0 && a[i][j - 1] < 0) {
                    break;
                }
                if (a[i][j] >= 0) {
                    continue;
                }
                if (i > 0 && a[i - 1][j] > index) {
                    break;
                }
                if (j > 0 && a[i][j - 1] > index) {
                    break;
                }
                a[i][j] = index;
                b[index] = true;
                s = s + count(index + 1, max);
                b[index] = false;
                a[i][j] = -1;
            }
        }
        return s;
    }
}
