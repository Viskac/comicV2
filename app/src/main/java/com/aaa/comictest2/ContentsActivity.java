package com.aaa.comictest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aaa.comictest2.bean.ComicJson;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ContentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        //1.读取传来的数据comicName
        Intent intent = getIntent();
        String comicName = intent.getStringExtra("comicName");

        //通过comicName读取Assets里的json文件，拿到ComicJson对象
        String s = readJson(comicName);
        ComicJson comicJson = JSON.parseObject(s, ComicJson.class);

        //2.ComicJson.getChapterNames();得到list
        ArrayList<String> chapterNames = comicJson.getChapterNames();

        //3.设置ListView
        ListView listView = findViewById(R.id.lv_01);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chapterNames));

        //4.点击事件：跳转到MainActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ContentsActivity.this, MainActivity.class);
                //打包数据
                LinkedHashMap<String, ArrayList<String>> chapters = comicJson.getChapters();
                ArrayList<String> urlList = chapters.get(chapterNames.get(position));

                //intent.putExtra("list",(Parcelable) urlList);
                intent.putStringArrayListExtra("list", urlList);
                //提交数据
                startActivity(intent);

            }
        });

    }

    //读取assets里的json文件
    public String readJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            //读取assets里的文件
            AssetManager am = getResources().getAssets();
            inputStream = am.open("ttt/"+fileName +".json");
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
        return stringBuilder.toString();
    }
}