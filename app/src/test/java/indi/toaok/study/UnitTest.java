package indi.toaok.study;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public static void test() {
        CountDownTimer countDownTimer = new CountDownTimer(10);
        Thread thread1=new Thread(countDownTimer);
        System.out.println(Thread.currentThread()+":线程已创建");
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread()+":等待3秒,线程开始执行");
            thread1.start();

            Thread.sleep(3000);
            System.out.println(Thread.currentThread()+":等待3秒,执行线程中断");
            thread1.interrupt();
            System.out.println(Thread.currentThread()+":等待3秒,线程重新开始执行");
            Thread.sleep(1000);
            thread1.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class CountDownTimer implements Runnable {

        public CountDownTimer(int time) {
            this.time = time * 1000;
            remaainingTime = this.time;
            System.out.println("执行时间" + this.time);
        }

        long time;
        //毫秒级
        long startTime = -1;

        //剩余时间
        long remaainingTime = -1;

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            System.out.println("开始时间" + startTime);
            if (startTime > 0) {
                while (remaainingTime > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("I has interputed");
                        time=remaainingTime;
                        break;
                    }
                    remaainingTime = time - (System.currentTimeMillis() - startTime);
                    System.out.println(Thread.currentThread()+":剩余时间" + (remaainingTime+1000) / 1000);
                }
                if (remaainingTime <= 0) {
                    System.out.println("I can do somthing ");
                }
            }
        }
    }

    

}