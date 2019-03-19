package indi.toaok.androiddemo.module.base.bean;

import indi.toaok.themvp.model.IModel;
import lombok.Data;

/**
 * toolbar 数据
 * @author Toaok
 * @version 1.0  2018/10/8.
 */

@Data
public class ToolbarBean implements IModel {

    /**
     * 标题
     */
    private String titleText;

    /*
    * 右侧文字
    * */
    private String rightText;

    private String message;
}
