package indi.toaok.androiddemo.http.rx.api;

import java.util.Map;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public interface IRequestBody<T> {
    Map toRequestBody();
}
