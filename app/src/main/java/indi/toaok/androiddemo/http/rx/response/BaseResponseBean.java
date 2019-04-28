package indi.toaok.androiddemo.http.rx.response;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class BaseResponseBean<T> implements IResponse {


    private static final int SECCESS_CODE=200;

    /**
     * code : 200
     * message : 成功!
     * result : ...
     */

    protected int code;
    protected String message;
    protected T result;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public boolean isSuccess() {
        return code==SECCESS_CODE;
    }

    public void setCode(int code) {
        this.code = code;
    }




    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResponseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
