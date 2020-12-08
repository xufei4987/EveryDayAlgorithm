package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 给定一个会议时间安排的数组，每个会议都会包括开始时间和结束时间，请判断一个人是否能够参加所有的会议
 * [[s1,e1],[s2,e2],.....,[sn,en]]
 */
public class P252CanAttendMeetings {
    boolean canAttendMeetings(int[][] intervals){
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals,(i1,i2) -> i1[0] - i2[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        P252CanAttendMeetings p252CanAttendMeetings = new P252CanAttendMeetings();
        System.out.println(p252CanAttendMeetings.canAttendMeetings(new int[][]{{1, 3}, {2, 5}, {6, 7}}));
    }
}
