/*
ID: zhm05251
LANG: JAVA
TASK: race3
*/

import java.io.*;
import java.util.*;

public class race3 {

    private static final String INPUT_FILE_NAME = "race3.in";
    private static final String OUTPUT_FILE_NAME = "race3.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        int[][] a = new int[50][51];
        int n = 0;
        StringTokenizer st = new StringTokenizer(f.readLine());
        while (st.hasMoreTokens()) {
            int t = Integer.parseInt(st.nextToken());
            if (t == -1) {
                break;
            }
            n++;
            while (t != -2) {
                a[n - 1][++a[n - 1][0]] = t;
                t = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(f.readLine());
        }

        List<Integer> answers1 = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            int[] queue = new int[n];
            queue[0] = 0;
            boolean[] flags = new boolean[n];
            flags[0] = true;
            flags[i] = true;
            int index = 0;
            int count = 1;
            while (index < count) {
                int x = queue[index];
                for (int j = 1; j <= a[x][0]; j++) {
                    if (!flags[a[x][j]]) {
                        flags[a[x][j]] = true;
                        queue[count++] = a[x][j];
                    }
                }
                index++;
            }
            if (!flags[n - 1]) {
                answers1.add(i);
            }
        }

        List<Integer> answers2 = new ArrayList<>();
        for (Integer i : answers1) {
            int[] queue = new int[n];
            queue[0] = 0;
            int[] flags = new int[n];
            flags[0] = 1;
            flags[i] = 2;
            int index = 0;
            int count = 1;
            while (index < count) {
                int x = queue[index];
                for (int j = 1; j <= a[x][0]; j++) {
                    if (flags[a[x][j]] == 0) {
                        flags[a[x][j]] = 1;
                        queue[count++] = a[x][j];
                    }
                }
                index++;
            }

            queue = new int[n];
            queue[0] = i;
            index = 0;
            count = 1;
            boolean flag = true;
            while (index < count) {
                int x = queue[index];
                for (int j = 1; j <= a[x][0]; j++) {
                    if (flags[a[x][j]] == 0) {
                        flags[a[x][j]] = 2;
                        queue[count++] = a[x][j];
                    } else if (flags[a[x][j]] == 1) {
                        flag = false;
                    }
                }
                index++;
            }

            if (flag) {
                answers2.add(i);
            }
        }

        out.print(String.valueOf(answers1.size()));
        for (int i : answers1) {
            out.print(" " + String.valueOf(i));
        }
        out.println();

        out.print(String.valueOf(answers2.size()));
        for (int i : answers2) {
            out.print(" " + String.valueOf(i));
        }
        out.println();
        out.close();
    }


}
