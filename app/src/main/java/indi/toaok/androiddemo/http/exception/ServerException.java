package indi.toaok.androiddemo.http.exception;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class ServerException extends RuntimeException{

    public int code;
    public String message;

    public Object object;


    public ServerException(int code, String message) {
        this(code, message, null);
    }

    public ServerException(int code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }
}
