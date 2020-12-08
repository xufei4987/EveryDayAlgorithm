package leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定一个会议时间安排的数组，每个会议都会包括开始时间和结束时间,为了避免会议室冲突，
 * 同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室才能满足这些会议的安排
 */
public class P253MinMeetingRooms {
    /**
     * 双指针解法：
     * 对开始时间和结束时间分开排序
     * 开始时间大于等于结束时间 则会议室可以重复使用，否则需要增加会议室
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        int rooms = 0;
        int endIdx = 0;
        for (int i = 0; i < startTimes.length; i++) {
            if (startTimes[i] < endTimes[endIdx]){ //需要重新开一个会议室
                rooms++;
            } else { //可以重复利用以前的会议室
                endIdx++;
            }
        }
        return rooms;
    }

    /**
     * 利用最小堆进行求解，堆内存放着会议的结束时间
     * @param intervals
     * @return
     */
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals,(i1,i2)->i1[0] - i2[0]);
        //默认就是最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < minHeap.peek()){ //开始时间小于当前最早会议结束时间，将会议结束时间加入到堆中
                minHeap.offer(intervals[i][1]);
            } else { //开始时间大于等于当前最早会议结束时间，会议可以重复使用，移除堆顶（更新该会议室的结束时间）
                minHeap.poll();
                minHeap.offer(intervals[i][1]);
            }
        }
        return minHeap.size();
    }

    public static void main(String[] args) {
        P253MinMeetingRooms p253MinMeetingRooms = new P253MinMeetingRooms();
        System.out.println(p253MinMeetingRooms.minMeetingRooms1(new int[][]{{1, 3}, {2, 5}, {6, 7}}));
    }
}
