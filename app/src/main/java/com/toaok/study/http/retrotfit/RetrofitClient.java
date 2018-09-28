package com.toaok.study.http.retrotfit;

import com.toaok.utilcode.util.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Toaok
 * @version 1.0  2018/8/20.
 */
public class RetrofitClient {


    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(baseUrl)//设置baseURL,baseUrl 中的路径(path)必须以 / 结束
                .addConverterFactory(GsonConverterFactory.create())//通过GsonConverterFactory为Retrofit添加Gson支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//通过RxJavaCallAdapterFactory为Retrofit添加RxJava支持
                .client(getClient())//设置自定义的OkHttpClient
                .build();
        return builder.create(clazz);
    }

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    private static OkHttpClient getClient() {

        /*
         * 本地缓存
         * */
        File casheFile = new File(System.getProperty("user.dir"), "cache");
        Cache cache = new Cache(casheFile, 1024 * 1024);


        /**
         * 请求缓存策略
         */
        Interceptor mRewriteCacheControlInterceptor = getInterceptor();

        /*
         * SSL
         * */

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .cache(cache);


        return client.build();
    }

    /*************************缓存设置*********************/
   /*
    1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存
    */
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    protected static Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //判断网络是否可用
                if (NetworkUtils.isAvailableByPing()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response originalResponse = chain.proceed(request);

                if (NetworkUtils.isAvailableByPing()) {
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("pragma")
                            .build();
                } else {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
    }

}
