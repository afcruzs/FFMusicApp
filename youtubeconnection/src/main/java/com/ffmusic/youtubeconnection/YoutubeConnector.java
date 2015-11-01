package com.ffmusic.youtubeconnection;

import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YoutubeConnector {
    private YouTube youtube;
    private YouTube.Search.List query;

    private final String TAG = "youtube";
    private final long MAX_RESULTS = 15;
    private final String FIELDS = "items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)";
    private final String TYPE = "video";

    public static final String BROWSER_KEY
            = "AIzaSyDCpsDFxQ7ZTkClA4ArwqKk9FBgSqomjxM";

    /*
    * Initializes youtube instance
    * Sets configurations for query instance
    * */
    public YoutubeConnector(String applicationName) {
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName(applicationName).build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(BROWSER_KEY);
            query.setType(TYPE);
            query.setFields(FIELDS);
            query.setMaxResults(MAX_RESULTS);
        }catch(IOException e){
            Log.d(TAG, "Could not initialize: " + e);
        }
    }

    /*
    * Searches a String on Youtube
    * */
    public List<VideoItem> search(String keywords){
        query.setQ(keywords);
        try{
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            List<VideoItem> items = new ArrayList<VideoItem>();
            for(SearchResult result : results){
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }
            return items;
        }catch(IOException e){
            Log.d(TAG, "Could not search: " + e);
            return null;
        }
    }
}