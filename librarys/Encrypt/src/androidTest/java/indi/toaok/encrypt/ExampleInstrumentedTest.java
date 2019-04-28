package indi.toaok.encrypt;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.runner.AndroidJUnit4;
import indi.toaok.ndk.Security;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        String key="abcd";
        String str = "dzcx";
        String strEn = null;
        String strDe = null;
        try {
            strEn = Security.encryptWithKey(key,str);
            strDe = Security.decryptWithKey(key,strEn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("加密前:" + str);
        System.out.println("加密后:" + strEn);
        System.out.println("解密后:" + strDe);
    }
}
