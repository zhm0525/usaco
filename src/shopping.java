/*
ID: zhm05251
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.*;

public class shopping {

    private static final String INPUT_FILE_NAME = "shopping.in";
    private static final String OUTPUT_FILE_NAME = "shopping.out";

    private static final int MAX = 5;
    private static int[] mProductNumbers = new int[MAX];
    private static int[] mProductQuantities = new int[MAX];
    private static int[] mProductPrices = new int[MAX];

    private static List<Discount> mDiscounts = new ArrayList<>();
    private static int[][][][][] x = new int[MAX + 1][MAX + 1][MAX + 1][MAX + 1][MAX + 1];
    private static int s, b, n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        s = Integer.parseInt(st.nextToken());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(f.readLine());
            int m = Integer.parseInt(st.nextToken());
            int[] quantities = new int[MAX];
            for (int j = 0; j < m; j++) {
                int number = Integer.parseInt(st.nextToken());
                int index = getProductIndex(number);
                quantities[index] = Integer.parseInt(st.nextToken());
            }
            int price = Integer.parseInt(st.nextToken());
            mDiscounts.add(new Discount(quantities, price));
        }
        st = new StringTokenizer(f.readLine());
        b = Integer.parseInt(st.nextToken());
        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(f.readLine());
            int number = Integer.parseInt(st.nextToken());
            int index = getProductIndex(number);
            mProductQuantities[index] = Integer.parseInt(st.nextToken());
            mProductPrices[index] = Integer.parseInt(st.nextToken());
        }

        for (int i0 = 0; i0 < MAX + 1; i0++) {
            for (int i1 = 0; i1 < MAX + 1; i1++) {
                for (int i2 = 0; i2 < MAX + 1; i2++) {
                    for (int i3 = 0; i3 < MAX + 1; i3++) {
                        for (int i4 = 0; i4 < MAX + 1; i4++) {
                            x[i0][i1][i2][i3][i4] = Integer.MAX_VALUE;
                        }
                    }
                }
            }
        }

        out.println(count(mProductQuantities));
        out.close();
    }

    private static int count(int[] q) {
        if (x[q[0]][q[1]][q[2]][q[3]][q[4]] < Integer.MAX_VALUE) {
            return x[q[0]][q[1]][q[2]][q[3]][q[4]];
        }
        int s = 0;
        for (int i = 0; i < MAX; i++) {
            s = s + q[i] * mProductPrices[i];
        }

        for (Discount discount : mDiscounts) {
            boolean flag = true;
            int[] dq = discount.getQuantities();
            int[] tq = new int[MAX];
            for (int i = 0; i < MAX; i++) {
                tq[i] = q[i] - dq[i];
                if (tq[i] < 0) {
                    flag = false;
                }
            }
            if (flag) {
                int t = discount.getPrice() + count(tq);
                s = t < s ? t : s;
            }
        }
        x[q[0]][q[1]][q[2]][q[3]][q[4]] = s;
        return s;
    }

    private static int getProductIndex(int number) {
        for (int i = 0; i < n; i++) {
            if (mProductNumbers[i] == number) {
                return i;
            }
        }
        mProductNumbers[n++] = number;
        return n - 1;
    }

    private static class Discount {
        private int[] mQuantities = new int[MAX];
        private int mPrice;

        public Discount(int[] quantities, int price) {
            mQuantities = quantities;
            mPrice = price;
        }

        public int[] getQuantities() {
            return mQuantities;
        }

        public int getPrice() {
            return mPrice;
        }
    }
}
