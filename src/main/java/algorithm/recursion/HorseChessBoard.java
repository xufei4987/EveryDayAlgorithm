package algorithm.recursion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 马走棋盘（回溯算法）
 */
public class HorseChessBoard {
    private static final int ROW = 8;
    private static final int COL = 8;
    //标记棋盘是否被访问过
    private static boolean[] visited;
    //是否成功走完整个棋盘
    private static boolean finished;

    public static void main(String[] args) {
        int[][] chessBoard = new int[ROW][COL];
        visited = new boolean[ROW * COL];
        System.out.println("开始马踏棋盘。。。");
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, 0, 0, 1);
        long end = System.currentTimeMillis();
        for (int[] row : chessBoard) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("耗时：" + (end - start) + "ms");
    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessBoard 棋盘
     * @param row        当前位置的行
     * @param col        当前位置的列
     * @param step       当前是第几步
     */
    private static void traversalChessBoard(int[][] chessBoard, int row, int col, int step) {
        chessBoard[row][col] = step;
        visited[row * ROW + col] = true;
        ArrayList<Point> nextPoints = next(new Point(col, row));
        //利用贪心算法进行优化，每次都走下一步的下一步可走的最少的
        nextPoints.sort(Comparator.comparingInt(p -> next(p).size()));
        while (!nextPoints.isEmpty()) {
            Point p = nextPoints.remove(0);
            //是否访问过
            if (!visited[p.y * ROW + p.x]) {
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        //没有完成任务就将当前位置的状态还原 方便进行回溯
        if (step < ROW * COL && !finished) {
            chessBoard[row][col] = 0;
            visited[row * ROW + col] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 传入当前位置，找到下一步可以走的位置
     *
     * @param point
     * @return
     */
    private static ArrayList<Point> next(Point point) {
        ArrayList<Point> nextPoints = new ArrayList<>();
        Point p = new Point();
        if ((p.x = point.x - 2) >= 0 && (p.y = point.y - 1) >= 0) {
            nextPoints.add(new Point(p));
        }
        if ((p.x = point.x - 2) >= 0 && (p.y = point.y + 1) < ROW) {
            nextPoints.add(new Point(p));
        }
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y - 2) >= 0) {
            nextPoints.add(new Point(p));
        }
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y + 2) < ROW) {
            nextPoints.add(new Point(p));
        }
        if ((p.x = point.x + 2) < ROW && (p.y = point.y - 1) >= 0) {
            nextPoints.add(new Point(p));
        }
        if ((p.x = point.x + 2) < ROW && (p.y = point.y + 1) < ROW) {
            nextPoints.add(new Point(p));
        }
        if ((p.x = point.x + 1) < ROW && (p.y = point.y - 2) >= 0) {
            nextPoints.add(new Point(p));
        }
        if ((p.x = point.x + 1) < ROW && (p.y = point.y + 2) < ROW) {
            nextPoints.add(new Point(p));
        }
        return nextPoints;
    }
}
