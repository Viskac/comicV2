package com.aaa.comictest2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.aaa.comictest2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.IndexViewHolder> {
    private ArrayList<String> comicNameList;
    private LinkedHashMap<String, String> coverPicMap;
    private Context context;

    //构造方法，用户外部传值进来
    public IndexAdapter(ArrayList<String> comicNameList, LinkedHashMap<String, String> coverPicMap, Context context) {
        this.comicNameList = comicNameList;
        this.coverPicMap = coverPicMap;
        this.context = context;
    }

    @NonNull
    @Override
    public IndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.activity_index_item, null);
        return new IndexViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexAdapter.IndexViewHolder holder, int position) {
        //获取漫画名
        String comicName = comicNameList.get(position);
        System.out.println("123 --- position:" + position);
        //获取封面
        String coverUrl = coverPicMap.get(comicName);

        //给已经放进viewHolder里的imageview绑定数据
        useGlide(coverUrl, holder.imageView);
        //给已经放进viewHolder里的textview绑定数据
        holder.textView.setText(comicName);
    }

    @Override
    public int getItemCount() {
        return comicNameList == null ? 0 : comicNameList.size();
    }

    public class IndexViewHolder extends RecyclerView.ViewHolder {
        //获取itemView容器里的View布局
        //放封面
        private ImageView imageView;
        //放漫画名
        private TextView textView;

        public IndexViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_index);
            textView = itemView.findViewById(R.id.tv_index);

            //设置RecyclerView的item的点击事件：
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myOnItemClickListener != null) {
                        myOnItemClickListener.onRecyclerItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    //设置RecyclerView的item的点击事件：
    private OnRecyclerItemClickListener myOnItemClickListener;

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        myOnItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }

    //外部。Glide加载
    public void useGlide(String url, ImageView imageView) {
        //使用ARGB_8888加载图片更细腻
        RequestOptions options = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888);

        Glide.with(context)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                        int imageWidth = resource.getWidth();//原图的宽
                        int imageHeight = resource.getHeight();//原图的高

                        int screenWidth = 1080/3; //手机屏幕的宽度
                        int height = screenWidth * imageHeight / imageWidth;

                        ViewGroup.LayoutParams para = imageView.getLayoutParams();
                        para.height = height;
                        para.width = screenWidth;
                        imageView.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);

                    }

                });
    }
}
