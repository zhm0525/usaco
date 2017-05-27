/*
ID: zhm05251
LANG: JAVA
TASK: latin
*/

import java.io.*;
import java.util.*;

public class latin {

    private static final String INPUT_FILE_NAME = "latin.in";
    private static final String OUTPUT_FILE_NAME = "latin.out";

    private static boolean[][] row, column;
    private static int n, r;
    private static long s = 0;

    private static int[] row1;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        row = new boolean[n][n];
        column = new boolean[n][n];

        r = 1;
        for (int i = 1; i < n; i++) {
            r = r * i;
        }

        row1 = new int[n];
        row1[0] = 1;

        search(1, 1, 0);

        out.println(String.valueOf(s));
        out.close();
    }

    private static void search(int x, int y, int rs) {
        if (x >= n - 1) {
            s += r;
            return;
        }

        if (y >= n - 1) {
            int i = (n - 1) * n / 2 - rs - x;
            if (i != y && !row[x][i] && !column[y][i]) {

                if (x == 1) {
                    row1[n - 1] = i;
                    String key = getKey(getCircle(row1));
                    long count = getCount(key);
                    if (count >= 0) {
                        s += count;
                    } else {
                        long t = s;
                        column[y][i] = true;
                        search(x + 1, 1, 0);
                        column[y][i] = false;
                        setCount(key, s - t);
                    }
                } else {
                    column[y][i] = true;
                    search(x + 1, 1, 0);
                    column[y][i] = false;
                }
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i != x && i != y && !row[x][i] && !column[y][i]) {
                if (x == 1) {
                    row1[y] = i;
                }

                row[x][i] = true;
                column[y][i] = true;
                search(x, y + 1, rs + i);
                row[x][i] = false;
                column[y][i] = false;
            }
        }
    }

    private static int[] getCircle(int[] row1) {
        boolean[] b = new boolean[n];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!b[i]) {
                b[i] = true;
                int r = 0, t = i;
                while (!b[row1[t]]) {
                    t = row1[t];
                    b[t] = true;
                    r++;
                }
                list.add(r);
            }
        }

        int size = list.size();
        int[] circle = new int[size];
        for (int i = 0; i < size; i++) {
            circle[i] = list.get(i);
        }
        return circle;
    }

    private static String getKey(int[] circle) {
        Arrays.sort(circle);
        int keyNumber = 0;
        for (int i = 0; i < circle.length; i++) {
            keyNumber = keyNumber * 10 + circle[i];
        }
        return String.valueOf(keyNumber);
    }


    private static Map<String, Long> mCircleCountMap = new HashMap<>();

    private static long getCount(String key) {
        if (mCircleCountMap.containsKey(key)) {
            return mCircleCountMap.get(key);
        }
        return -1L;
    }

    private static void setCount(String key, long count) {
        mCircleCountMap.put(key, count);
    }

}


//public class latin {
//
//    private static final String INPUT_FILE_NAME = "latin.in";
//    private static final String OUTPUT_FILE_NAME = "latin.out";
//
//    private static boolean[][] row, column;
//    private static int n, r;
//    private static long s = 0;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));
//
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        n = Integer.parseInt(st.nextToken());
//        row = new boolean[n][n];
//        column = new boolean[n][n];
//
//        r = 1;
//        for (int i = 1; i < n; i++) {
//            r = r * i;
//        }
//
//        search(1, 0, 0);
//
//        out.println(String.valueOf(s));
//        out.close();
//    }
//
//    private static void search(int x, int y, int rs) {
//        if (x >= n - 1) {
//            s += r;
//            return;
//        }
//
//        if (y >= n - 1) {
//            int i = (n - 1) * n / 2 - rs;
//            if (i != y && !row[x][i] && !column[y][i]) {
//                column[y][i] = true;
//                search(x + 1, 0, 0);
//                column[y][i] = false;
//            }
//            return;
//        }
//
//        if (x == y) {
//            search(x, y + 1, rs);
//            return;
//        }
//
//        if (x == 1 && y == 0) {
//            int i = 1;
//            row[x][i] = true;
//            column[y][i] = true;
//            search(x, y + 1, rs + i);
//            row[x][i] = false;
//            column[y][i] = false;
//
//            i = 2;
//            if (i < n) {
//                long t = s;
//                row[x][i] = true;
//                column[y][i] = true;
//                search(x, y + 1, rs + i);
//                row[x][i] = false;
//                column[y][i] = false;
//                s = t + (s - t) * (n - 2);
//            }
//            return;
//        }
//
//        for (int i = 1; i < n; i++) {
//            if (i != y && !row[x][i] && !column[y][i]) {
//                row[x][i] = true;
//                column[y][i] = true;
//                search(x, y + 1, rs + i);
//                row[x][i] = false;
//                column[y][i] = false;
//            }
//        }
//    }
//}


//public class latin {
//
//    private static final String INPUT_FILE_NAME = "latin.in";
//    private static final String OUTPUT_FILE_NAME = "latin.out";
//
//    private static boolean[][] row, column;
//    private static int n;
//    private static long s = 0;
//    private static int[][] a;
//
//    private static PrintWriter out;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
//        out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));
//
//        StringTokenizer st = new StringTokenizer(f.readLine());
//        n = Integer.parseInt(st.nextToken());
//        row = new boolean[n][n];
//        column = new boolean[n][n];
//
//        a = new int[n][n];
//        search(0, 0);
//
//        out.println(s);
//        out.close();
//    }
//
//    private static void search(int x, int y) {
//        if (x >= n) {
//            s++;
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    out.print("" + a[i][j] + " ");
//                }
//                out.println();
//            }
//            out.println();
//            return;
//        }
//
//        if (y >= n) {
//            search(x + 1, 0);
//            return;
//        }
//
//        if (x == 0) {
//            a[x][y] = y + 1;
//            search(x, y + 1);
//            return;
//        }
//
//        if (y == 0) {
//            a[x][y] = x + 1;
//            search(x, y + 1);
//            return;
//        }
//
////        if (x == y) {
////            a[x][y] = 1;
////            search(x, y + 1);
////            return;
////        }
//
//        for (int i = 0; i < n; i++) {
//            if (i != x && i != y && !row[x][i] && !column[y][i]) {
//                row[x][i] = true;
//                column[y][i] = true;
//                a[x][y] = i + 1;
//                search(x, y + 1);
//                row[x][i] = false;
//                column[y][i] = false;
//            }
//        }
//    }
//}