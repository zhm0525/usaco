/*
ID: zhm05251
LANG: JAVA
TASK: vans
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class vans {

    private static final String INPUT_FILE_NAME = "vans.in";
    private static final String OUTPUT_FILE_NAME = "vans.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

//        int[] g = new int[n + 1];
//        g[0] = 0;
//        g[1] = 1;
//        for (int i = 2; i < n; i++) {
//            for (int j = 1; j < i - 1; j++) {
//                g[i] += g[j] * (i - 1 - j) * 2;
//            }
//            g[i] += g[i - 2] + 1;
//        }
//
//        int s = 0;
//        for (int i = 1; i < n; i++) {
//            s += g[i] * 2;
//        }

        BigInteger[] g = new BigInteger[n + 1];
        g[0] = new BigInteger("0");
        g[1] = new BigInteger("1");
        for (int i = 2; i < n; i++) {
            g[i] = new BigInteger("0");
            for (int j = 1; j < i - 1; j++) {
                g[i] = g[i].add(g[j].multiply(BigInteger.valueOf((i - 1 - j) * 2)));
            }
            g[i] = g[i].add(g[i - 2]);
            g[i] = g[i].add(BigInteger.valueOf(1));
        }

        BigInteger s = new BigInteger("0");
        for (int i = 1; i < n; i++) {
            s = s.add(g[i]);
        }
        s = s.multiply(BigInteger.valueOf(2));

        out.println(s.toString());
        out.close();
    }

}