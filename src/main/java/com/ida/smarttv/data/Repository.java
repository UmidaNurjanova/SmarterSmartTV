package com.ida.smarttv.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ida.smarttv.data.remote.client.RetrofitClient;
import com.ida.smarttv.data.remote.model.CreateEventResponse;
import com.ida.smarttv.data.remote.model.EventIdeaResponse;
import com.ida.smarttv.data.remote.model.EventResponse;
import com.ida.smarttv.data.remote.model.LoginModel;
import com.ida.smarttv.data.remote.model.RegisterModel;
import com.ida.smarttv.data.remote.service.ApiService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class Repository {


    private ApiService apiService;
    private RetrofitClient retrofitClient;


    public Repository(Application application) {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }


    public MutableLiveData<RegisterModel> register_owner(String username,
                                                         String password,
                                                         String full_name) {
        final MutableLiveData<RegisterModel> mutableLiveData = new MutableLiveData<>();
        apiService.registerOwner(username, password, full_name).enqueue(new Callback<RegisterModel>() {
            @Override
            @NonNull
            public void onResponse(@NotNull Call<RegisterModel> call, @NotNull Response<RegisterModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<RegisterModel> call, @NotNull Throwable t) {

            }
        });
        return mutableLiveData;
    }


    public MutableLiveData<RegisterModel> register_caregiver(String username,
                                                             String password,
                                                             String full_name,
                                                             String owner_fullname,
                                                             String owner_username) {
        final MutableLiveData<RegisterModel> mutableLiveData = new MutableLiveData<>();
        apiService.registerCareGiver(username, password, full_name, owner_fullname, owner_username).enqueue(new Callback<RegisterModel>() {
            @Override
            @NonNull
            public void onResponse(@NotNull Call<RegisterModel> call, @NotNull Response<RegisterModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<RegisterModel> call, @NotNull Throwable t) {

            }
        });
        return mutableLiveData;
    }


    public MutableLiveData<LoginModel> login(String username,
                                             String password) {
        final MutableLiveData<LoginModel> mutableLiveData = new MutableLiveData<>();
        apiService.loginUser(username, password).enqueue(new Callback<LoginModel>() {
            @Override
            @NonNull
            public void onResponse(@NotNull Call<LoginModel> call, @NotNull Response<LoginModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<LoginModel> call, @NotNull Throwable t) {

            }
        });
        return mutableLiveData;
    }


    public MutableLiveData<EventResponse> showAllEventsForUser(String username) {
        final MutableLiveData<EventResponse> mutableLiveData = new MutableLiveData<>();

        apiService.showEvents(username).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(@NotNull Call<EventResponse> call, @NotNull Response<EventResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<EventResponse> call, @NotNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }


    public void deleteEvent(int id) {
        apiService.deleteEvent(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Timber.d("successfully deleted");
                    } else {

                    }

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });

    }


    public MutableLiveData<CreateEventResponse> createEvent(String event_name, String event_location,
                                                            String event_Date, String full_name,
                                                            String creator_username) {
        final MutableLiveData<CreateEventResponse> mutableLiveData = new MutableLiveData<>();

        apiService.createEvent(event_name, event_location, event_Date, full_name, creator_username).enqueue(new Callback<CreateEventResponse>() {
            @Override
            public void onResponse(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<CreateEventResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });


        return mutableLiveData;
    }


    public MutableLiveData<EventIdeaResponse> showAllEventIdeas() {
        final MutableLiveData<EventIdeaResponse> mutableLiveData = new MutableLiveData<>();

        apiService.showAllEventIdeas().enqueue(new Callback<EventIdeaResponse>() {
            @Override
            public void onResponse(@NotNull Call<EventIdeaResponse> call, @NotNull Response<EventIdeaResponse> response) {

                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<EventIdeaResponse> call, @NotNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }


    public void deleteEventIdea(int id) {
        apiService.deleteEventIdea(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Timber.d("successfully deleted");
                    } else {
                        Timber.d("exception occurred");
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                Timber.d("error occurred");
            }
        });

    }
}

