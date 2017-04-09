/*
ID: zhm05251
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.*;

public class pprime {
    private static final String INPUT_FILE_NAME = "pprime.in";
    private static final String OUTPUT_FILE_NAME = "pprime.out";

    private static List<Integer> mPrimes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // 1
        if (a <= 2 && 2 <= b) {
            out.println(2);
        }
        if (a <= 3 && 3 <= b) {
            out.println(3);
        }
        if (a <= 5 && 5 <= b) {
            out.println(5);
        }
        if (a <= 7 && 7 <= b) {
            out.println(7);
        }

        // 2
        if (a <= 11 && 11 <= b) {
            out.println(11);
        }

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

        // 3
        if (99 < b || a <= 999) {
            for (int i = 1; i <= 9; i++) {
                if (i % 2 == 1 && i != 5) {
                    for (int j = 0; j <= 9; j++) {
                        int number = i * 100 + j * 10 + i;
                        if (a <= number && number <= b && isPrime(number)) {
                            out.println(number);
                        }
                    }
                }
            }
        }

        // 4
        if (999 < b || a <= 9999) {
            for (int i = 1; i <= 9; i++) {
                if (i % 2 == 1 && i != 5) {
                    for (int j = 0; j <= 9; j++) {
                        int number = i * 1000 + j * 100 + j * 10 + i;
                        if (a <= number && number <= b && isPrime(number)) {
                            out.println(number);
                        }
                    }
                }
            }
        }

        // 5
        if (9999 < b || a <= 99999) {
            for (int i = 1; i <= 9; i++) {
                if (i % 2 == 1 && i != 5) {
                    for (int j = 0; j <= 9; j++) {
                        for (int k = 0; k <= 9; k++) {
                            int number = i * 10000 + j * 1000 + k * 100 + j * 10 + i;
                            if (a <= number && number <= b && isPrime(number)) {
                                out.println(number);
                            }
                        }
                    }
                }
            }
        }

        // 6
        if (99999 < b || a <= 999999) {
            for (int i = 1; i <= 9; i++) {
                if (i % 2 == 1 && i != 5) {
                    for (int j = 0; j <= 9; j++) {
                        for (int k = 0; k <= 9; k++) {
                            int number = i * 100000 + j * 10000 + k * 1000 + k * 100 + j * 10 + i;
                            if (a <= number && number <= b && isPrime(number)) {
                                out.println(number);
                            }
                        }
                    }
                }
            }
        }

        // 7
        if (999999 < b || a <= 9999999) {
            for (int i = 1; i <= 9; i++) {
                if (i % 2 == 1 && i != 5) {
                    for (int j = 0; j <= 9; j++) {
                        for (int k = 0; k <= 9; k++) {
                            for (int l = 0; l <= 9; l++) {
                                int number = i * 1000000 + j * 100000 + k * 10000 + l * 1000 + k * 100 + j * 10 + i;
                                if (a <= number && number <= b && isPrime(number)) {
                                    out.println(number);
                                }
                            }
                        }
                    }
                }
            }
        }

        // 8
        if (9999999 < b || a <= 99999999) {
            for (int i = 1; i <= 9; i++) {
                if (i % 2 == 1 && i != 5) {
                    for (int j = 0; j <= 9; j++) {
                        for (int k = 0; k <= 9; k++) {
                            for (int l = 0; l <= 9; l++) {
                                int number = i * 10000000 + j * 1000000 + k * 100000 + l * 10000 + l * 1000 + k * 100 + j * 10 + i;
                                if (a <= number && number <= b && isPrime(number)) {
                                    out.println(number);
                                }
                            }
                        }
                    }
                }
            }
        }
        out.close();
    }

    private static boolean isPrime(int number) {
        int i = 0;
        while (i< mPrimes.size() && mPrimes.get(i) < number / 2) {
            if (number % mPrimes.get(i) == 0) {
                return false;
            }
            i++;
        }
        return true;
    }
}
