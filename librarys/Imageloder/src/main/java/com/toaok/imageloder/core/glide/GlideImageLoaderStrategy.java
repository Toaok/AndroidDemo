package com.toaok.imageloder.core.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.toaok.imageloder.R;
import com.toaok.imageloder.core.BaseImageLoaderStrategy;
import com.toaok.imageloder.core.ImageLoaderConfig;
import com.toaok.imageloder.core.LoaderListener;
import com.toaok.imageloder.core.utils.ImageUtil;

import java.io.File;

/**
 * Glide 加载器，当前工程使用Glide 作为图片Loader
 * 全局网络图片加载 请使用 {@link com.toaok.imageloder.core.ImageLoader}
 * <p>
 * 如需要使用其他图片加载引擎 ， 请自定义ImageLoaderStrategy 并指定 {@link com.toaok.imageloder.core.ImageLoader#getLoader()}
 * Created by sj on 10/17/16.
 */

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    // default config
    public final static ImageLoaderConfig defaultConfigBuilder = new ImageLoaderConfig.Builder().
            cropType(ImageLoaderConfig.CENTER_CROP)
            .asBitmap(true)
            .placeHolderResId(R.drawable.bg_image_placeholder)
            .errorResId(R.drawable.bg_image_placeholder)
            .diskCacheStrategy(ImageLoaderConfig.MDiskCacheStrategy.DATA)
            .loadPriority(ImageLoaderConfig.LoadPriority.HIGH)
            .build();

    private GlideImageLoaderStrategy() {
    }

    public static GlideImageLoaderStrategy getInstance() {
        return LazyHolder.strategy;
    }

    private static class LazyHolder {
        public static final GlideImageLoaderStrategy strategy = new GlideImageLoaderStrategy();
    }

    @SuppressLint("CheckResult")
    private static void setListener(RequestBuilder request, final LoaderListener listener) {
        request.listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                listener.onError();
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                listener.onSuccess(resource);
                return false;
            }
        });
    }

    @Override
    public void loadImage(final ImageView imageView, String imageUrl, ImageLoaderConfig config, LoaderListener listener) {
        final Context context = imageView.getContext();
        if (null == config) {
            config = defaultConfigBuilder;
        }
        try {
            RequestBuilder builder;
            RequestOptions options;

            options = new RequestOptions()
                    .placeholder(defaultConfigBuilder.getPlaceHolderResId())
                    .error(defaultConfigBuilder.getErrorResId())
                    .priority(Priority.HIGH);
            if (config.isAsGif()) {
                //gif类型
                RequestBuilder<GifDrawable> request = Glide.with(context).asGif().load(imageUrl);
                if (config.getCropType() == ImageLoaderConfig.CENTER_CROP) {
                    options.centerCrop();
                } else {
                    options.fitCenter();
                }
                builder = request;
            } else if (config.isAsBitmap()) {
                //bitmap 类型
                RequestBuilder<Bitmap> request = Glide.with(context).asBitmap().load(imageUrl);
                if (config.getCropType() == ImageLoaderConfig.CENTER_CROP) {
                    options.centerCrop();
                } else if (config.getCropType() == ImageLoaderConfig.CENTER_INSIDE) {//实现centerInside效果(imageview scaletype设置后，此处要使用request.dontTransform设置才生效)
                    options.dontTransform();
                } else {
                    options.fitCenter();
                }
                builder = request;
            } else if (config.isCrossFade()) {
                // TODO 渐入渐出动画
                RequestBuilder request = Glide.with(context).load(imageUrl);
                if (config.getCropType() == ImageLoaderConfig.CENTER_CROP) {
                    options.centerCrop();
                } else {
                    options.fitCenter();
                }
                builder = request;
            } else {
                RequestBuilder request = Glide.with(context).load(imageUrl);
                if (config.getCropType() == ImageLoaderConfig.CENTER_CROP) {
                    options.centerCrop();
                } else {
                    options.fitCenter();
                }
                builder = request;
            }

            options.diskCacheStrategy(config.getDiskCacheStrategy().getStrategy())
                    .skipMemoryCache(config.isSkipMemoryCache())
                    .priority(config.getLoadPriority().getPriority())
                    .dontAnimate();
            //缓存设置
            if (null != config.getDisplaySize()) {
                options.override(config.getDisplaySize().getWidth(), config.getDisplaySize().getHeight());
            }
            //设置监听器
            if (null != listener) {
                setListener(builder, listener);
            }
            if (0 != config.getErrorResId()) {
                options.error(config.getErrorResId());
            }
            if (0 != config.getPlaceHolderResId()) {
                options.placeholder(config.getPlaceHolderResId());
            }

            //将操作设置到builder中
            builder.apply(options);


            // 圆角处理
            if (config.isCropCircle()) {
                builder.into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(360);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
            } else {
                Log.i("glide","成功");
                builder.into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("glide","异常"+e.getMessage());
            if (imageView != null && config != null && config.getErrorResId() != 0) {
                imageView.setImageResource(config.getErrorResId());
            }
        }
    }

    /**
     * @param imageView
     * @param imageUrl
     */
    @Override
    public void loadImage(ImageView imageView, String imageUrl) {
        loadImage(imageView, imageUrl, defaultConfigBuilder, null);
    }

    @Override
    public void loadImage(ImageView imageView, String imageUrl, String thumbnailUrl) {

    }

    @Override
    public void loadRoundedImage(ImageView imageView, String imageUrl) {
        ImageLoaderConfig roundConfigBuilder = new ImageLoaderConfig.Builder()
                .cropType(ImageLoaderConfig.CENTER_CROP)
                .asBitmap(true)
                .cropCircle(true)
                .placeHolderResId(R.drawable.bg_image_placeholder_round)
                .errorResId(R.drawable.bg_image_placeholder)
                .diskCacheStrategy(ImageLoaderConfig.MDiskCacheStrategy.DATA)
                .loadPriority(ImageLoaderConfig.LoadPriority.HIGH).build();
        loadImage(imageView, imageUrl, roundConfigBuilder, null);
    }

    @Override
    public void loadGif(ImageView imageView, String imageUrl) {
    }

    @SuppressLint("CheckResult")
    @Override
    public void downloadImage(final Context context, final Handler handler, final String uri, final String savePath, final String name, final boolean isInsertMedia) {
        Glide.with(context.getApplicationContext())
                .downloadOnly()
                .listener(new RequestListener<File>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                        if (handler != null) {
                            handler.sendEmptyMessage(ImageUtil.SAVE_IMAGE_FAIL);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                        ImageUtil.saveImageAndRefresh(context, handler, BitmapFactory.decodeFile(resource.getPath()), uri, savePath, name, isInsertMedia);
                        return false;
                    }
                })
                .load(uri);
    }

    @SuppressLint("CheckResult")
    @Override
    public void downloadImage(final Context context, final Handler handler, final String uri,
                              final String savePath, final String name, final boolean isInsertMedia,
                              final LoaderListener loaderListener) {
        Glide.with(context.getApplicationContext())
                .downloadOnly().listener(new RequestListener<File>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                Bitmap bitmap = BitmapFactory.decodeFile(resource.getPath());
                ImageUtil.saveImageAndRefresh(context, handler, bitmap, uri, savePath, name, isInsertMedia);
                loaderListener.onSuccess(bitmap);
                return false;
            }
        }).load(uri);
    }
}
