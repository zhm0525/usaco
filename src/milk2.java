/*
ID: zhm05251
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {
    private static final String INPUT_FILE_NAME = "milk2.in";
    private static final String OUTPUT_FILE_NAME = "milk2.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            WorkTime workTime = new WorkTime(startTime, endTime);
            mWorkTimeList.add(workTime);
        }
        Collections.sort(mWorkTimeList);

        int mLongestContinuousTime = 0;
        int mLongestIdleTime = 0;

        int size = mWorkTimeList.size();
        if (size > 0) {
            int mStartInterval = mWorkTimeList.get(0).getStartTime();
            int mEndInterval = mWorkTimeList.get(0).getEndTime();

            for (int i = 1; i < size; i++) {
                int startTime = mWorkTimeList.get(i).getStartTime();
                int endTime = mWorkTimeList.get(i).getEndTime();
                if (mStartInterval <= startTime && endTime <= mEndInterval) {
                    continue;
                }
                if (startTime <= mEndInterval && mEndInterval <= endTime) {
                    mEndInterval = endTime;
                }
                if (mEndInterval < startTime) {
                    if (mEndInterval - mStartInterval > mLongestContinuousTime) {
                        mLongestContinuousTime = mEndInterval - mStartInterval;
                    }
                    if (startTime - mEndInterval > mLongestIdleTime) {
                        mLongestIdleTime = startTime - mEndInterval;
                    }
                    mStartInterval = startTime;
                    mEndInterval = endTime;
                }
            }
            if (mEndInterval - mStartInterval > mLongestContinuousTime) {
                mLongestContinuousTime = mEndInterval - mStartInterval;
            }
        }
        out.println(String.valueOf(mLongestContinuousTime) + " " + String.valueOf(mLongestIdleTime));
        out.close();
    }

    private static List<WorkTime> mWorkTimeList = new ArrayList<>();

    public static class WorkTime implements Comparable<WorkTime> {
        private int mStartTime;
        private int mEndTime;

        public WorkTime(int startTime, int endTime) {
            mStartTime = startTime;
            mEndTime = endTime;
        }

        public int getStartTime() {
            return mStartTime;
        }

        public int getEndTime() {
            return mEndTime;
        }

        @Override
        public int compareTo(WorkTime o) {
            if (mStartTime < o.getStartTime()) {
                return -1;
            } else if (mStartTime == o.getStartTime() &&
                    mEndTime < o.getEndTime()) {
                return -1;
            }
            return 1;
        }
    }

}
