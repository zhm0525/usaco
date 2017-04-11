/*
ID: zhm05251
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.*;

public class prefix {
    private static final String INPUT_FILE_NAME = "prefix.in";
    private static final String OUTPUT_FILE_NAME = "prefix.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        List<String> words = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(f.readLine());
        boolean flag = true;
        while (flag) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            String next = st.nextToken();
            if (".".equals(next)) {
                flag = false;
            } else {
                words.add(next);
            }
        }
        List<Character> contents = new ArrayList<>();
        flag = true;
        while (flag) {
            String input = f.readLine();
            if (input == null) {
                flag = false;
            } else {
                st = new StringTokenizer(input);
                String line = st.nextToken();
                for (int i = 0, length = line.length(); i < length; i++) {
                    contents.add(line.charAt(i));
                }
            }
        }
        int contentsSize = contents.size();
        boolean[] match = new boolean[contentsSize + 1];
        match[0] = true;
        int result = 0;
        for (int i = 0; i <= contentsSize; i++) {
            if (match[i]) {
                for (String word : words) {
                    int length = word.length();
                    if (i + length <= contentsSize) {
                        if (match[i + length]) {
                            continue;
                        }
                        flag = true;
                        for (int j = 0; j < length; j++) {
                            if (contents.get(i + j) != word.charAt(j)) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            match[i + length] = true;
                        }
                    }
                }
                result = i;
            }
        }
        out.println(result);
        out.close();
    }

}
