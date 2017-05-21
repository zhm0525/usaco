/*
ID: zhm05251
LANG: JAVA
TASK: cryptcow
*/

import java.io.*;
import java.util.*;

public class cryptcow {

    private static final String INPUT_FILE_NAME = "cryptcow.in";
    private static final String OUTPUT_FILE_NAME = "cryptcow.out";

    private static final String MESSAGE = "Begin the Escape execution at the Break of Dawn";

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

        if (message.length == MESSAGE.length()) {
            return 1;
        }

        for (int i = 0; i < message.length; i++) {
            if (message[i] == 'C') {
                for (int j = i + 1; j < message.length; j++) {
                    if (message[j] == 'O') {
                        int p = i, q = j;
                        while (p - 1 >= 0 && message[p - 1] != 'C' && message[p - 1] != 'O' && message[p - 1] != 'W') {
                            p--;
                        }
                        while (q + 1 < message.length && message[q + 1] != 'C' && message[q + 1] != 'O' && message[q + 1] != 'W') {
                            q++;
                        }
                        char[] messageCheck = new char[(i - p) + (q - j)];
                        int x = 0;
                        for (int l = p; l < i; l++) {
                            messageCheck[x++] = message[l];
                        }
                        for (int l = j + 1; l <= q; l++) {
                            messageCheck[x++] = message[l];
                        }
                        boolean flag = true;
                        for (int u = 0; u < MESSAGE.length() - messageCheck.length; u++) {
                            flag = true;
                            for (int v = 0; v < messageCheck.length; v++) {
                                if (MESSAGE.charAt(u + v) != messageCheck[v]) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                break;
                            }
                        }
                        if (!flag) {
                            continue;
                        }

                        for (int k = j + 1; k < message.length; k++) {
                            if (message[k] == 'W') {
                                char[] messageNext = new char[message.length - 3];
                                x = 0;
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
                }
            }
        }
        return 0;
    }
}
