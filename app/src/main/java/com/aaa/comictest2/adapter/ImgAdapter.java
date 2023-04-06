package com.aaa.comictest2.adapter;

import android.content.Context;
import android.graphics.Bitmap;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaa.comictest2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ImgViewHolder> {
    private ArrayList<String> urlList;
    private Context context;

    public ImgAdapter(ArrayList<String> urlList, Context context) {
        this.urlList = urlList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获取itemView容器
        View view = View.inflate(context, R.layout.activity_img_item, null);
        return new ImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgViewHolder holder, int position) {
        //给已经放进viewHolder里的imageview绑定数据
        String url = urlList.get(position);
        useGlide(url, holder.iv);

    }

    @Override
    public int getItemCount() {
        return urlList == null ? 0 : urlList.size();
    }

    public class ImgViewHolder extends RecyclerView.ViewHolder {
        //获取itemView容器里的TextView布局
        private ImageView iv;

        public ImgViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_01);

        }
    }

    //外部。Glide加载
    public void useGlide(String url, ImageView imageView) {

        //使用ARGB_8888加载图片更细腻
//        RequestOptions options = new RequestOptions()
//                .format(DecodeFormat.PREFER_ARGB_8888)
//                .placeholder(R.drawable.loading);

        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                        int imageWidth = resource.getWidth();//原图的宽
                        int imageHeight = resource.getHeight();//原图的高

                        int screenWidth = 1080;//手机屏幕的宽度
                        int height = screenWidth * imageHeight / imageWidth;

                        ViewGroup.LayoutParams para = imageView.getLayoutParams();
                        para.width = screenWidth;
                        para.height = height;
//                        imageView.setImageBitmap(resource);
//                        Glide.with(context)
//                                .load(url)
//                                .into(imageView);
                    }
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                    }
                });

        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }

}
