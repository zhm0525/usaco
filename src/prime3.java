/*
ID: zhm05251
LANG: JAVA
TASK: prime3
*/

import java.io.*;
import java.util.*;

public class prime3 {

    private static final String INPUT_FILE_NAME = "prime3.in";
    private static final String OUTPUT_FILE_NAME = "prime3.out";

//    private static List<Integer> mPrimeList = new ArrayList<>();
//    private static List<Boolean> mPrimeNon0List = new ArrayList<>();

    private static int[][] mPrimeList = new int[10000][5];
    private static boolean[] mPrimeNon0List = new boolean[10000];
    private static int mPrimeN = 0;

    private static boolean[][][] mPrimeMapG = new boolean[10][10][10];
    private static boolean[][][][][] mPrimeMap = new boolean[10][10][10][10][10];

    private static int[][] a = new int[5][5];
    private static int s, x;

    private static List<String> mResults;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        countPrimes();
        mResults = new ArrayList<>();
        search();
        Collections.sort(mResults);

        for (int i = 0, size = mResults.size(); i < size; i++) {
            if (i > 0) {
                out.println();
            }
            String result = mResults.get(i);
            for (int j = 0; j < 5; j++) {
                out.println(result.substring(j * 5, (j + 1) * 5));
            }
        }
        out.close();
    }

    private static void countPrimes() {
        final int start = 10000;
        final int end = 100000;
        boolean[] b = new boolean[end];
        for (int i = 2; i < end; i++) {
            if (!b[i]) {
                int k = i;
                while (k + i < end) {
                    k += i;
                    b[k] = true;
                }
            }
        }

        for (int i = start; i < end; i++) {
            if (!b[i]) {
                int[] p = new int[5];
                int t = i, r = 0;
                boolean non0Flag = true;
                for (int j = 0; j < 5; j++) {
                    int m = t % 10;
                    p[4 - j] = m;
                    r += m;
                    non0Flag = non0Flag || (m == 0);
                    t = t / 10;
                }
                if (r == s) {
                    if (p[0] == x) {
                        mPrimeList[mPrimeN] = p;
                        mPrimeNon0List[mPrimeN] = non0Flag;
                        mPrimeN++;
                    }
                    mPrimeMapG[p[0]][p[2]][p[4]] = true;
                    mPrimeMap[p[0]][p[1]][p[2]][p[3]][p[4]] = true;
                }
            }
        }
    }

    private static void search() {
        for (int i = 0; i < mPrimeN; i++) {
            if (!mPrimeNon0List[i]) {
                continue;
            }
            for (int l = 0; l < 5; l++) {
                a[0][l] = mPrimeList[i][l];
            }

            for (int j = 0; j < mPrimeN; j++) {
                if (!mPrimeNon0List[j]) {
                    continue;
                }
                for (int l = 0; l < 5; l++) {
                    a[l][0] = mPrimeList[j][l];
                }

                for (int k = 0; k < mPrimeN; k++) {
                    for (int l = 0; l < 5; l++) {
                        a[l][l] = mPrimeList[k][l];
                    }
                    if (!mPrimeMapG[a[4][0]][a[2][2]][a[0][4]]) {
                        continue;
                    }

                    // d1 for (1,3), d2 for (3,1)
                    for (int d1 = 0; d1 < 10; d1++) {
                        int d2 = s - a[4][0] - a[2][2] - a[0][4] - d1;
                        if (!checkDigit(d2)) {
                            continue;
                        }
                        if (!mPrimeMap[a[4][0]][d2][a[2][2]][d1][a[0][4]]) {
                            continue;
                        }
                        a[1][3] = d1;
                        a[3][1] = d2;

                        // e1 for (1,2), e3 for (1,4)
                        // e2 for (2,1), e4 for (4,1)
                        for (int e1 = 0; e1 < 10; e1++) {
                            int e3 = s - a[1][0] - a[1][1] - a[1][3] - e1;
                            if (!checkDigit(e3)) {
                                continue;
                            }
                            if (!mPrimeMap[a[1][0]][a[1][1]][e1][a[1][3]][e3]) {
                                continue;
                            }
                            a[1][2] = e1;
                            a[1][4] = e3;
                            for (int e2 = 0; e2 < 10; e2++) {
                                int e4 = s - a[0][1] - a[1][1] - a[3][1] - e2;
                                if (!checkDigit(e4)) {
                                    continue;
                                }
                                if (!mPrimeMap[a[0][1]][a[1][1]][e2][a[3][1]][e4]) {
                                    continue;
                                }
                                a[2][1] = e2;
                                a[4][1] = e4;

                                for (int g23 = 0; g23 < 10; g23++) {
                                    int g24 = s - a[2][0] - a[2][1] - a[2][2] - g23;
                                    int g34 = s - a[0][4] - a[1][4] - g24 - a[4][4];
                                    int g32 = s - a[3][0] - a[3][1] - a[3][3] - g34;
                                    int g42 = s - a[0][2] - a[1][2] - a[2][2] - g32;
                                    int g43 = s - a[4][0] - a[4][1] - g42 - a[4][4];

                                    if (!checkDigit(g24) ||
                                            !checkDigit(g34) ||
                                            !checkDigit(g32) ||
                                            !checkDigit(g42) ||
                                            !checkDigit(g43)) {
                                        continue;
                                    }

                                    if (a[0][3] + a[1][3] + g23 + a[3][3] + g43 != s) {
                                        continue;
                                    }

                                    a[2][3] = g23;
                                    a[2][4] = g24;
                                    a[3][4] = g34;
                                    a[3][2] = g32;
                                    a[4][2] = g42;
                                    a[4][3] = g43;

                                    boolean flag = true;
                                    for (int h = 2; h <= 4; h++) {
                                        if (!mPrimeMap[a[h][0]][a[h][1]][a[h][2]][a[h][3]][a[h][4]] ||
                                                !mPrimeMap[a[0][h]][a[1][h]][a[2][h]][a[3][h]][a[4][h]]) {
                                            flag = false;
                                            break;
                                        }
                                    }

                                    if (flag) {
                                        // find an answer, need sort
                                        StringBuffer stringBuffer = new StringBuffer();
                                        for (int u = 0; u < 5; u++) {
                                            for (int v = 0; v < 5; v++) {
                                                stringBuffer.append(String.valueOf(a[u][v]));
                                            }
                                        }
                                        mResults.add(stringBuffer.toString());
                                    }
                                }


                            }

                        }
                    }
                }
            }
        }
    }

    private static boolean checkDigit(int x) {
        return 0 <= x && x <= 9;
    }

}
