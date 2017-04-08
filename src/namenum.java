/*
ID: zhm05251
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

public class namenum {
    private static final String DICT_FILE_NAME = "dict.txt";
    private static final String INPUT_FILE_NAME = "namenum.in";
    private static final String OUTPUT_FILE_NAME = "namenum.out";

    private static final char[][] KEYPAD = {{}, {},
            {'A', 'B', 'C'},
            {'D', 'E', 'F'},
            {'G', 'H', 'I'},
            {'J', 'K', 'L'},
            {'M', 'N', 'O'},
            {'P', 'R', 'S'},
            {'T', 'U', 'V'},
            {'W', 'X', 'Y'}};

    private static List<String> mDict = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(DICT_FILE_NAME));

        String nextLine = f.readLine();
        while (nextLine != null) {
            StringTokenizer st = new StringTokenizer(nextLine);
            mDict.add(st.nextToken());
            nextLine = f.readLine();
        }

        f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        String input = st.nextToken();

        boolean hasResult = false;
        for (String words : mDict) {
            if (words.length() == input.length()) {
                boolean match = true;
                for (int i = 0, length = words.length(); i < length; i++) {
                    char c = words.charAt(i);
                    int num = input.charAt(i) - '0';
                    if (KEYPAD[num][0] == c || KEYPAD[num][1] == c || KEYPAD[num][2] == c) {
                        continue;
                    } else {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    out.println(words);
                    hasResult = true;
                }
            }
        }
        if (!hasResult) {
            out.println("NONE");
        }
        out.close();
    }

}
