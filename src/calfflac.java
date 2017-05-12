/*
ID: zhm05251
LANG: JAVA
TASK: calfflac
*/

import java.io.*;
import java.util.*;

public class calfflac {

    private static final String INPUT_FILE_NAME = "calfflac.in";
    private static final String OUTPUT_FILE_NAME = "calfflac.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        int n = 0;
        char[] a = new char[20000 + 250];

        int m = 0;
        char[] b = new char[20000];
        int[] c = new int[20000];

        String input = f.readLine();
        while (input != null) {
            for (int i = 0, length = input.length(); i < length; i++) {
                a[n] = input.charAt(i);
                if (Character.isLetter(a[n])) {
                    b[m] = Character.toLowerCase(a[n]);
                    c[m] = n;
                    m++;
                }
                n++;
            }
            a[n++] = '\n';
            input = f.readLine();
        }

        int l = 0, p = 0, q = 0;
        for (int i = 0; i < m; i++) {
            int k = 0;
            while (i - k >= 0 && i + k + 1 < m && b[i - k] == b[i + 1 + k]) {
                k++;
            }
            k--;
            if ((k + 1) * 2 > l) {
                l = (k + 1) * 2;
                p = c[i - k];
                q = c[i + k + 1];
            }

            k = 0;
            while (i - k >= 0 && i + k < m && b[i - k] == b[i + k]) {
                k++;
            }
            k--;
            if (k * 2 + 1 > l) {
                l = k * 2 + 1;
                p = c[i - k];
                q = c[i + k];
            }
        }

        out.println(String.valueOf(l));
        for (int i = p; i <= q; i++) {
            out.print(a[i]);
        }
        out.println();
        out.close();
    }
}
