package indi.toaok.androiddemo.http.rx;

import android.util.Log;

import indi.toaok.androiddemo.http.exception.ServerException;
import indi.toaok.androiddemo.http.rx.api.IResponse;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class RxSchedulers {

    /**
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> handleResult() {
        return httpResponseObservable -> httpResponseObservable.flatMap((Function<T, Observable<T>>) response -> {
            if (response instanceof IResponse) {
                Log.d("reuqestInfo", response.toString());
                if (((IResponse) response).isSuccess()) {
                    return createData(response);
                } else {
                    return Observable.error(new ServerException(((IResponse) response).getCode(), ((IResponse) response).getMessage(), response));
                }
            }
            return createData(response);
        });
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
