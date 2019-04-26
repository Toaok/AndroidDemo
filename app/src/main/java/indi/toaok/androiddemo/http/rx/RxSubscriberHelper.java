package indi.toaok.androiddemo.http.rx;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public abstract class  RxSubscriberHelper<T> implements Observer<T> {
    public static class Builder {

        boolean isShowMessage = true;
        boolean isCheckPermission = true;
        boolean isShowLoad = false;
        boolean isShowDialog = false;

        public Builder setShowMessage(boolean showMessage) {
            isShowMessage = showMessage;
            return this;
        }

        public Builder setCheckPermission(boolean checkPermission) {
            isCheckPermission = checkPermission;
            return this;
        }

        public Builder setShowLoad(boolean showLoad) {
            isShowLoad = showLoad;
            return this;
        }

        public Builder setShowDialog(boolean showLoad) {
            isShowDialog = showLoad;
            return this;
        }
    }

    /**
     * 错误是否弹出错误toast，默认是{@link Builder#isShowMessage}
     */
    protected boolean isShowMessage = true;
    /**
     * 是否自动拦截权限类错误，默认是{@link Builder#isCheckPermission}
     */
    protected boolean isCheckPermission = true;
    /**
     * 是否显示加载，默认不显示{@link Builder#isShowLoad}
     */
    protected boolean isShowLoad = false;

    /**
     * 错误是否弹出弹框提示，默认是{@link Builder#isShowDialog}
     */
    protected boolean isShowDialog = false;


    protected final WeakReference<Context> context;


    public RxSubscriberHelper() {
        this(null, false);
    }

    public RxSubscriberHelper(Context context, boolean isShowLoad) {
        this(context, new Builder().setShowLoad(isShowLoad));
    }

    public RxSubscriberHelper(Context context, Builder builder) {
        this.context = context == null ? null : new WeakReference<>(context);
        if (builder != null) {
            isShowMessage = builder.isShowMessage;
            isCheckPermission = builder.isCheckPermission;
            isShowLoad = builder.isShowLoad;
            isShowDialog = builder.isShowDialog;
        }
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (isShowLoad) {
            onShowLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {

        }

    }

    @Override
    public void onComplete() {
        if (isShowLoad) {
            onDisssload();
        }
    }


    protected void onShowLoading() {
        if (context != null) {
            DialogHelper.getInstance().showLodingDialog(context.get());
        }
    }

    protected void onDisssload() {
        if (context != null) {
            DialogHelper.getInstance().dissLodingDialog();
        }
    }

}
