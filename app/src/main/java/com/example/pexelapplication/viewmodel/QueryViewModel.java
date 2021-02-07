package com.example.pexelapplication.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pexelapplication.R;
import com.example.pexelapplication.model.Photo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QueryViewModel extends AndroidViewModel {

    private static final String STRING_SET_DATA_KEY = "STRING_SET_DATA_KEY";
    private SharedPreferences sharedPref = getApplication().getSharedPreferences(
            getApplication().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    private SharedPreferences.Editor editor = sharedPref.edit();

    private MutableLiveData<List<Photo>> _favoritePhotos = new MutableLiveData<>();

    public QueryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Photo>> getFavoritePhotos() {
        return _favoritePhotos;
    }

    public void  setFavoritePhotos() {
        String serializedObject = sharedPref.getString(STRING_SET_DATA_KEY, "");

        if(serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Photo>>(){}.getType();
            List<Photo> list = gson.fromJson(serializedObject, type);

            _favoritePhotos.setValue(gson.fromJson(serializedObject, type));
        }
    }

    public void addToFavorite(Photo photo) {
        setFavoritePhotos();

        if(_favoritePhotos.getValue() == null)
            _favoritePhotos.setValue(new ArrayList<>());

        List<Photo> temp = getFavoritePhotos().getValue();
        temp.add(photo);
        _favoritePhotos.setValue(temp);
        String val = _favoritePhotos.getValue().toString();
        saveToPref(_favoritePhotos.getValue());
        setFavoritePhotos();
    }

    public void deleteFromFavorite(Photo photo) {
//        String photoJson = convertPhotoToJsonString(photos);
//        sharedPref.getStringSet(STRING_SET_DATA_KEY, null).remove(photoJson);
//        _favoritePhotos.getValue().remove(photo);
    }

    private void saveToPref(List<Photo> photos) {
        Gson gson = new Gson();
        String photosJson = gson.toJson(photos);
        editor.putString(STRING_SET_DATA_KEY, photosJson).apply();
    }
}
