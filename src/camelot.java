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
    private static final int MAX_VALUE = Integer.MAX_VALUE / 4;

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

        Position kingPosition = list.get(0);
        int minKing2Knight = MAX_VALUE;
        for (int i = 1, size = list.size(); i < size; i++) {
            Position knightPosition = list.get(i);
            int t = kingSP[kingPosition.getX()][kingPosition.getY()]
                    [knightPosition.getX()][knightPosition.getY()];
            if (t < minKing2Knight) {
                minKing2Knight = t;
            }
        }

        List<Position> catchKingPositions = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (kingSP[kingPosition.getX()][kingPosition.getY()][i][j] < minKing2Knight) {
                    catchKingPositions.add(new Position(i, j));
                }
            }
        }

        int s = MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // (i,j) gather point
                boolean flag = true;
                int t = 0;
                for (int k = 1, size = list.size(); k < size; k++) {
                    Position knightPosition = list.get(k);
                    if (knightSP[knightPosition.getX()][knightPosition.getY()][i][j] == MAX_VALUE) {
                        flag = false;
                        break;
                    }
                    t = t + knightSP[knightPosition.getX()][knightPosition.getY()][i][j];
                }
                if (!flag) {
                    continue;
                }

                if (t + minKing2Knight < s) {
                    s = t + minKing2Knight;
                }
                if (t + kingSP[kingPosition.getX()][kingPosition.getY()][i][j] < s) {
                    s = t + kingSP[kingPosition.getX()][kingPosition.getY()][i][j];
                }
                if (minKing2Knight == 0 || t > s) {
                    continue;
                }

                for (Position catchKingPosition : catchKingPositions) {
                    int x = catchKingPosition.getX();
                    int y = catchKingPosition.getY();

                    for (int k = 1, size = list.size(); k < size; k++) {
                        // k knight catch the king
                        int r = t;
                        Position knightPosition = list.get(k);
                        r = r - knightSP[knightPosition.getX()][knightPosition.getY()][i][j];
                        r = r + kingSP[kingPosition.getX()][kingPosition.getY()][x][y] +
                                knightSP[knightPosition.getX()][knightPosition.getY()][x][y] +
                                knightSP[x][y][i][j];

                        if (r < s) {
                            s = r;
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
                map[i][j] = MAX_VALUE;
            }
        }
        map[position.getX()][position.getY()] = 0;
        List<Position> list = new ArrayList<>();
        list.add(position);
        int index = 0;
        while (index < list.size()) {
            Position currentPosition = list.get(index);
            int x = currentPosition.getX();
            int y = currentPosition.getY();
            for (int i = 0; i < directions.length; i++) {
                int xt = x + directions[i][0];
                int yt = y + directions[i][1];
                if (0 <= xt && xt < m && 0 <= yt && yt < n) {
                    if (map[xt][yt] >= MAX_VALUE) {
                        map[xt][yt] = map[currentPosition.getX()][currentPosition.getY()] + 1;
                        list.add(new Position(xt, yt));
                    }
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

    }
}
