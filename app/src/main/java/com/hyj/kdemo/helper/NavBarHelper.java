package com.hyj.kdemo.helper;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyj.kdemo.R;


/**
 * Created by ruoyun on 2017/4/18.
 * 公用的标题帮助类
 */
public class NavBarHelper {

    private View view;
    private View.OnClickListener onClickListener;
    private ImageView mImLeftIcon;
    private TextView mTvCenter;

    private NavBarHelper(Builder builder) {
        this.view = builder.view;
        this.onClickListener = builder.onClickListener;
    }

    private void initView() {
        mImLeftIcon = (ImageView) view.findViewById(R.id.mImLeftIcon);
        mTvCenter = (TextView) view.findViewById(R.id.mTvCenter);
    }

    private void setLeftIcon(int id) {
        if (id == 0) {
            mImLeftIcon.setImageDrawable(null);
        } else {
            mImLeftIcon.setVisibility(View.VISIBLE);
            mImLeftIcon.setImageResource(id);
            if (onClickListener != null)
                mImLeftIcon.setOnClickListener(onClickListener);
        }
    }

    public void setLeftVisible(boolean visible) {
        if (visible) {
            mImLeftIcon.setVisibility(View.VISIBLE);
        } else {
            mImLeftIcon.setVisibility(View.INVISIBLE);
        }
    }

    private void setCenterName(String name) {
        if (!TextUtils.isEmpty(name)) {
            mTvCenter.setVisibility(View.VISIBLE);
            mTvCenter.setText(name);
        }
    }


    public static class Builder {
        private View.OnClickListener onClickListener;
        private String centerString;
        private int leftIcon = 0;
        private View view;

        public Builder builderToolbar(View view) {
            this.view = view;
            return this;
        }

        public Builder builderClickLister(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder builderCenterString(String name) {
            this.centerString = name;
            return this;
        }

        public Builder builderLeftIcon(int id) {
            this.leftIcon = id;
            return this;
        }

        public NavBarHelper create() {
            return new NavBarHelper(this);
        }

        public NavBarHelper createNormal() {
            NavBarHelper helper = new NavBarHelper(this);
            helper.initView();
            helper.setLeftIcon(leftIcon);
            helper.setCenterName(centerString);
            return helper;
        }
    }
}
