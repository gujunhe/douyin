package com.qxy.douyin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qxy.douyin.R;

public class ProfilemoreLayout extends FrameLayout {

    public ProfilemoreLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProfilemoreLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ProfilemoreLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_profilemore, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}

