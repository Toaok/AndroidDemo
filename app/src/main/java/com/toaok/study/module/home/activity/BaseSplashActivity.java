package com.toaok.study.module.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.toaok.study.BuildConfig;
import com.toaok.study.model.vo.SplashBean;
import com.toaok.study.module.home.view.SplashDelegate;
import com.toaok.themvp.databind.DataBindActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseSplashActivity<T extends SplashDelegate> extends DataBindActivity {

    private ArrayList<String> images;

    protected SplashBean mSplashBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();

    }


    void init() {
        images = new ArrayList<>();
        try {
            Document document = Jsoup.connect(BuildConfig.SPLAH_URL).get();
            Elements imgs = document.select("div#article.article>div.article-content>div.img-container>img.large");

            Iterator<Element> iterator = imgs.iterator();
            while (iterator.hasNext()) {
                String src = iterator.next().attr("src");
                if (!TextUtils.isEmpty(src)) {
                    images.add(src);
                    Log.i("image-src", src);
                }
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (images != null) {
                        if (mSplashBean == null) {
                            mSplashBean = new SplashBean(images.get((int) (Math.random() * (images.size()))));
                        }
                        mSplashBean.setUrl(images.get((int) (Math.random() * (images.size()))));
                        notifyModelChanged(mSplashBean);
                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            MainActivity.startActivity(BaseSplashActivity.this);
                        }
                    }).start();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
