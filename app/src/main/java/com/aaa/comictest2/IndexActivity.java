package com.aaa.comictest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import com.aaa.comictest2.adapter.IndexAdapter;
import com.aaa.comictest2.bean.IndexJson;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //1.读取json转为对象，
        String s = readJson();
        IndexJson indexJson = JSON.parseObject(s, IndexJson.class);

        //2.拿到comicNameList和coverPicMap
        ArrayList<String> comicNameList = indexJson.getComicNames();
        LinkedHashMap<String, String> coverPicMap = indexJson.getCoverPics();

        //3.设置RecyclerView网格瀑布流
        RecyclerView recyclerView = findViewById(R.id.rv_index);
        //以哪种形式展示RecyclerView

        //(2)网格布局
        // spanCount是每行显示几个(此处变化后也要在IndexAdapter中修改图片宽度)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        //4.设置Adapter
        IndexAdapter indexAdapter = new IndexAdapter(comicNameList, coverPicMap, this);
        recyclerView.setAdapter(indexAdapter);

        //5.传递数据（ComicName）
        //通过recyclerView给item绑定点击事件
        indexAdapter.setRecyclerItemClickListener(new IndexAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                //点击后，传递漫画名到ContentsActivity
                Intent intent = new Intent(IndexActivity.this, ContentsActivity.class);
                intent.putExtra("comicName", comicNameList.get(position));
                startActivity(intent);
            }
        });
    }

    //读取assets里的json文件
    public String readJson() {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            //读取assets里的文件
            AssetManager am = getResources().getAssets();
            inputStream = am.open("ttt/index.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //读取json文件里的内容
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = stringBuilder.toString();
        return s;
    }
}