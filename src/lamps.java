/*
ID: zhm05251
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.*;

public class lamps {
    private static final String INPUT_FILE_NAME = "lamps.in";
    private static final String OUTPUT_FILE_NAME = "lamps.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        List<Integer> mLampOnList = new ArrayList<>();
        int on = Integer.parseInt(st.nextToken());
        while (on != -1) {
            mLampOnList.add(on);
            on = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(f.readLine());
        List<Integer> mLampOffList = new ArrayList<>();
        int off = Integer.parseInt(st.nextToken());
        while (off != -1) {
            mLampOffList.add(off);
            off = Integer.parseInt(st.nextToken());
        }

        List<LampsState> initList = new ArrayList<>();
        initList.add(new LampsState(n));

        List<List<LampsState>> mLampsStatesList = new ArrayList<>();
        mLampsStatesList.add(initList);

        for (int i = 1; i <= c; i++) {
            List<LampsState> list = new ArrayList<>();
            for (int j = 0, size = mLampsStatesList.get(i - 1).size(); j < size; j++) {
                LampsState state = mLampsStatesList.get(i - 1).get(j);
                list.add(new LampsState(n, state.switch1()));
                list.add(new LampsState(n, state.switch2()));
                list.add(new LampsState(n, state.switch3()));
                list.add(new LampsState(n, state.switch4()));
            }
            int index = list.size() - 1;
            while (index > 0) {
                boolean dup = false;
                for (int j = 0; j < index; j++) {
                    if (list.get(j).isSame(list.get(index))) {
                        dup = true;
                    }
                }
                if (dup) {
                    list.remove(index);
                }
                index--;
            }
            mLampsStatesList.add(list);
        }

        List<LampsState> finalList = mLampsStatesList.get(c);

        List<LampsState> results = new ArrayList<>();
        for (int i = 0, size = finalList.size(); i < size; i++) {
            LampsState state = finalList.get(i);
            if (state.checkOn(mLampOnList) && state.checkOff(mLampOffList)) {
                results.add(state);
            }
        }

        if (results.isEmpty()) {
            out.println("IMPOSSIBLE");
        } else {
            Collections.sort(results);
            for (LampsState state : results) {
                boolean[] states = state.getStates();
                for (int i = 0; i < n; i++) {
                    if (states[i]) {
                        out.print("1");
                    } else {
                        out.print("0");
                    }
                }
                out.println();
            }
        }
        out.close();
    }

    private static class LampsState implements Comparable<LampsState> {
        private int n;
        private boolean[] mStates;

        public LampsState(int n) {
            this.n = n;
            this.mStates = new boolean[n];
            for (int i = 0; i < n; i++) {
                mStates[i] = true;
            }
        }

        public LampsState(int n, boolean[] states) {
            this.n = n;
            this.mStates = states;
        }

        public int getN() {
            return n;
        }

        public boolean[] getStates() {
            return mStates;
        }

        public boolean checkOn(List<Integer> list) {
            for (int i = 0, size = list.size(); i < size; i++) {
                if (!mStates[list.get(i) - 1]) {
                    return false;
                }
            }
            return true;
        }

        public boolean checkOff(List<Integer> list) {
            for (int i = 0, size = list.size(); i < size; i++) {
                if (mStates[list.get(i) - 1]) {
                    return false;
                }
            }
            return true;
        }

        public boolean isSame(LampsState state) {
            if (n != state.getN()) {
                return false;
            }
            boolean[] states = state.getStates();
            for (int i = 0; i < n; i++) {
                if (mStates[i] != states[i]) {
                    return false;
                }
            }
            return true;
        }

        public boolean[] switch1() {
            boolean[] switchedStates = new boolean[n];
            for (int i = 0; i < n; i++) {
                switchedStates[i] = !mStates[i];
            }
            return switchedStates;
        }

        public boolean[] switch2() {
            boolean[] switchedStates = new boolean[n];
            for (int i = 0; i < n; i++) {
                switchedStates[i] = i % 2 == 0 ? !mStates[i] : mStates[i];
            }
            return switchedStates;
        }

        public boolean[] switch3() {
            boolean[] switchedStates = new boolean[n];
            for (int i = 0; i < n; i++) {
                switchedStates[i] = i % 2 == 1 ? !mStates[i] : mStates[i];
            }
            return switchedStates;
        }

        public boolean[] switch4() {
            boolean[] switchedStates = new boolean[n];
            for (int i = 0; i < n; i++) {
                switchedStates[i] = i % 3 == 0 ? !mStates[i] : mStates[i];
            }
            return switchedStates;
        }

        @Override
        public int compareTo(LampsState lampsState) {
            if (n < lampsState.getN()) {
                return -1;
            } else if (n > lampsState.getN()) {
                return 1;
            }
            boolean[] states = lampsState.getStates();
            for (int i = 0; i < n; i++) {
                if (mStates[i] != states[i]) {
                    return mStates[i] ? 1 : -1;
                }
            }
            return 0;
        }
    }
}
