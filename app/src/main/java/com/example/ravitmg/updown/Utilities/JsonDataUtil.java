package com.example.ravitmg.updown.Utilities;

import android.content.Context;
import android.util.Log;

import com.example.ravitmg.updown.Model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ravitmg on 31/01/18.
 */

public class JsonDataUtil {
    /*public static ArrayList<Song> getJsonForGame(Context context, String category) {
        Log.e("filename",category);
        ArrayList<Song> songArrayList = new ArrayList<>();
        try {
            String jsonString = loadJSONFromAsset(context, category);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonObject1 = jsonObject.getJSONObject("release");
                Song songModel = new Song(jsonObject.getString("sid"),
                        jsonObject.getString("name"), jsonObject1.getInt("year"),
                        jsonObject1.getInt("month"), jsonObject1.getInt("day"),
                        jsonObject.getInt("prediction"));
                songArrayList.add(songModel);
            }
        } catch (JSONException e) {
            Log.e("Json Exception:", e.getMessage() + "");
        }
        Collections.sort(songArrayList, new SortComparator());
        return songArrayList;
    }

  */

    public static class SortComparator implements Comparator<Song> {
        @Override
        public int compare(Song o1, Song o2) {

            return o1.getPrediction() - o2.getPrediction();

        }
    }

    private static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
