/*
ID: zhm05251
LANG: JAVA
TASK: milk3
*/

import java.io.*;
import java.util.*;

public class milk3 {
    private static final String INPUT_FILE_NAME = "milk3.in";
    private static final String OUTPUT_FILE_NAME = "milk3.out";

    private static final int MAX_BUCKET_CAPACITY = 20;
    private static boolean[][][] mStatus =
            new boolean[MAX_BUCKET_CAPACITY + 1][MAX_BUCKET_CAPACITY + 1][MAX_BUCKET_CAPACITY + 1];

    private static List<BucketStatus> mBucketStatusList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        BucketStatus.aCapacity = Integer.parseInt(st.nextToken());
        BucketStatus.bCapacity = Integer.parseInt(st.nextToken());
        BucketStatus.cCapacity = Integer.parseInt(st.nextToken());

        mStatus[0][0][BucketStatus.cCapacity] = true;
        mBucketStatusList.add(new BucketStatus(0, 0, BucketStatus.cCapacity));

        int index = 0;
        while (index < mBucketStatusList.size()) {
            BucketStatus bucketStatus = mBucketStatusList.get(index);
            insert(bucketStatus.moveAtoB());
            insert(bucketStatus.moveAtoC());
            insert(bucketStatus.moveBtoA());
            insert(bucketStatus.moveBtoC());
            insert(bucketStatus.moveCtoA());
            insert(bucketStatus.moveCtoB());
            index++;
        }

        List<Integer> results = getResults(BucketStatus.bCapacity, BucketStatus.cCapacity);
        boolean first = true;
        for (int i = 0, size = results.size(); i < size; i++) {
            if (first) {
                first = false;
            } else {
                out.print(" ");
            }
            out.print(results.get(i));
        }
        out.println();
        out.close();
    }

    private static List<Integer> getResults(int b, int c) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= c; i++) {
            boolean flag = false;
            for (int j = 0; j <= b; j++) {
                if (mStatus[0][j][i]) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                list.add(i);
            }
        }
        return list;
    }

    private static void insert(BucketStatus status) {
        if (isAvailable(status)) {
            mBucketStatusList.add(status);
        }
    }

    private static boolean isAvailable(BucketStatus status) {
        if (status == null) {
            return false;
        }
        if (!mStatus[status.getA()][status.getB()][status.getC()]) {
            mStatus[status.getA()][status.getB()][status.getC()] = true;
            return true;
        }
        return false;
    }

    public static class BucketStatus {
        public static int aCapacity;
        public static int bCapacity;
        public static int cCapacity;

        public int a;
        public int b;
        public int c;

        public BucketStatus(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }

        public BucketStatus moveAtoB() {
            if (a == 0) {
                return null;
            }
            if (a + b <= bCapacity) {
                return new BucketStatus(0, a + b, c);
            }
            return new BucketStatus(a + b - bCapacity, bCapacity, c);
        }

        public BucketStatus moveAtoC() {
            if (a == 0) {
                return null;
            }
            if (a + c <= cCapacity) {
                return new BucketStatus(0, b, a + c);
            }
            return new BucketStatus(a + c - cCapacity, b, cCapacity);
        }

        public BucketStatus moveBtoA() {
            if (b == 0) {
                return null;
            }
            if (b + a <= aCapacity) {
                return new BucketStatus(b + a, 0, c);
            }
            return new BucketStatus(aCapacity, b + a - aCapacity, c);
        }

        public BucketStatus moveBtoC() {
            if (b == 0) {
                return null;
            }
            if (b + c <= cCapacity) {
                return new BucketStatus(a, 0, b + c);
            }
            return new BucketStatus(a, b + c - cCapacity, cCapacity);
        }

        public BucketStatus moveCtoA() {
            if (c == 0) {
                return null;
            }
            if (c + a <= aCapacity) {
                return new BucketStatus(c + a, b, 0);
            }
            return new BucketStatus(aCapacity, b, c + a - aCapacity);
        }

        public BucketStatus moveCtoB() {
            if (c == 0) {
                return null;
            }
            if (c + b <= bCapacity) {
                return new BucketStatus(a, c + b, 0);
            }
            return new BucketStatus(a, bCapacity, c + b - bCapacity);
        }

    }
}
