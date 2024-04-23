import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Point;

class Main {
    static int WIDTH = 1280, HEIGHT = 720;
    static int SCALE = 3;
    static int tps = 20;
    static int fps = 60;
    static boolean isRunning = true;
    static String state = "Paused";
    static int cellSize = 10;
    static BufferStrategy bs;
    static Graphics g;
    static JFrame frame = new JFrame();
    static Canvas canvas = new Canvas();
    static Inputhandler input = new Inputhandler();
    static Grid grid = new Grid(1920/cellSize, 1080/cellSize, cellSize);

    public static void main(String[] args) {
        canvas.addKeyListener(input);
        canvas.addMouseListener(input);
        canvas.addMouseWheelListener(input);
        canvas.addMouseMotionListener(input);
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(canvas);
        frame.setTitle("Game of Life");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();

        long old_tps = System.currentTimeMillis();
        long old_fps = old_tps;

        while(isRunning) {
            if(System.currentTimeMillis() - old_tps >= 1000/tps) {
                tick();
                old_tps = System.currentTimeMillis();
            }

            if(System.currentTimeMillis() - old_fps >= 1000/fps) {
                render();
                old_fps = System.currentTimeMillis();
            } 
        }
    }

    public static void showText(String s) {
        System.out.println(s);
    }

    public static void undieCell(Point point) {
        int x = -1;
        int y = -1;

        for(int i = 0; i < grid.x; i++) {
            for(int j = 0; j < grid.y; j++) {
                if(point.getX() >= i*grid.size && point.getX() <= (i+1)*grid.size && point.getY() >= j*grid.size && point.getY() <= (j+1)*grid.size) {
                    x = i;
                    y = j;
                    break;
                }
            }

            if(x != -1) {
                break;
            }
        }

        grid.grid[x][y] = true;
    }

    public static void killCell(Point point) {
        int x = -1;
        int y = -1;

        for(int i = 0; i < grid.x; i++) {
            for(int j = 0; j < grid.y; j++) {
                if(point.getX() >= i*grid.size && point.getX() <= (i+1)*grid.size && point.getY() >= j*grid.size && point.getY() <= (j+1)*grid.size) {
                    x = i;
                    y = j;
                    break;
                }
            }

            if(x != -1) {
                break;
            }
        }

        grid.grid[x][y] = false;
    }

    public static void tick() {
        if(state == "Running") {
            grid.tick();
        }
    }

    public static void render() {
        bs = canvas.getBufferStrategy();

        if(bs == null) {
            canvas.createBufferStrategy(3);
            bs = canvas.getBufferStrategy();
        }

        g = bs.getDrawGraphics();

        g.setColor(Color.RED);
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        grid.render();

        if(state == "Paused") {
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(state, 0, 18);
        }

        bs.show();
    }
}