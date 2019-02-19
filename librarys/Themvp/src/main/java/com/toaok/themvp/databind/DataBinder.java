package com.toaok.themvp.databind;


import com.toaok.themvp.model.IModel;
import com.toaok.themvp.view.IDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/9/7.
 */
public interface DataBinder<V extends IDelegate,D extends IModel>{

    /**
     *  将数据与View绑定，这样当数据改变的时候，框架就知道这个数据是和那个
     *  View绑定在一起的，就可以自动改变UI,当数据改变的时候，会回调本方法。
     *
     * @param viewDelegate
     * @param data
     */
    void viewBindModel(V viewDelegate, D data);
}
