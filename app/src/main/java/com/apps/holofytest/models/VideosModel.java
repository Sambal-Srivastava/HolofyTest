package com.apps.holofytest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosModel {
    @SerializedName("categories")
    @Expose
    public List<Category> categories = null;

    public class Category {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("videos")
        @Expose
        public List<Video> videos = null;

        public class Video {

            @SerializedName("description")
            @Expose
            public String description;
            @SerializedName("sources")
            @Expose
            public List<String> sources = null;
            @SerializedName("subtitle")
            @Expose
            public String subtitle;
            @SerializedName("thumb")
            @Expose
            public String thumb;
            @SerializedName("title")
            @Expose
            public String title;

        }
    }
}
