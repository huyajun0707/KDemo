package com.renmai.component.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.network.library.utils.LogUtil;
import com.renmai.component.R;


/**
 * 自定义toast类
 * Created by puhui-hebin on 2017/5/23 16:20.
 * 修改：华为8.0系统不能显示，因为每个toast都有一个token,所以不能复用
 */
public class MToast {
    public static final int TIPS = 1;//正常提示类
    public static final int ERROR = 2;//错误提示类
    public static final int SUCCESS = 3;//成功提示类

    private static ShowView showView;

    //直接传入字符串
    private static void show(String message, int type) {
        try {
            Toast mToast = new Toast(AppUtil.getInstance().getApplication());
            if (showView == null) {
                showView = new ShowView();
                showView.init();
            }
            mToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);//DensityUtil.getScreenHeight()/2-DensityUtil.dip2px(BaseApplication.getInstance(),35)
            mToast.setView(showView.view);
            mToast.setDuration(Toast.LENGTH_SHORT);
            showView.content_tv.setText(message);
            if (type == TIPS) {
                showView.imageView.setImageResource(R.drawable.icon_submit_prompt);
            } else if (type == ERROR) {
                showView.imageView.setImageResource(R.drawable.icon_submit_failure);
            } else if (type == SUCCESS) {
                showView.imageView.setImageResource(R.drawable.icon_submit_success);
            }
            mToast.show();
        } catch (Exception e) {
            LogUtil.Companion.e(e);
            Toast.makeText(AppUtil.getInstance().getApplication(), message, Toast.LENGTH_SHORT).show();
        }
    }

    //正常提示类
    public static void showNormal(@NonNull String message) {
        show(message, TIPS);
    }

    //错误提示类
    public static void showError(@NonNull String message) {
        show(message, ERROR);
    }

    //成功提示类
    public static void showSuccess(@NonNull String message) {
        show(message, SUCCESS);
    }


    static class ShowView {
        protected TextView content_tv;
        protected ImageView imageView;
        protected View view;

        public void init() {
            /**
             * 下面这一行，有时候在手机内存不足时候，会抛出异常说是xml有问题不能inflate，可是xml是没有问题的
             * 所以在发生问题时候，就用系统原生的toast吧
             */
            view = LayoutInflater.from(AppUtil.getInstance().getApplication()).inflate(R.layout.toast_layout, null);
            view.getBackground().setAlpha(204);
            content_tv = (TextView) view.findViewById(R.id.content_tv);
            imageView = (ImageView) view.findViewById(R.id.tipsIv);
        }
    }
}
