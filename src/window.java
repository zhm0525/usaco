/*
ID: zhm05251
LANG: JAVA
TASK: window
*/

import java.io.*;
import java.util.*;

public class window {

    private static final String INPUT_FILE_NAME = "window.in";
    private static final String OUTPUT_FILE_NAME = "window.out";

    private static List<Window> mWindows;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        mWindows = new ArrayList<>();
        String input = f.readLine();
        while (input != null) {
            char op = input.charAt(0);
            char id = input.charAt(2);
            if (op == 'w') {
                String[] sp = input.substring(1, input.length() - 1).split(",");
                int x1 = Integer.parseInt(sp[1]);
                int y1 = Integer.parseInt(sp[2]);
                int x2 = Integer.parseInt(sp[3]);
                int y2 = Integer.parseInt(sp[4]);

                if (x1 > x2) {
                    int xt = x1;
                    x1 = x2;
                    x2 = xt;
                }
                if (y1 > y2) {
                    int yt = y1;
                    y1 = y2;
                    y2 = yt;
                }
                Window window = new Window(id, x1, y1, x2, y2);
                mWindows.add(window);
            } else if (op == 't') {
                Window window = getWindow(id);
                if (window != null) {
                    window.bringToTop();
                }
            } else if (op == 'b') {
                Window window = getWindow(id);
                if (window != null) {
                    window.putOnBottom();
                }
            } else if (op == 'd') {
                Window window = getWindow(id);
                if (window != null) {
                    mWindows.remove(window);
                }
                mWindows.remove(window);
            } else if (op == 's') {
                Window window = getWindow(id);
                if (window != null) {
                    out.println(String.format("%.3f", window.show(mWindows)));
                }
            }
            input = f.readLine();
        }
        out.close();
    }

    private static Window getWindow(char id) {
        for (Window window : mWindows) {
            if (window.getId() == id) {
                return window;
            }
        }
        return null;
    }

    private static class Window {

        public static int MAX_HEIGHT = 0;
        public static int MIN_HEIGHT = 0;

        private char id;
        private int x1, y1, x2, y2;
        private int h;

        public Window(char id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.h = ++MAX_HEIGHT;
        }

        public Window(char id, int x1, int y1, int x2, int y2, int h) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.h = h;
        }

        public Window copy() {
            return new Window(id, x1, y1, x2, y2, h);
        }

        public char getId() {
            return this.id;
        }

        public int getHeight() {
            return this.h;
        }

        public double getArea() {
            return (x2 - x1) * (y2 - y1);
        }

        public int[] getXY() {
            return new int[]{x1, y1, x2, y2};
        }

        public void bringToTop() {
            this.h = ++MAX_HEIGHT;
        }

        public void putOnBottom() {
            this.h = --MIN_HEIGHT;
        }

        public void setInvisible() {
            this.x1 = 0;
            this.y1 = 0;
            this.x2 = 0;
            this.y2 = 0;
        }

        public double show(List<Window> list) {
            List<Window> vList = new ArrayList<>();
            vList.add(this.copy());

            for (Window window : list) {
                if (window.getId() == this.id) {
                    continue;
                }
                if (window.getHeight() < this.h) {
                    continue;
                }

                int[] xy = window.getXY();
                int x1 = xy[0];
                int y1 = xy[1];
                int x2 = xy[2];
                int y2 = xy[3];

                for (int i = 0, size = vList.size(); i < size; i++) {
                    Window vWindow = vList.get(i);
                    int[] vXY = vWindow.getXY();
                    int vx1 = vXY[0];
                    int vy1 = vXY[1];
                    int vx2 = vXY[2];
                    int vy2 = vXY[3];

                    if (x2 <= vx1 || vx2 <= x1 ||
                            y2 <= vy1 || vy2 <= y1) {
                        continue;
                    }

                    if (x1 <= vx1) {
                        if (x2 < vx2) {
                            vList.add(new Window(id, x2, vy1, vx2, vy2, h));
                            vx2 = x2;
                        }
                    } else {
                        if (x2 < vx2) {
                            vList.add(new Window(id, vx1, vy1, x1, vy2, h));
                            vList.add(new Window(id, x2, vy1, vx2, vy2, h));
                            vx1 = x1;
                            vx2 = x2;
                        } else {
                            vList.add(new Window(id, vx1, vy1, x1, vy2, h));
                            vx1 = x1;
                        }
                    }

                    if (y1 <= vy1) {
                        if (y2 < vy2) {
                            vList.add(new Window(id, vx1, y2, vx2, vy2, h));
                        }
                    } else {
                        if (y2 < vy2) {
                            vList.add(new Window(id, vx1, vy1, vx2, y1, h));
                            vList.add(new Window(id, vx1, y2, vx2, vy2, h));
                        } else {
                            vList.add(new Window(id, vx1, vy1, vx2, y1, h));
                        }
                    }
                    vWindow.setInvisible();
                }
            }

            double s = 0d;
            for (Window window : vList) {
                s = s + window.getArea();
            }
            return 100d * s / getArea();
        }

    }
}
