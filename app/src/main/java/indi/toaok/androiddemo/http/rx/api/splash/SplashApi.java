package indi.toaok.androiddemo.http.rx.api.splash;


import java.util.Map;

import indi.toaok.androiddemo.http.rx.RequestManager;
import indi.toaok.androiddemo.http.rx.RxSchedulers;
import indi.toaok.androiddemo.http.rx.RxSubscriberHelper;
import indi.toaok.androiddemo.http.rx.api.BaseRequestBody;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class SplashApi {

    interface Api {
        @FormUrlEncoded
        @POST("/getImage")
        Observable<SplashResultBean> getImages(@FieldMap Map<String, Object> params);
    }


    private Api api;

    public SplashApi() {
        api = RequestManager.getRequest(Api.class);
    }

    public void getImages(SplashRequestBean requestBean, final RxSubscriberHelper<SplashResultBean> subscriberHelper) {
        api.getImages(new BaseRequestBody(requestBean).toRequestBody())
                .compose(RxSchedulers.rxSchedulerHelper())
                .compose(RxSchedulers.handleResult())
                .subscribe(subscriberHelper);
    }
}

