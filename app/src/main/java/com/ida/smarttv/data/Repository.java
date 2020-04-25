package com.ida.smarttv.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.ida.smarttv.data.remote.client.RetrofitClient;
import com.ida.smarttv.data.remote.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Repository {


    private ApiService apiService;
    private RetrofitClient retrofitClient;


    public Repository(Application application) {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }


}
