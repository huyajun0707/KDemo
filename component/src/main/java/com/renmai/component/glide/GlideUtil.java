package com.renmai.component.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.network.library.utils.LogUtil;
import com.renmai.component.constant.Constant;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-11-21 16:27
 * @depiction ： Glide工具类
 */

public class GlideUtil {

    private static GlideUtil mGlideUtil;

    private GlideUtil() {
        // cannot be instantiated
    }

    public static synchronized GlideUtil getInstance() {
        if (mGlideUtil == null) {
            mGlideUtil = new GlideUtil();
        }
        return mGlideUtil;
    }

    /**
     * Bitmap
     *
     * @param ctx
     * @param originalSource
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param baseTarget
     */
    public void with(Context ctx,
                     Object originalSource,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     BaseTarget baseTarget) {
        with(ctx,
                originalSource,
                true,
                null,
                placeHolderDrawable,
                errorDrawable,
                strategy,
                baseTarget);
    }

    /**
     * Bitmap
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param animationResourceId
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param baseTarget
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     int animationResourceId,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     BaseTarget baseTarget) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_BITMAP,
                0,
                false,
                false,
                null,
                true,
                animationResourceId,
                null,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                null,
                baseTarget);
    }

    /**
     * Bitmap
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param animator
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param baseTarget
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     ViewPropertyTransition.Animator animator,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     BaseTarget baseTarget) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_BITMAP,
                0,
                false,
                false,
                null,
                true,
                Constant.View.DEFAULT_SIZE,
                animator,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                null,
                baseTarget);
    }


    /**
     * Bitmap
     *
     * @param ctx
     * @param originalSource
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param view
     */
    public void with(Context ctx,
                     Object originalSource,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     ImageView view) {
        with(ctx,
                originalSource,
                true,
                null,
                placeHolderDrawable,
                errorDrawable,
                strategy,
                view);
    }

    /**
     * Bitmap
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param animationResourceId
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param view
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     int animationResourceId,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     ImageView view) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_BITMAP,
                0,
                false,
                false,
                null,
                true,
                animationResourceId,
                null,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                view,
                null);
    }

    /**
     * Bitmap
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param animator
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param view
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     ViewPropertyTransition.Animator animator,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     ImageView view) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_BITMAP,
                0,
                false,
                false,
                null,
                true,
                Constant.View.DEFAULT_RESOURCE,
                animator,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_FIT_CENTER,
                strategy,
                view,
                null);
    }

    /**
     * Gif
     *
     * @param ctx
     * @param originalSource
     * @param gifDisplayTime
     * @param hasGifDiskCacheStrategy
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param baseTarget
     */
    public void with(Context ctx,
                     Object originalSource,
                     int gifDisplayTime,
                     boolean hasGifDiskCacheStrategy,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     BaseTarget baseTarget) {
        with(ctx,
                originalSource,
                true,
                gifDisplayTime,
                hasGifDiskCacheStrategy,
                null,
                placeHolderDrawable,
                errorDrawable,
                strategy,
                baseTarget);
    }

    /**
     * Gif
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param gifDisplayTime
     * @param hasGifDiskCacheStrategy
     * @param animationResourceId
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param baseTarget
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     int gifDisplayTime,
                     boolean hasGifDiskCacheStrategy,
                     int animationResourceId,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     BaseTarget baseTarget) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_GIF,
                gifDisplayTime,
                hasGifDiskCacheStrategy,
                false,
                null,
                true,
                animationResourceId,
                null,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                null,
                baseTarget);
    }

    /**
     * Gif
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param gifDisplayTime
     * @param hasGifDiskCacheStrategy
     * @param animator
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param baseTarget
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     int gifDisplayTime,
                     boolean hasGifDiskCacheStrategy,
                     ViewPropertyTransition.Animator animator,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     BaseTarget baseTarget) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_GIF,
                gifDisplayTime,
                hasGifDiskCacheStrategy,
                false,
                null,
                true,
                Constant.View.DEFAULT_RESOURCE,
                animator,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                null,
                baseTarget);
    }

    /**
     * Gif
     *
     * @param ctx
     * @param originalSource
     * @param gifDisplayTime
     * @param hasGifDiskCacheStrategy
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param view
     */
    public void with(Context ctx,
                     Object originalSource,
                     int gifDisplayTime,
                     boolean hasGifDiskCacheStrategy,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     ImageView view) {
        with(ctx,
                originalSource,
                true,
                gifDisplayTime,
                hasGifDiskCacheStrategy,
                null,
                placeHolderDrawable,
                errorDrawable,
                strategy,
                view);
    }

    /**
     * Gif
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param gifDisplayTime
     * @param hasGifDiskCacheStrategy
     * @param animationResourceId
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param view
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     int gifDisplayTime,
                     boolean hasGifDiskCacheStrategy,
                     int animationResourceId,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     ImageView view) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_GIF,
                gifDisplayTime,
                hasGifDiskCacheStrategy,
                false,
                null,
                true,
                animationResourceId,
                null,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                view,
                null);
    }

    /**
     * Gif
     *
     * @param ctx
     * @param originalSource
     * @param isSkipMemoryCache
     * @param gifDisplayTime
     * @param hasGifDiskCacheStrategy
     * @param animator
     * @param placeHolderDrawable
     * @param errorDrawable
     * @param strategy
     * @param view
     */
    public void with(Context ctx,
                     Object originalSource,
                     boolean isSkipMemoryCache,
                     int gifDisplayTime,
                     boolean hasGifDiskCacheStrategy,
                     ViewPropertyTransition.Animator animator,
                     Drawable placeHolderDrawable,
                     Drawable errorDrawable,
                     DiskCacheStrategy strategy,
                     ImageView view) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                isSkipMemoryCache,
                Constant.View.GLIDE_GIF,
                gifDisplayTime,
                hasGifDiskCacheStrategy,
                false,
                null,
                true,
                Constant.View.DEFAULT_RESOURCE,
                animator,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_SIZE,
                Constant.View.DEFAULT_RESOURCE,
                placeHolderDrawable,
                Constant.View.DEFAULT_RESOURCE,
                errorDrawable,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                view,
                null);
    }

    /**
     * ALL
     *
     * @param ctx                     上下文
     * @param originalSource          原图来源
     * @param thumbnailSource         缩略图来源
     * @param thumbnailScale          缩略图比例
     * @param priority                加载优先级
     * @param isSkipMemoryCache       是否跳过内存缓存
     * @param displayType             显示类型（bitmap／gif）
     * @param gifPlayTime             gif播放次数
     * @param hasGifDiskCacheStrategy 是否缓存gif
     * @param hasTransformation       是否有图片样式转换器
     * @param transformation          图片样式转换器
     * @param hasAnimation            是否有动画
     * @param width                   宽
     * @param height                  高
     * @param placeHolderResourceId   占位图资源ID
     * @param placeHolderDrawable     占位图
     * @param errorResourceId         错误图资源ID
     * @param errorDrawable           错误图
     * @param displayMode             显示模式
     * @param strategy                缓存枚举
     * @param view                    控件
     * @param baseTarget              图片Target回调
     *                                (SimpleTarget/baseTarget/NotificationTarget/AppWidgetTarget)
     */
    public void with(Context ctx,
                     Object originalSource,
                     Object thumbnailSource,
                     int thumbnailScale,
                     Priority priority,
                     boolean isSkipMemoryCache,
                     int displayType,
                     int gifPlayTime,
                     boolean hasGifDiskCacheStrategy,
                     boolean hasTransformation,
                     Transformation<Bitmap> transformation,
                     boolean hasAnimation,
                     int animationResourceId,
                     ViewPropertyTransition.Animator animator,
                     int width,
                     int height,
                     int placeHolderResourceId,
                     Drawable placeHolderDrawable,
                     int errorResourceId,
                     Drawable errorDrawable,
                     int displayMode,
                     DiskCacheStrategy strategy,
                     ImageView view,
                     BaseTarget baseTarget) {
        if (ctx != null && originalSource != null) {
            RequestManager requestManager = Glide.with(ctx);//load(originalSource)
            RequestBuilder requestBuilder = null;
            switch (displayType) {
                case Constant.View.GLIDE_BITMAP:
                    requestBuilder = requestManager.asBitmap();
                    break;
                case Constant.View.GLIDE_GIF:
                    if (hasGifDiskCacheStrategy) {
                        requestBuilder = requestManager.asGif().listener(new GifListenner(gifPlayTime)).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                    } else {
                        requestBuilder = requestManager.asGif().listener(new GifListenner(gifPlayTime)).diskCacheStrategy(DiskCacheStrategy.NONE);
                    }
                    break;
                default:
                    break;
            }

            requestBuilder.load(originalSource);
            if (thumbnailSource != null) {
                requestBuilder.thumbnail(Glide.with(ctx).load(thumbnailSource));
            }
            if (thumbnailScale != Constant.View.DEFAULT_SIZE) {
                requestBuilder.thumbnail(thumbnailScale);
            }
            if (priority != null) {
                requestBuilder.priority(priority);
            }
            requestBuilder.skipMemoryCache(isSkipMemoryCache);
            if (hasTransformation && transformation != null) {
                requestBuilder.transform(transformation);
            } else {
                requestBuilder.dontTransform();
            }
            if (hasAnimation) {
                if (animationResourceId != Constant.View.DEFAULT_SIZE) {
                    requestBuilder.transition(GenericTransitionOptions.with(animationResourceId));
                } else if (animator != null) {
                    requestBuilder.transition(GenericTransitionOptions.with(animator));
                } else {
                    requestBuilder.transition(GenericTransitionOptions.withNoTransition());

                }
            } else {
                requestBuilder.transition(GenericTransitionOptions.withNoTransition());
            }
            if (width != Constant.View.DEFAULT_SIZE && height != Constant.View.DEFAULT_SIZE) {
                requestBuilder.override(width, height);
            }
            if (placeHolderDrawable != null) {
                requestBuilder.placeholder(placeHolderDrawable);
            }
            if (placeHolderResourceId != Constant.View.DEFAULT_RESOURCE) {
                requestBuilder.placeholder(placeHolderResourceId);
            }
            if (errorDrawable != null) {
                requestBuilder.error(errorDrawable);
            }
            if (errorResourceId != Constant.View.DEFAULT_RESOURCE) {
                requestBuilder.error(errorResourceId);
            }
            if (strategy != null) {
                requestBuilder.diskCacheStrategy(strategy);
            }
            if (!hasTransformation) {
                switch (displayMode) {
                    case Constant.View.GLIDE_CENTER_CROP:
                        requestBuilder.centerCrop();
                        break;
                    case Constant.View.GLIDE_FIT_CENTER:
                        requestBuilder.fitCenter();
                        break;
                    default:
                        break;
                }
            }
            if (view != null) {
                requestBuilder.into(view);

            }
            if (baseTarget != null) {
                requestBuilder.into(baseTarget);
            }
        } else {
            LogUtil.Companion.print("Glide with exception");
        }
    }


    /**
     * Bitmap
     *
     * @param context
     * @param source
     * @param aBoolean
     * @param defaultSize1
     * @param o
     * @param isSkipMemoryCache
     * @param glideBitmap
     * @param i
     * @param errorDrawable
     * @param b2
     * @param ctx
     * @param b1
     * @param resource
     * @param animator
     * @param b
     * @param size
     * @param defaultSize
     * @param defaultResource
     * @param originalSource
     * @param width
     * @param height
     * @param strategy
     * @param imageView
     * @param view
     */
    public void with(Context context, Object source, Boolean aBoolean, int defaultSize1, Priority o, boolean isSkipMemoryCache, int glideBitmap, int i, Drawable errorDrawable, boolean b2, Context ctx,
                     boolean b1, int resource, ViewPropertyTransition.Animator animator, boolean b, int size, int defaultSize, int defaultResource, Object originalSource,
                     int width,
                     int height,
                     DiskCacheStrategy strategy,
                     ImageView imageView, ImageView view) {
        with(ctx,
                originalSource,
                null,
                Constant.View.DEFAULT_SIZE,
                null,
                true,
                Constant.View.GLIDE_BITMAP,
                0,
                false,
                false,
                null,
                true,
                Constant.View.DEFAULT_RESOURCE,
                null,
                width,
                height,
                Constant.View.DEFAULT_RESOURCE,
                null,
                Constant.View.DEFAULT_RESOURCE,
                null,
                Constant.View.GLIDE_CENTER_CROP,
                strategy,
                view,
                null);


    }


    private class GifListenner implements RequestListener<GifDrawable> {
        private int loopCount;

        public GifListenner(int loopCount) {
            this.loopCount = loopCount;
        }


        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
            resource.startFromFirstFrame();
            resource.setLoopCount(loopCount);
            resource.stop();
            return false;
        }
    }
}
