package indi.toaok.encrypt.security;

import indi.toaok.encrypt.security.except.EncryptException;
import indi.toaok.ndk.Security;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class Salt{
    protected static String getSalt(String baseSalt) throws EncryptException {
        /**
         * 返回salt
         */
        return Security.getSalt(baseSalt);
    }
}
