package algorithm1.sorting;

import java.util.ArrayList;
import java.util.List;

public class ShellSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected boolean isStable() {
        return false;
    }

    @Override
    protected void sort() {
        //步长序列
        List<Integer> stepSequence = generateStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    /**
     * 分成多少列进行排序,底层利用插入排序进行排序
     *
     * @param step
     */
    private void sort(Integer step) {
        //对某一列进行排序
        for (int col = 0; col < step; col++) {
            //插入排序
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                E curVal = array[begin];
                while (cur >= step && compare(curVal, array[cur - step]) < 0) {
                    array[cur] = array[cur - step];
                    cur -= step;
                }
                array[cur] = curVal;
            }
        }

    }

    /**
     * 按照希尔的方式计算步长序列 n/2^k (k = 1、2、3...) 步长的最小值为1
     *
     * @return
     */
    private List<Integer> generateStepSequence() {
        ArrayList<Integer> stepSeq = new ArrayList<>();
        int step = array.length;
        while ((step = step >> 1) > 0) {
            stepSeq.add(step);
        }
        System.out.println(stepSeq);
        return stepSeq;
    }
}
