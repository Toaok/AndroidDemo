package indi.toaok.androiddemo.http.rx;

import android.content.Context;

import indi.toaok.androiddemo.module.comment.widget.LoadingDialog;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class DialogHelper {
    private static DialogHelper sInstance;

    private LoadingDialog.DialogDismissListener dialogDismissListener;

    private DialogHelper() {
    }

    public synchronized static DialogHelper getInstance() {
        if (sInstance == null) {
            sInstance = new DialogHelper();
        }
        return sInstance;
    }

    public void showLodingDialog(Context context) {
        if (dialogDismissListener != null) {
            dialogDismissListener.onDismiss();
        }
        dialogDismissListener = LoadingDialog.createLoadingDialog(context, null,"");
    }

    public void dissLodingDialog() {
        if(dialogDismissListener != null) {
            dialogDismissListener.onDismiss();
        }
    }
}
