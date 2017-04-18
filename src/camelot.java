/*
ID: zhm05251
LANG: JAVA
TASK: camelot
*/

import java.io.*;
import java.util.*;

public class camelot {

    private static final String INPUT_FILE_NAME = "camelot.in";
    private static final String OUTPUT_FILE_NAME = "camelot.out";

    private static final int[][] KING_DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private static final int[][] KNIGHT_DIRECTIONS = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Position> list = new ArrayList<>();
        String input = f.readLine();
        while (input != null) {
            st = new StringTokenizer(input);
            while (st.hasMoreTokens()) {
                int x = st.nextToken().charAt(0) - 'A';
                int y = Integer.parseInt(st.nextToken()) - 1;
                list.add(new Position(x, y));
            }
            input = f.readLine();
        }

        int[][][][] kingSP = new int[m][n][m][n];
        int[][][][] knightSP = new int[m][n][m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                kingSP[i][j] = getShortestPathMap(new Position(i, j), m, n, KING_DIRECTIONS);
                knightSP[i][j] = getShortestPathMap(new Position(i, j), m, n, KNIGHT_DIRECTIONS);
            }
        }

        int s = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // (i,j) gather point
                for (int x = 0; x < m; x++) {
                    for (int y = 0; y < m; y++) {
                        // (x,y) point for one knight catch king
                        for (int k = 0, size = list.size(); k < size; k++) {
                            // k knight catch the king, k == 0 for king walk self
                            int t = 0;


                            if (t < s) {
                                s = t;
                            }
                        }
                    }
                }
            }
        }
        out.println(String.valueOf(s));
        out.close();
    }

    private static int[][] getShortestPathMap(Position position, int m, int n, int[][] directions) {
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        map[position.getX()][position.getY()] = 0;
        List<Position> list = new ArrayList<>();
        list.add(position);
        int index = 0;
        while (index < list.size()) {
            Position currentPosition = list.get(index);
            List<Position> nextPositions = currentPosition.getNextPositions(directions, m, n);
            for (Position p : nextPositions) {
                if (map[p.getX()][p.getY()] >= Integer.MAX_VALUE) {
                    map[p.getX()][p.getY()] = map[currentPosition.getX()][currentPosition.getY()] + 1;
                    list.add(p);
                }
            }
            index++;
        }
        return map;
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

        public List<Position> getNextPositions(int[][] directions, int m, int n) {
            List<Position> list = new ArrayList<>();
            for (int i = 0; i < directions.length; i++) {
                int xt = x + directions[i][0];
                int yt = y + directions[i][1];
                if (0 <= xt && xt < m && 0 <= yt && yt < n) {
                    list.add(new Position(xt, yt));
                }
            }
            return list;
        }

    }
}
