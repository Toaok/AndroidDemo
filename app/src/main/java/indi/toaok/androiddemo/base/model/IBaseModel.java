package indi.toaok.androiddemo.base.model;

import indi.toaok.themvp.model.IModel;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public interface IBaseModel extends IModel {
    String getMessage();

    void setMessage(String message);
}
