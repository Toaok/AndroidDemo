package indi.toaok.androiddemo.http.rx.api;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public interface IResponse {

   int getCode();

   boolean isSuccess();

   String getMessage();
}
