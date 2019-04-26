package indi.toaok.androiddemo.module.comment.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import indi.toaok.androiddemo.R;

public class LoadingDialog extends AlertDialog {

    private Context context;
    private String tips;
    private DialogCancelListener dialogCancelListener;

    public static DialogDismissListener createLoadingDialog(final Context context, DialogCancelListener dialogCancelListener, String tips) {
        if(context == null) {
            return null;
        }
        if(context instanceof Activity && ((Activity)context).isFinishing()) {
            return null;
        }
        final LoadingDialog customLoadingDialog = new LoadingDialog(context, tips);
        customLoadingDialog.setDialogCancelListener(dialogCancelListener);
        customLoadingDialog.setCanceledOnTouchOutside(false);
        customLoadingDialog.setCancelable(true);
        customLoadingDialog.show();
        return () -> customLoadingDialog.dismiss();
    }


    private LoadingDialog(Context context) {
        super(context, R.style.TransparentDialogStyle);
        this.context = context;
    }

    private LoadingDialog(Context context, String tips) {
        super(context, R.style.TransparentDialogStyle);
        this.context = context;
        this.tips = tips;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.view_progress_dialog, null);

        if (TextUtils.isEmpty(tips) == false) {
            TextView tvTips = (TextView) view.findViewById(R.id.tv_message);
            tvTips.setVisibility(View.VISIBLE);
            tvTips.setText(tips);
        }

        Window dialogWindow = getWindow();
        dialogWindow.setContentView(view);
        this.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (dialogCancelListener != null) {
                    dialogCancelListener.onCancel();
                }
            }
        });
    }

    public DialogCancelListener getDialogCancelListener() {
        return dialogCancelListener;
    }

    public void setDialogCancelListener(DialogCancelListener dialogCancelListener) {
        this.dialogCancelListener = dialogCancelListener;
    }


    public interface DialogDismissListener {
        void onDismiss();
    }

    public interface DialogCancelListener {
        void onCancel();
    }
}