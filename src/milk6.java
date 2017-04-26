/*
ID: zhm05251
LANG: JAVA
TASK: milk6
*/

import java.io.*;
import java.util.*;

public class milk6 {

    private static final String INPUT_FILE_NAME = "milk6.in";
    private static final String OUTPUT_FILE_NAME = "milk6.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            edges.add(new Edge(i + 1, x - 1, y - 1, l));
        }

        int[][] g = new int[m][m];
        for (Edge edge : edges) {
            g[edge.getX()][edge.getY()] += edge.getLength();
        }
        int maxFlow = ek(m, 0, m - 1, g);
        out.print(String.valueOf(maxFlow));

        Collections.sort(edges);

        int chosenEdgesNum = 0;
        boolean[] chosenEdges = new boolean[n + 1];
        for (Edge edge : edges) {
            g = new int[m][m];
            for (int j = 0; j < n; j++) {
                Edge e = edges.get(j);
                if (e.getIndex() != edge.getIndex() && !chosenEdges[e.getIndex()]) {
                    g[e.getX()][e.getY()] += e.getLength();
                }
            }
            int rm = ek(m, 0, m - 1, g);
            if (maxFlow - rm == edge.getLength()) {
                maxFlow = rm;
                chosenEdgesNum++;
                chosenEdges[edge.getIndex()] = true;
            }
        }

        out.println(" " + String.valueOf(chosenEdgesNum));

        for (int i = 1; i <= n; i++) {
            if (chosenEdges[i]) {
                out.println(String.valueOf(i));
            }
        }

        out.close();
    }

    private static final int ek(int m, int s, int t, int[][] g) {
        int result = 0;
        int[] pre = new int[m];
        int[] flow = new int[m];
        Queue<Integer> queue = new LinkedList<>();

        while (true) {
            for (int i = 0; i < m; i++) {
                pre[i] = -1;
                flow[i] = Integer.MAX_VALUE;
            }
            pre[s] = s;
            queue.clear();
            queue.add(s);

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == t) {
                    break;
                }
                for (int i = 0; i < m; i++) {
                    if (pre[i] < 0 && g[cur][i] != 0) {
                        pre[i] = cur;
                        flow[i] = flow[cur] < g[cur][i] ? flow[cur] : g[cur][i];
                        queue.add(i);
                    }
                }
            }

            if (pre[t] < 0) {
                break;
            }

            int i = t;
            while (i != s) {
                g[pre[i]][i] -= flow[t];
                g[i][pre[i]] += flow[t];
                i = pre[i];
            }

            result += flow[t];
        }
        return result;
    }

    private static class Edge implements Comparable<Edge> {
        private int i;
        private int x;
        private int y;
        private int l;

        public Edge(int i, int x, int y, int l) {
            this.i = i;
            this.x = x;
            this.y = y;
            this.l = l;
        }

        public int getIndex() {
            return i;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getLength() {
            return l;
        }

        @Override
        public int compareTo(Edge edge) {
            if (l > edge.getLength()) {
                return -1;
            } else if (l < edge.getLength()) {
                return 1;
            }
            return 0;
        }
    }
}
