package com.aaa.comictest2.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class IndexJson {
    private ArrayList<String> ComicNames;
    private LinkedHashMap<String,String> CoverPics;

    @Override
    public String toString() {
        return "IndexJson{" +
                "ComicName=" + ComicNames +
                ", CoverPic=" + CoverPics +
                '}';
    }

    public ArrayList<String> getComicNames() {
        return ComicNames;
    }

    public void setComicNames(ArrayList<String> comicNames) {
        ComicNames = comicNames;
    }

    public LinkedHashMap<String, String> getCoverPics() {
        return CoverPics;
    }

    public void setCoverPics(LinkedHashMap<String, String> coverPics) {
        CoverPics = coverPics;
    }
}
