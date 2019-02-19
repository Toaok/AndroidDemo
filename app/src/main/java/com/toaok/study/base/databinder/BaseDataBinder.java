package com.toaok.study.base.databinder;import android.text.TextUtils;import com.toaok.study.base.view.BaseDelegate;import com.toaok.themvp.databind.DataBinder;import com.toaok.themvp.model.IModel;/** * 基准DataBinder 所有在该项目中使用TheMVP框架的DataBinder都必须继承该抽象类 * * @author Toaok * @version 1.0  2018/9/26. */public abstract class BaseDataBinder<V extends BaseDelegate, D extends IModel> implements DataBinder<V, D> {    @Override    public void viewBindModel(V viewDelegate, D data) {        if (!TextUtils.isEmpty(data.getMessage())) {            viewDelegate.toast(data.getMessage());        }    }}