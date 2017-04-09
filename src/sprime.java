/*
ID: zhm05251
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.*;

public class sprime {
    private static final String INPUT_FILE_NAME = "sprime.in";
    private static final String OUTPUT_FILE_NAME = "sprime.out";

    private static List<Integer> mPrimes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        final int MAX = 10000;
        boolean[] flags = new boolean[MAX];
        for (int i = 2; i < MAX; i++) {
            if (!flags[i]) {
                mPrimes.add(i);
            }
            int j = 1;
            while (i * j < MAX) {
                flags[i * j] = true;
                j++;
            }
        }

        int limit = 1;
        for (int i = 0; i < n; i++) {
            limit = limit * 10;
        }

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);

        int index = 0;
        while (index < list.size()) {
            int x = list.get(index);
            if (x * 10 > limit) {
                break;
            }
            for (int i = 1; i <= 9; i++) {
                if (i % 2 == 1 && i != 5) {
                    if (isPrime(x * 10 + i)) {
                        list.add(x * 10 + i);
                    }
                }
            }
            index++;
        }

        for (int i = 0, size = list.size(); i < size; i++) {
            int x = list.get(i);
            if (limit / 10 < x && x < limit) {
                out.println(x);
            }
        }
        out.close();
    }

    private static boolean isPrime(int number) {
        int i = 0;
        while (i < mPrimes.size() && mPrimes.get(i) < number / 2) {
            if (number % mPrimes.get(i) == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

}
