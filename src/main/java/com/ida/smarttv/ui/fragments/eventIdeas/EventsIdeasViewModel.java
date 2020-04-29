package com.ida.smarttv.ui.fragments.eventIdeas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ida.smarttv.data.Repository;
import com.ida.smarttv.data.remote.model.EventIdeaResponse;

public class EventsIdeasViewModel extends AndroidViewModel {
    Repository repository;

    public EventsIdeasViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public MutableLiveData<EventIdeaResponse> showAllIdeas() {
        return repository.showAllEventIdeas();
    }


    public void deleteIdea(int id) {
        repository.deleteEventIdea(id);
    }

}
