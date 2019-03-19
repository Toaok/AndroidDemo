package indi.toaok.androiddemo.module.other.view;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.base.view.BaseDelegate;
import indi.toaok.androiddemo.module.other.widget.DWebView;

/**
 * @author Toaok
 * @version 1.0  2018/10/16.
 */
public class WebViewDelegate extends BaseDelegate {

    @BindView(R.id.wv_content)
    DWebView wvContent;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_web;
    }

    public void initWebView(final String url) {
        progressBar.setMax(100 * 10000);
        wvContent.setOnJWebViewProgressLinstener(new DWebView.OnJWebViewProgressLinstener() {
            @Override
            public void showLoading(int newProgress) {
                WebViewDelegate.this.showLoading(newProgress);
            }

            @Override
            public void cancelShowLoading() {
                WebViewDelegate.this.cancelShowLoading();
            }
        });
        wvContent.setDownloadListener((url1, userAgent, contentDisposition, mimetype, contentLength) -> downloadByBrowser(url1));
        wvContent.requestFocusFromTouch();
        wvContent.loadUrl(url);
        Log.d("url", url);
    }

    /*
     * 浏览器下载
     * */
    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        getActivity().startActivity(intent);
    }

    protected void showLoading(int newProgress) {
        progressBar.setProgress(newProgress * 10000);
        if (newProgress == 100) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    protected void cancelShowLoading() {
        progressBar.setVisibility(View.GONE);
    }


    public DWebView getWvContent() {
        return wvContent;
    }

    public void setWvContent(DWebView wvContent) {
        this.wvContent = wvContent;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void goBack() {
        if (wvContent.canGoBack()) {
            wvContent.goBack();
            return;
        }
        if (getActivity() != null)
            getActivity().finish();
    }
}
