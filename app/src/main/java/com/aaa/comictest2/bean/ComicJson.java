package com.aaa.comictest2.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ComicJson {
    private String ComicName;
    private String CoverPic;
    private ArrayList<String> ChapterNames ;
    private LinkedHashMap<String,ArrayList<String>> Chapters;

    @Override
    public String toString() {
        return "ComicJson{" +
                "ComicName='" + ComicName + '\'' +
                ", CoverPic='" + CoverPic + '\'' +
                ", ChapterNames=" + ChapterNames +
                ", Chapters=" + Chapters +
                '}';
    }

    public String getComicName() {
        return ComicName;
    }

    public void setComicName(String comicName) {
        ComicName = comicName;
    }

    public String getCoverPic() {
        return CoverPic;
    }

    public void setCoverPic(String coverPic) {
        CoverPic = coverPic;
    }

    public ArrayList<String> getChapterNames() {
        return ChapterNames;
    }

    public void setChapterNames(ArrayList<String> chapterNames) {
        ChapterNames = chapterNames;
    }

    public LinkedHashMap<String, ArrayList<String>> getChapters() {
        return Chapters;
    }

    public void setChapters(LinkedHashMap<String, ArrayList<String>> chapters) {
        Chapters = chapters;
    }

    //    private ArrayList<String> chapter;
//
//    @Override
//    public String toString() {
//        return "ComicJson{" +
//                "chapter=" + chapter +
//                '}';
//    }
//
//    public ArrayList<String> getChapter() {
//        return chapter;
//    }
//
//    public void setChapter(ArrayList<String> chapter) {
//        this.chapter = chapter;
//    }
}
