/*
ID: zhm05251
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.*;

public class milk {
    private static final String INPUT_FILE_NAME = "milk.in";
    private static final String OUTPUT_FILE_NAME = "milk.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Farmer> farmers = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            int price = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            Farmer farmer = new Farmer(price, amount);
            farmers.add(farmer);
        }
        Collections.sort(farmers);

        int total = 0;
        int index = 0;
        while (n > 0) {
            Farmer farmer = farmers.get(index);
            if (farmer.getAmount() < n) {
                total = total + farmer.getPrice() * farmer.getAmount();
                n = n - farmer.getAmount();
            } else {
                total = total + farmer.getPrice() * n;
                n = 0;
            }
            index++;
        }
        out.println(total);
        out.close();
    }

    public static class Farmer implements Comparable<Farmer> {

        private int mPrice;
        private int mAmount;

        public Farmer(int price, int amount) {
            mPrice = price;
            mAmount = amount;
        }

        public int getPrice() {
            return mPrice;
        }

        public int getAmount() {
            return mAmount;
        }


        @Override
        public int compareTo(Farmer o) {
            if (mPrice < o.getPrice()) {
                return -1;
            }
            return 1;
        }
    }

}