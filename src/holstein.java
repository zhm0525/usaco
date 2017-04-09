/*
ID: zhm05251
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.*;

public class holstein {
    private static final String INPUT_FILE_NAME = "holstein.in";
    private static final String OUTPUT_FILE_NAME = "holstein.out";

    private static int n;
    private static List<Integer> mVitamins;

    private static int m;
    private static List<List<Integer>> mFeedsList;

    private static boolean[] flags;
    private static int minimum = Integer.MAX_VALUE;
    private static List<Integer> mResults = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        mVitamins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mVitamins.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(f.readLine());
        m = Integer.parseInt(st.nextToken());

        mFeedsList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            mFeedsList.add(list);
        }

        flags = new boolean[m];
        search(0);

        out.print(minimum);
        for (Integer result : mResults) {
            out.print(" " + String.valueOf(result));
        }
        out.println();
        out.close();
    }

    private static void search(int position) {
        check();
        if (position >= m) {
            return;
        }
        flags[position] = true;
        search(position + 1);
        flags[position] = false;
        search(position + 1);
    }

    private static void check() {
        int[] totals = new int[n];
        for (int i = 0; i < m; i++) {
            if (flags[i]) {
                List<Integer> feeds = mFeedsList.get(i);
                for (int j = 0; j < n; j++) {
                    totals[j] = totals[j] + feeds.get(j);
                }
            }
        }
        boolean flag = true;
        for (int i = 0, size = mVitamins.size(); i < size; i++) {
            if (totals[i] < mVitamins.get(i)) {
                flag = false;
                break;
            }
        }

        if (!flag) {
            return;
        }

        int t = 0;
        for (int i = 0; i < m; i++) {
            t = flags[i] ? t + 1 : t;
        }
        if (t < minimum) {
            minimum = t;
            mResults.clear();
            for (int i = 0; i < m; i++) {
                if (flags[i]) {
                    mResults.add(i + 1);
                }
            }
        }
    }
}
