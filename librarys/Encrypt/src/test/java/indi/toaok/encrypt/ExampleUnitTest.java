package indi.toaok.encrypt;

import org.junit.Test;

import indi.toaok.ndk.Security;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
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