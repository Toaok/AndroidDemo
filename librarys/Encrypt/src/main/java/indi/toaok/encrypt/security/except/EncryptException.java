package indi.toaok.encrypt.security.except;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class EncryptException extends Exception {
    /**
     * -2  无法获取签名信息
     * -1  签名校验失败
     */
    int code;
    String detailMessage;

    public EncryptException(int code, String msg) {
        this.code = code;
        this.detailMessage = msg;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
