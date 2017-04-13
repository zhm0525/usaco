/*
ID: zhm05251
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.*;

public class contact {
    private static final String INPUT_FILE_NAME = "contact.in";
    private static final String OUTPUT_FILE_NAME = "contact.out";

    private static List<Character> contentList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        String input = f.readLine();
        while (input != null) {
            for (int i = 0, length = input.length(); i < length; i++) {
                contentList.add(input.charAt(i));
            }
            input = f.readLine();
        }

        int[] x = new int[getMaxLength(b) + 1];
        for (int i = 0, size = contentList.size(); i < size; i++) {
            for (int j = a; j <= b; j++) {
                if (i + j <= size) {
                    x[getIndex(i, i + j)]++;
                }
            }
        }

        List<Pair> list = new ArrayList<>();
        for (int i = getMaxLength(a - 1) + 1; i <= getMaxLength(b); i++) {
            if (x[i] > 0) {
                list.add(new Pair(i, x[i]));
            }
        }
        Collections.sort(list);

        int k = 0, listSize = list.size();
        for (int i = 0; i < n; i++) {
            if (k < listSize) {
                Pair pair = list.get(k);
                out.println(String.valueOf(pair.getCount()));
                int r = 0;
                while (k < listSize &&
                        list.get(k).getCount() == pair.getCount()) {
                    if (r > 0) {
                        out.print(" ");
                    }
                    out.print(list.get(k).getString());
                    r++;
                    if (r >= 6) {
                        out.println();
                        r = 0;
                    }
                    k++;
                }
                if (r > 0) {
                    out.println();
                }
            }
        }
        out.close();
    }

    private static int getMaxLength(int x) {
        int s = 0;
        for (int i = 0; i < x; i++) {
            s = (s + 1) * 2;
        }
        return s;
    }

    private static int getIndex(int a, int b) {
        int s = 0;
        for (int i = a; i < b; i++) {
            if (contentList.get(i) == '0') {
                s = s * 2 + 1;
            } else if (contentList.get(i) == '1') {
                s = s * 2 + 2;
            }
        }
        return s;
    }

    private static class Pair implements Comparable<Pair> {
        private int index;
        private int count;

        public Pair(int index, int count) {
            this.index = index;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public int getIndex() {
            return index;
        }

        private String getString() {
            int t = index;
            StringBuffer stringBuffer = new StringBuffer();
            while (t > 0) {
                if ((t - 1) % 2 == 0) {
                    stringBuffer.insert(0, "0");
                } else {
                    stringBuffer.insert(0, "1");
                }
                t = (t - 1) / 2;
            }
            return stringBuffer.toString();
        }

        @Override
        public int compareTo(Pair pair) {
            if (count > pair.getCount()) {
                return -1;
            } else if (count == pair.getCount() &&
                    index < pair.getIndex()) {
                return -1;
            }
            return 1;
        }
    }


}
