package com.toaok.study.base.presenter.fragment;import com.toaok.study.base.view.BaseDelegate;import com.toaok.themvp.databind.DataBindFragment;import com.toaok.themvp.databind.DataBinder;/** * 基准BaseFragment 所有在该项目中使用TheMVP框架的BaseFragment都必须继承该抽象类 * * @author Toaok * @version 1.0  2018/9/27. */public abstract class BaseFragment<T extends BaseDelegate> extends DataBindFragment<T> {    /**     * 如果该Fragment需要进行数据绑定，必须重新该方法     *     * @return 返回数据绑定类     */    @Override    protected DataBinder getDataBinder() {        return null;    }    //TODO Anything    @Override    public void onDestroy() {        super.onDestroy();       /*       * huwei emui 8.0 memory leaks       * */       if(binder!=null){           binder=null;       }    }}