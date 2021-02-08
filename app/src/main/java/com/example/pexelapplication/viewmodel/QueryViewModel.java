package com.example.pexelapplication.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class QueryViewModel extends AndroidViewModel {

    private static final String STRING_SET_DATA_KEY = "STRING_SET_DATA_KEY";
    private SharedPreferences sharedPref = getApplication().getSharedPreferences(
            getApplication().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    private SharedPreferences.Editor editor = sharedPref.edit();

    private MutableLiveData<Set<Photo>> _recentPhotos = new MutableLiveData<>();

    public QueryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Set<Photo>> getRecentPhotos() {
        return _recentPhotos;
    }

    public void retrieveRecentPhoto() {
        String serializedObject = sharedPref.getString(STRING_SET_DATA_KEY, "");

        if(serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<LinkedHashSet<Photo>>(){}.getType();
            Set<Photo> list = gson.fromJson(serializedObject, type);

            _recentPhotos.setValue(gson.fromJson(serializedObject, type));
        }
    }

    public void addToRecentPhotos(Photo photo) {
        retrieveRecentPhoto();

        if(_recentPhotos.getValue() == null)
            _recentPhotos.setValue(new HashSet<>());

        Set<Photo> temp = getRecentPhotos().getValue();
        temp.add(photo);
        _recentPhotos.setValue(temp);
        String val = _recentPhotos.getValue().toString();
        saveToPref(_recentPhotos.getValue());
        retrieveRecentPhoto();
    }

//    public void deleteFromFavorite(Photo photo) {
//        String photoJson = convertPhotoToJsonString(photos);
//        sharedPref.getStringSet(STRING_SET_DATA_KEY, null).remove(photoJson);
//        _favoritePhotos.getValue().remove(photo);
//    }

    private void saveToPref(Set<Photo> photos) {
        Gson gson = new Gson();
        String photosJson = gson.toJson(photos);
        editor.putString(STRING_SET_DATA_KEY, photosJson).apply();
    }
}
