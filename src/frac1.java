/*
ID: zhm05251
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

public class frac1 {
    private static final String INPUT_FILE_NAME = "frac1.in";
    private static final String OUTPUT_FILE_NAME = "frac1.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Fraction> list = new ArrayList<>();
        list.add(new Fraction(0, 1));
        list.add(new Fraction(1, 1));

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (isMutualPrime(j, i)) {
                    list.add(new Fraction(j, i));
                }
            }
        }

        Collections.sort(list);

        for (int i = 0, size = list.size(); i < size; i++) {
            out.println(list.get(i).getOutput());
        }
        out.close();
    }

    private static boolean isMutualPrime(int x, int y) {
        if (x <= y) {
            return x == 0 ? y == 1 : isMutualPrime(x, y % x);
        }
        return isMutualPrime(y, x);
    }

    public static class Fraction implements Comparable<Fraction> {
        private int mNumerator;
        private int mDenominator;

        public Fraction(int numerator, int denominator) {
            mNumerator = numerator;
            mDenominator = denominator;
        }

        public int getNumerator() {
            return mNumerator;
        }

        public int getDenominator() {
            return mDenominator;
        }

        public String getOutput() {
            return String.valueOf(mNumerator) + "/" + String.valueOf(mDenominator);
        }

        @Override
        public int compareTo(Fraction o) {
            if (mNumerator * o.getDenominator() < mDenominator * o.getNumerator()) {
                return -1;
            }
            return 1;
        }
    }
}
