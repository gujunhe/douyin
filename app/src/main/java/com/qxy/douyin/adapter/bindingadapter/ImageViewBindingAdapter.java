package com.qxy.douyin.adapter.bindingadapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.qxy.douyin.R;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"image","defaultImageResource"},requireAll = false)
    public  static  void setImage(ImageView imageView,String imageUrl,int imageResource)
    {
        if(!TextUtils.isEmpty(imageUrl))
        {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);
        }
        else
        {
            imageView.setImageResource(imageResource);
        }
    }
}
