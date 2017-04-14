/*
ID: zhm05251
LANG: JAVA
TASK: msquare
*/

import java.io.*;
import java.util.*;

public class msquare {

    private static final String INPUT_FILE_NAME = "msquare.in";
    private static final String OUTPUT_FILE_NAME = "msquare.out";

    private static final int SIZE = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        int[] targetMap = new int[SIZE];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < SIZE / 2; i++) {
            targetMap[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < SIZE / 2; i++) {
            targetMap[SIZE - 1 - i] = Integer.parseInt(st.nextToken());
        }

        List<State> states = new ArrayList<>();
        states.add(new State());
        int index = 0;
        boolean[] flags = new boolean[10000000];
        flags[states.get(index).getMapHash()] = true;
        while (index < states.size() &&
                !states.get(index).isFinish(targetMap)) {
            State state = states.get(index);

            State stateA = state.transformA();
            State stateB = state.transformB();
            State stateC = state.transformC();

            if (!flags[stateA.getMapHash()]) {
                flags[stateA.getMapHash()] = true;
                states.add(stateA);
            }
            if (!flags[stateB.getMapHash()]) {
                flags[stateB.getMapHash()] = true;
                states.add(stateB);
            }
            if (!flags[stateC.getMapHash()]) {
                flags[stateC.getMapHash()] = true;
                states.add(stateC);
            }
            index++;
        }
        if (index < states.size()) {
            List<Character> records = states.get(index).getRecords();
            int size = records.size();
            out.println(String.valueOf(size));
            for (int i = 0; i < size; i++) {
                out.print(records.get(i));
            }
            out.println();
        } else {
            out.println("NO ANSWER");
        }
        out.close();
    }

    private static class State {
        private int[] map = new int[SIZE];
        private List<Character> records = new ArrayList<>();

        public State() {
            for (int i = 0; i < SIZE / 2; i++) {
                map[i] = i + 1;
            }
            for (int i = 0; i < SIZE / 2; i++) {
                map[SIZE - 1 - i] = i + 1 + SIZE / 2;
            }
        }

        public State(int[] map, List<Character> records) {
            this.map = map;
            this.records = records;
        }

        public State transformA() {
            int[] targetMap = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                if (i < SIZE / 2) {
                    targetMap[i] = map[i + SIZE / 2];
                } else {
                    targetMap[i] = map[i - SIZE / 2];
                }
            }
            List<Character> targetRecords = new ArrayList<>();
            targetRecords.addAll(records);
            targetRecords.add('A');
            return new State(targetMap, targetRecords);
        }

        public State transformB() {
            int[] targetMap = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                if (i % (SIZE / 2) == 0) {
                    targetMap[i] = map[i + SIZE / 2 - 1];
                } else {
                    targetMap[i] = map[i - 1];
                }
            }
            List<Character> targetRecords = new ArrayList<>();
            targetRecords.addAll(records);
            targetRecords.add('B');
            return new State(targetMap, targetRecords);
        }

        public State transformC() {
            int[] targetMap = new int[SIZE];
            // same
            targetMap[0] = map[0];
            targetMap[3] = map[3];
            targetMap[4] = map[4];
            targetMap[7] = map[7];
            // closewise
            targetMap[1] = map[5];
            targetMap[2] = map[1];
            targetMap[6] = map[2];
            targetMap[5] = map[6];

            List<Character> targetRecords = new ArrayList<>();
            targetRecords.addAll(records);
            targetRecords.add('C');
            return new State(targetMap, targetRecords);
        }

        public List<Character> getRecords() {
            return records;
        }

        public boolean isFinish(int[] targetMap) {
            for (int i = 0; i < SIZE; i++) {
                if (map[i] != targetMap[i]) {
                    return false;
                }
            }

            return true;
        }

        public int getMapHash() {
            int s = 0;
            for (int i = 0; i < SIZE - 1; i++) {
                s = s * 10 + map[i];
            }
            return s;
        }
    }
}
