package indi.toaok.study;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import indi.toaok.androiddemo.api.vo.request.SplashRequestBean;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void test() {
        try {
            Map map=convertToMap(new SplashRequestBean());
            System.out.println(map);
        } catch (Exception e) {
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

    public static HashMap<String, Object> convertToMap(Object obj)
            throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());

            fields[i].setAccessible(accessFlag);
        }

        return map;
    }

}