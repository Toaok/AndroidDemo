package com.toaok.study.base;

import com.toaok.themvp.databind.DataBinder;
import com.toaok.themvp.model.IModel;

/**
 * 基准DataBinder 所有在该项目中使用TheMVP框架的DataBinder都必须继承该抽象类
 *
 * @author Toaok
 * @version 1.0  2018/9/26.
 */
public abstract class BaseDataBinder<T extends BaseDelegate, D extends IModel> implements DataBinder<T, D> {
    //TODO Anything
}
