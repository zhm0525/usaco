/*
ID: zhm05251
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.*;

public class sort3 {
    private static final String INPUT_FILE_NAME = "sort3.in";
    private static final String OUTPUT_FILE_NAME = "sort3.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int n1 = 0, n2 = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            list.add(x);
            if (x == 1) {
                n1++;
            } else if (x == 2) {
                n2++;
            }
        }

        int result = 0;

        // exchange for 1
        for (int i = 0; i < n1; i++) {
            if (list.get(i) == 1) {
                continue;
            }
            if (list.get(i) == 2) {
                int p = n1;
                while (list.get(p) != 1) {
                    p++;
                }
                list.set(p, 2);
            } else if (list.get(i) == 3) {
                int p = list.size() - 1;
                while (list.get(p) != 1) {
                    p--;
                }
                list.set(p, 3);
            }
            list.set(i, 1);
            result++;
        }

        // exchange for 2
        for (int i = n1; i < n1 + n2; i++) {
            if (list.get(i) == 2) {
                continue;
            }
            int p = list.size() - 1;
            while (list.get(p) != 2) {
                p--;
            }
            list.set(p, list.get(i));
            list.set(i, 2);
            result++;
        }
        out.println(result);
        out.close();
    }

}
