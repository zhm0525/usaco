/*
ID: zhm05251
LANG: JAVA
TASK: lgame
*/

import java.io.*;
import java.util.*;

public class lgame {

    private static final String DICT_FILE_NAME = "lgame.dict";
    private static final String INPUT_FILE_NAME = "lgame.in";
    private static final String OUTPUT_FILE_NAME = "lgame.out";

    public static void main(String[] args) throws IOException {
        BufferedReader df = new BufferedReader(new FileReader(DICT_FILE_NAME));
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        List<Word> mDictList = new ArrayList<>();
        String dictInput = df.readLine();
        while (dictInput != null && !dictInput.equals(".")) {
            Word word = new Word(dictInput);
            mDictList.add(word);
            dictInput = df.readLine();
        }

        String input = f.readLine();
        Word targetWord = new Word(input);

        List<Word> mFilterList = new ArrayList<>();
        for (Word word : mDictList) {
            if (targetWord.isCovered(word)) {
                mFilterList.add(word);
            }
        }

        int maxPoint = 0;
        List<String> answerList = new ArrayList<>();
        for (int i = 0, size = mFilterList.size(); i < size; i++) {
            Word word1 = mFilterList.get(i);
            int point = word1.getPoint();
            if (point > maxPoint) {
                maxPoint = point;
                answerList.clear();
                answerList.add(word1.getContent());
            } else if (point == maxPoint) {
                answerList.add(word1.getContent());
            }

            for (int j = i + 1; j < size; j++) {
                Word word2 = mFilterList.get(j);
                if (targetWord.isCovered(word1, word2)) {
                    int point2 = word1.getPoint() + word2.getPoint();
                    if (point2 > maxPoint) {
                        maxPoint = point2;
                        answerList.clear();
                        answerList.add(word1.getContent() + " " + word2.getContent());
                    } else if (point2 == maxPoint) {
                        answerList.add(word1.getContent() + " " + word2.getContent());
                    }
                }
            }
        }

        out.println(String.valueOf(maxPoint));
        for (String answer : answerList) {
            out.println(answer);
        }
        out.close();
    }

    private static final class Word {
        private static final int LETTER_COUNT = 26;
        private static final int[] POINTS =
                {2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};

        private String mContent;
        private int mPoint;
        private int[] mLetterCount;

        public Word(String content) {
            mContent = content;
            mPoint = getPoint(content);
            mLetterCount = getLetterCount(content);
        }

        private int getPoint(String content) {
            int point = 0;
            for (int i = 0, length = content.length(); i < length; i++) {
                int wi = content.charAt(i) - 'a';
                if (0 <= wi && wi < LETTER_COUNT) {
                    point += POINTS[wi];
                }
            }
            return point;
        }

        private int[] getLetterCount(String content) {
            int[] count = new int[LETTER_COUNT];
            for (int i = 0, length = content.length(); i < length; i++) {
                int wi = content.charAt(i) - 'a';
                if (0 <= wi && wi < LETTER_COUNT) {
                    count[wi]++;
                }
            }
            return count;
        }

        public boolean isCovered(Word word) {
            int[] letterCount = word.getLetterCount();
            for (int i = 0; i < LETTER_COUNT; i++) {
                if (mLetterCount[i] < letterCount[i]) {
                    return false;
                }
            }
            return true;
        }

        public boolean isCovered(Word word1, Word word2) {
            if (word1.getContent().length() + word2.getContent().length() >
                    mContent.length()) {
                return false;
            }
            int[] letterCount1 = word1.getLetterCount();
            int[] letterCount2 = word2.getLetterCount();
            for (int i = 0; i < LETTER_COUNT; i++) {
                if (mLetterCount[i] < letterCount1[i] + letterCount2[i]) {
                    return false;
                }
            }
            return true;
        }

        public String getContent() {
            return mContent;
        }

        public int getPoint() {
            return mPoint;
        }

        public int[] getLetterCount() {
            return mLetterCount;
        }
    }
}
