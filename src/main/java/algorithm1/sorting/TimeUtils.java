package algorithm1.sorting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void test(String taskName, Runnable task) {
        if (task == null) return;
        System.out.println(taskName);
        System.out.println("开始: " + fmt.format(new Date()));
        long begin = System.currentTimeMillis();
        task.run();
        long end = System.currentTimeMillis();
        System.out.println("结束: " + fmt.format(new Date()));
        double delta = (end - begin) / 1000.0;
        System.out.println("耗时：" + delta + "秒");
        System.out.println("---------------------");
    }
}
