/*
ID: zhm05251
LANG: JAVA
TASK: heritage
*/

import java.io.*;
import java.util.*;

public class heritage {

    private static final String INPUT_FILE_NAME = "heritage.in";
    private static final String OUTPUT_FILE_NAME = "heritage.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        String inOrder = f.readLine();
        String preOrder = f.readLine();

        out.println(getPostOrder(getTree(inOrder, preOrder)));
        out.close();
    }

    private static Tree getTree(String inOrder, String preOrder) {
        if (inOrder == null || preOrder == null ||
                inOrder.length() == 0 || preOrder.length() == 0 ||
                inOrder.length() != preOrder.length()) {
            return null;
        }
        char headValue = preOrder.charAt(0);
        int index = inOrder.indexOf(headValue);
        Tree tree = new Tree(headValue);
        tree.setLeft(getTree(inOrder.substring(0, index), preOrder.substring(1, index + 1)));
        tree.setRight(getTree(inOrder.substring(index + 1), preOrder.substring(index + 1)));
        return tree;
    }

    private static String getPostOrder(Tree tree) {
        if (tree == null) {
            return "";
        }
        return getPostOrder(tree.getLeft()) + getPostOrder(tree.getRight()) + String.valueOf(tree.value);
    }

    private static class Tree {
        private char value;
        private Tree left;
        private Tree right;

        public Tree(char value) {
            this.value = value;
            left = null;
            right = null;
        }

        public char getValue() {
            return value;
        }

        public void setLeft(Tree tree) {
            left = tree;
        }

        public void setRight(Tree tree) {
            right = tree;
        }

        public Tree getLeft() {
            return left;
        }

        public Tree getRight() {
            return right;
        }
    }
}
