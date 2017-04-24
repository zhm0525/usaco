/*
ID: zhm05251
LANG: JAVA
TASK: fence6
*/

import java.io.*;
import java.util.*;

public class fence6 {

    private static final String INPUT_FILE_NAME = "fence6.in";
    private static final String OUTPUT_FILE_NAME = "fence6.out";
    private static final int MAX_VALUE = Integer.MAX_VALUE / 3;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Fence> mFenceList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int number = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            int leftN = Integer.parseInt(st.nextToken());
            int rightN = Integer.parseInt(st.nextToken());
            Fence fence = new Fence(number, length);

            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < leftN; j++) {
                fence.addLeftEndConnect(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < rightN; j++) {
                fence.addRightEndConnect(Integer.parseInt(st.nextToken()));
            }
            mFenceList.add(fence);
        }
        int index = 0;
        for (Fence fence : mFenceList) {
            if (fence.getLeftEndIndex() < 0) {
                fence.setLeftEndIndex(index++);
            }
            int li = fence.getLeftEndIndex();
            for (Integer number : fence.getLeftEndConnectList()) {
                for (Fence fence1 : mFenceList) {
                    if (fence1.getNumber() == number) {
                        if (fence1.isLeftEndConnectContains(fence.getNumber())) {
                            if (fence1.getLeftEndIndex() < 0) {
                                fence1.setLeftEndIndex(li);
                            } else if (fence1.getLeftEndIndex() != li) {
                                // input error
                            }
                        }
                        if (fence1.isRightEndConnectContains(fence.getNumber())) {
                            if (fence1.getRightEndIndex() < 0) {
                                fence1.setRightEndIndex(li);
                            } else if (fence1.getRightEndIndex() != li) {
                                // input error
                            }
                        }
                    }
                }
            }

            if (fence.getRightEndIndex() < 0) {
                fence.setRightEndIndex(index++);
            }
            int ri = fence.getRightEndIndex();
            for (Integer number : fence.getRightEndConnectList()) {
                for (Fence fence1 : mFenceList) {
                    if (fence1.getNumber() == number) {
                        if (fence1.isLeftEndConnectContains(fence.getNumber())) {
                            if (fence1.getLeftEndIndex() < 0) {
                                fence1.setLeftEndIndex(ri);
                            } else if (fence1.getLeftEndIndex() != ri) {
                                // input error
                            }
                        }
                        if (fence1.isRightEndConnectContains(fence.getNumber())) {
                            if (fence1.getRightEndIndex() < 0) {
                                fence1.setRightEndIndex(ri);
                            } else if (fence1.getRightEndIndex() != ri) {
                                // input error
                            }
                        }
                    }
                }
            }
        }
        int[][] map = new int[index][index];
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index; j++) {
                map[i][j] = i == j ? 0 : MAX_VALUE;
            }
        }
        for (Fence fence : mFenceList) {
            int li = fence.getLeftEndIndex();
            int ri = fence.getRightEndIndex();
            int l = fence.getLength();
            map[li][ri] = l;
            map[ri][li] = l;
        }

        int s = MAX_VALUE;
        for (Fence fence : mFenceList) {
            int li = fence.getLeftEndIndex();
            int ri = fence.getRightEndIndex();
            map[li][ri] = MAX_VALUE;
            map[ri][li] = MAX_VALUE;

            int[] d = new int[index];
            boolean[] b = new boolean[index];
            for (int i = 0; i < index; i++) {
                d[i] = map[li][i];
            }
            b[li] = true;

            for (int i = 0; i < index - 1; i++) {
                int t = -1;
                for (int j = 0; j < index; j++) {
                    if (!b[j]) {
                        if (t < 0 || d[j] < d[t]) {
                            t = j;
                        }
                    }
                }
                if (t >= 0) {
                    b[t] = true;
                    for (int j = 0; j < index; j++) {
                        if (d[t] + map[t][j] < d[j]) {
                            d[j] = d[t] + map[t][j];
                        }
                    }
                }
            }


            int l = fence.getLength();
            if (d[ri] + l < s) {
                s = d[ri] + l;
            }
            map[li][ri] = l;
            map[ri][li] = l;
        }


        out.println(String.valueOf(s));
        out.close();
    }

    private static class Fence {

        private int mNumber;
        private int mLength;
        private int mLeftEndIndex;
        private int mRightEndIndex;
        private List<Integer> mLeftEndConnectList;
        private List<Integer> mRightEndConnectList;

        public Fence(int number, int length) {
            mNumber = number;
            mLength = length;
            mLeftEndIndex = -1;
            mRightEndIndex = -1;
            mLeftEndConnectList = new ArrayList<>();
            mRightEndConnectList = new ArrayList<>();
        }

        public int getNumber() {
            return mNumber;
        }

        public int getLength() {
            return mLength;
        }

        public void addLeftEndConnect(int number) {
            mLeftEndConnectList.add(number);
        }

        public void addRightEndConnect(int number) {
            mRightEndConnectList.add(number);
        }

        public List<Integer> getLeftEndConnectList() {
            return mLeftEndConnectList;
        }

        public List<Integer> getRightEndConnectList() {
            return mRightEndConnectList;
        }

        public boolean isLeftEndConnectContains(int number) {
            return mLeftEndConnectList.contains(number);
        }

        public boolean isRightEndConnectContains(int number) {
            return mRightEndConnectList.contains(number);
        }

        public void setLeftEndIndex(int index) {
            mLeftEndIndex = index;
        }

        public void setRightEndIndex(int index) {
            mRightEndIndex = index;
        }

        public int getLeftEndIndex() {
            return mLeftEndIndex;
        }

        public int getRightEndIndex() {
            return mRightEndIndex;
        }


    }

}
