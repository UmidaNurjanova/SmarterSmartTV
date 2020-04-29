package com.ida.smarttv.ui.fragments.addEvent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ida.smarttv.data.Repository;
import com.ida.smarttv.data.remote.model.CreateEventResponse;

public class AddEventViewModel extends AndroidViewModel {
    private Repository repository;

    public AddEventViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public MutableLiveData<CreateEventResponse> createEvent(String event_name, String location, String date, String fullName, String creatorName) {
        return repository.createEvent(event_name, location, date, fullName, creatorName);
    }

}
