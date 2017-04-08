/*
ID: zhm05251
LANG: JAVA
TASK: wormhole
*/

import java.io.*;
import java.util.*;

public class wormhole {
    private static final String INPUT_FILE_NAME = "wormhole.in";
    private static final String OUTPUT_FILE_NAME = "wormhole.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Position position = new Position(x, y);
            positions.add(position);
        }

        int result = calculate(n, positions);
        out.println(result);
        out.close();
    }

    private static int calculate(int n, List<Position> positions) {
        int result = 0;
        List<List<Pair>> pairsList = new ArrayList<>();

        int index = -1;
        while (index < 0 || index < pairsList.size()) {
            if (index < 0) {
                int i = 0;
                for (int j = i + 1; j < n; j++) {
                    List<Pair> list = new ArrayList<>();
                    list.add(new Pair(i, j));
                    pairsList.add(list);
                }
            } else {
                List<Pair> pairs = pairsList.get(index);
                if (pairs.size() == n / 2) {
                    if (isMatch(n, positions, pairs)) {
                        result++;
                    }
                } else {
                    int i = 0;
                    while (i < n && isUsed(i, pairs)) {
                        i++;
                    }
                    for (int j = i + 1; j < n; j++) {
                        if (isUsed(j, pairs)) {
                            continue;
                        }
                        List<Pair> list = new ArrayList<>();
                        for (Pair pair : pairs) {
                            list.add(pair);
                        }
                        list.add(new Pair(i, j));
                        pairsList.add(list);
                    }
                }
            }
            index++;
        }

        return result;
    }

    private static boolean isUsed(int index, List<Pair> pairs) {
        for (Pair pair : pairs) {
            if (index == pair.getX() || index == pair.getY()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMatch(int n, List<Position> positions, List<Pair> pairs) {
        for (int i = 0; i < n; i++) {
            int startIndex = i;
            int nextIndex = i;
            for (int j = 0; j < n; j++) {
                nextIndex = findNextPositionIndex(n, positions, nextIndex);
                if (nextIndex < 0) {
                    break;
                }

//                这里尤其注意，从一个点出发，和从一个点掉入虫洞，是两个含义，不能在此处判断重合
//                if (nextIndex == startIndex) {
//                    return true;
//                }

                nextIndex = findNextPairIndex(n, pairs, nextIndex);
                if (nextIndex < 0) {
                    break;
                }
                if (nextIndex == startIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int findNextPositionIndex(int n, List<Position> positions, int index) {
        int nextIndex = -1;
        int nearestX = Integer.MAX_VALUE;
        Position currentPosition = positions.get(index);
        for (int i = 0; i < n; i++) {
            if (index != i) {
                Position position = positions.get(i);
                if (currentPosition.getY() == position.getY() &&
                        currentPosition.getX() < position.getX() &&
                        position.getX() < nearestX) {
                    nextIndex = i;
                    nearestX = position.getX();
                }
            }
        }
        return nextIndex;
    }

    private static int findNextPairIndex(int n, List<Pair> pairs, int index) {
        for (int i = 0; i < n; i++) {
            Pair pair = pairs.get(i);
            if (pair.getX() == index) {
                return pair.getY();
            }
            if (pair.getY() == index) {
                return pair.getX();
            }
        }
        return -1;
    }

    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

    public static class Pair {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
