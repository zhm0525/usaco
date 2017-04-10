/*
ID: zhm05251
LANG: JAVA
TASK: hamming
*/

import java.io.*;
import java.util.*;

public class hamming {
    private static final String INPUT_FILE_NAME = "hamming.in";
    private static final String OUTPUT_FILE_NAME = "hamming.out";

    private static int n, b, d;
    private static List<Integer> mResults;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        mResults = new ArrayList<>();

        search(new ArrayList<Integer>());

        for (int i = 0; i < n; i++) {
            if (i % 10 != 0) {
                out.print(" ");
            }
            out.print(mResults.get(i));
            if (i == n - 1 || i % 10 == 9) {
                out.println();
            }
        }
        out.close();
    }

    private static void search(List<Integer> list) {
        if (list.size() == n) {
            mResults.addAll(list);
            return;
        }
        if (list.size() == 0) {
            list.add(0);
            search(list);
        } else {
            int size = list.size();
            int next = list.get(size - 1) + 1;
            while (mResults.size() == 0) {
                boolean flag = true;
                for (int i = 0; i < size; i++) {
                    if (getHammingDistance(list.get(i), next) < d) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    list.add(next);
                    search(list);
                    list.remove(list.size() - 1);
                }
                next++;
            }
        }
    }

    private static int getHammingDistance(int x, int y) {
        int distance = 0;
        while (x > 0 || y > 0) {
            if (x % 2 != y % 2) {
                distance++;
            }
            x = x / 2;
            y = y / 2;
        }
        return distance;
    }
}
