package com.qxy.douyin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.qxy.douyin.R;



public class TitleLayout extends RelativeLayout {

    private AppCompatTextView title;
    private AppCompatImageView menu;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_title, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = findViewById(R.id.title);
        menu = findViewById(R.id.menu);
    }

    public void setTitle(CharSequence text) {
        if (null != this.title) {
            this.title.setText(text);
        }
    }

    public void setTitleColor(@ColorInt int color) {
        if (null != title) {
            title.setTextColor(color);
        }
    }
}
