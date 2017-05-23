/*
ID: zhm05251
LANG: JAVA
TASK: cryptcow
*/

import java.io.*;

public class cryptcow {

    private static final String INPUT_FILE_NAME = "cryptcow.in";
    private static final String OUTPUT_FILE_NAME = "cryptcow.out";

    private static final String MESSAGE = "Begin the Escape execution at the Break of Dawn";
    private static final int MESSAGE_LENGTH = MESSAGE.length();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        String input = f.readLine();
        int illegal = 0;
        int count = 0;

        if ((input.length() - MESSAGE.length()) % 3 == 0) {
            illegal = isIllegal(input);
            if (illegal > 0) {
                count = (input.length() - MESSAGE.length()) / 3;
            }
        }

        out.println("" + illegal + " " + count);
        out.close();
    }

    private static int isIllegal(String input) {
        int C = 0, O = 0, W = 0;
        for (int i = 0, length = input.length(); i < length; i++) {
            char ch = input.charAt(i);
            if (ch == 'C') {
                C++;
            } else if (ch == 'O') {
                O++;
            } else if (ch == 'W') {
                W++;
            }
        }
        if (C != O || C != W || O != W) {
            return 0;
        }
        if (input.length() - C - O - W != MESSAGE.length()) {
            return 0;
        }

        char[] message = new char[input.length()];
        for (int i = 0, length = input.length(); i < length; i++) {
            message[i] = input.charAt(i);
        }

        return search(message);
    }

    private static int search(char[] message) {
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < message.length; i++) {
//            stringBuffer.append(message[i]);
//        }
//        System.out.println(stringBuffer.toString());

        for (int i = 0; i < message.length; i++) {
            if (message[i] == 'C') {
                break;
            } else if (message[i] == 'O' || message[i] == 'W' ||
                    message[i] != MESSAGE.charAt(i)) {
                return 0;
            }
        }

        for (int i = 0; i < message.length; i++) {
            int k = message.length - 1 - i;
            int l = MESSAGE.length() - 1 - i;
            if (message[k] == 'W') {
                break;
            } else if (message[k] == 'C' || message[k] == 'O' ||
                    message[k] != MESSAGE.charAt(l)) {
                return 0;
            }
        }

//        if (!isMessageAvailable(message)) {
//            return 0;
//        }

        if (!mMessageTree.insert(message)) {
            return 0;
        }

        if (message.length == MESSAGE.length()) {
            return 1;
        }

        for (int i = 0; i < message.length; i++) {
            if (message[i] != 'C') {
                continue;
            }

            for (int k = message.length - 1; k >= i; k--) {
                if (message[k] != 'W') {
                    continue;
                }
                if (!isSubMessage(message, k, i)) {
                    continue;
                }

                for (int j = i + 1; j < k; j++) {
                    if (message[j] != 'O') {
                        continue;
                    }
                    if (!isSubMessage(message, i, j)) {
                        continue;
                    }
                    if (!isSubMessage(message, j, k)) {
                        continue;
                    }

                    char[] messageNext = new char[message.length - 3];
                    int x = 0;
                    for (int l = 0; l < i; l++) {
                        messageNext[x++] = message[l];
                    }
                    for (int l = j + 1; l < k; l++) {
                        messageNext[x++] = message[l];
                    }
                    for (int l = i + 1; l < j; l++) {
                        messageNext[x++] = message[l];
                    }
                    for (int l = k + 1; l < message.length; l++) {
                        messageNext[x++] = message[l];
                    }
                    if (search(messageNext) == 1) {
                        return 1;
                    }
                }
            }
        }

//        for (int j = 0; j < message.length; j++) {
//            if (message[j] != 'O') {
//                continue;
//            }
//            for (int i = 0; i < j; i++) {
//                if (message[i] != 'C') {
//                    continue;
//                }
//                if (!isSubMessage(message, i, j)) {
//                    continue;
//                }
//                for (int k = message.length - 1; k >= j + 1; k--) {
//                    if (message[k] != 'W') {
//                        continue;
//                    }
//                    if (!isSubMessage(message, k, i)) {
//                        continue;
//                    }
//                    if (!isSubMessage(message, j, k)) {
//                        continue;
//                    }
//
//                    char[] messageNext = new char[message.length - 3];
//                    int x = 0;
//                    for (int l = 0; l < i; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    for (int l = j + 1; l < k; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    for (int l = i + 1; l < j; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    for (int l = k + 1; l < message.length; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    if (search(messageNext) == 1) {
//                        return 1;
//                    }
//                }
//
//            }
//        }

//        for (int i = 0; i < message.length; i++) {
//            if (message[i] != 'C') {
//                continue;
//            }
//            for (int j = i + 1; j < message.length; j++) {
//                if (message[j] != 'O') {
//                    continue;
//                }
//                if (!isSubMessage(message, i, j)) {
//                    continue;
//                }
//                for (int k = j + 1; k < message.length; k++) {
//                    if (message[k] != 'W') {
//                        continue;
//                    }
//                    if (!isSubMessage(message, k, i)) {
//                        continue;
//                    }
//                    if (!isSubMessage(message, j, k)) {
//                        continue;
//                    }
//
//                    char[] messageNext = new char[message.length - 3];
//                    int x = 0;
//                    for (int l = 0; l < i; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    for (int l = j + 1; l < k; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    for (int l = i + 1; l < j; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    for (int l = k + 1; l < message.length; l++) {
//                        messageNext[x++] = message[l];
//                    }
//                    if (search(messageNext) == 1) {
//                        return 1;
//                    }
//                }
//            }
//        }
        return 0;
    }

    private static boolean isSubMessage(char[] message, int i, int j) {
        int p = i, q = j;
        while (p - 1 >= 0 && message[p - 1] != 'C' && message[p - 1] != 'O' && message[p - 1] != 'W') {
            p--;
        }
        while (q + 1 < message.length && message[q + 1] != 'C' && message[q + 1] != 'O' && message[q + 1] != 'W') {
            q++;
        }

        int length = (i - p) + (q - j);
        boolean flag = true;
        for (int u = 0; u <= MESSAGE_LENGTH - length; u++) {
            flag = true;
            for (int v = p, t = 0; v < i; v++, t++) {
                if (MESSAGE.charAt(u + t) != message[v]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            for (int v = j + 1, t = i - p; v <= q; v++, t++) {
                if (MESSAGE.charAt(u + t) != message[v]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }

    private static boolean isMessageAvailable(char[] message) {
        for (int i = 0; i < message.length; i++) {
            if (message[i] == 'C') {
                break;
            } else if (message[i] == 'O' || message[i] == 'W' ||
                    message[i] != MESSAGE.charAt(i)) {
                return false;
            }
        }

        for (int i = 0; i < message.length; i++) {
            int k = message.length - 1 - i;
            int l = MESSAGE.length() - 1 - i;
            if (message[k] == 'W') {
                break;
            } else if (message[k] == 'C' || message[k] == 'O' ||
                    message[k] != MESSAGE.charAt(l)) {
                return false;
            }
        }

        int p = 0;
        while (p < message.length) {
            if (message[p] != 'C' && message[p] != 'O' && message[p] != 'W') {
                int q = p + 1;
                while (q < message.length && message[q] != 'C' && message[q] != 'O' && message[q] != 'W') {
                    q++;
                }
                boolean flag = true;
                int l = q - p;
                for (int i = 0; i <= MESSAGE_LENGTH - l; i++) {
                    flag = true;
                    for (int j = 0; j < l; j++) {
                        if (MESSAGE.charAt(i + j) != message[p + j]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
                if (!flag) {
                    return false;
                }
                p = q + 1;
            } else {
                p++;
            }
        }
        return true;
    }

    private static MessageTree mMessageTree = new MessageTree();

    private static class MessageTree {

        private static final int WORD_COUNT = 53; // 26 for A..Z, 26 for a..z, 1 for space
        private MessageTree[] trees = new MessageTree[WORD_COUNT];

        public MessageTree() {

        }

        public boolean isExist(char[] message) {
            return isExist(message, 0);
        }

        public boolean isExist(char[] message, int index) {
            if (index >= message.length) {
                return true;
            }
            int x = convertCharToInt(message[index]);
            if (trees[x] == null) {
                return false;
            }
            return trees[x].isExist(message, index + 1);
        }

        public boolean insert(char[] message) {
            return insert(message, 0);
        }

        public boolean insert(char[] message, int index) {
            if (index >= message.length) {
                return false;
            }
            int x = convertCharToInt(message[index]);
            boolean flag = false;
            if (trees[x] == null) {
                trees[x] = new MessageTree();
                flag = true;
            }
            return flag || trees[x].insert(message, index + 1);
        }

        private int convertCharToInt(char c) {
            if (c == ' ') {
                return 0;
            } else if ('A' <= c && c <= 'Z') {
                return c - 'A' + 1;
            } else if ('a' <= c && c <= 'z') {
                return c - 'a' + 27;
            }
            return -1;
        }
    }
}