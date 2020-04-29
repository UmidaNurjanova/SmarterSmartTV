package com.ida.smarttv.data.remote.service;


import com.ida.smarttv.data.remote.model.CreateEventResponse;
import com.ida.smarttv.data.remote.model.EventIdeaResponse;
import com.ida.smarttv.data.remote.model.EventResponse;
import com.ida.smarttv.data.remote.model.LoginModel;
import com.ida.smarttv.data.remote.model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //only two types of role are there
    // one role = tv_user
    //or
    //role = caregiver

    @GET("tv_register.php?role=tv_user")
    Call<RegisterModel> registerOwner(@Query("username") String username,
                                      @Query("password") String password,
                                      @Query("fullname") String full_name
    );


    @GET("tv_register.php?role=caregiver")
    Call<RegisterModel> registerCareGiver(@Query("username") String username,
                                          @Query("password") String password,
                                          @Query("fullname") String full_name,
                                          @Query("owner_fullname") String owner_fullname,
                                          @Query("owner_username") String owner_username
    );


    @GET("tv_login.php?")
    Call<LoginModel> loginUser(@Query("username") String username,
                               @Query("password") String password
    );


    @GET("tv_selectevents.php?")
    Call<EventResponse> showEvents(@Query("username") String username);


    @GET("tv_deleteevents.php?")
    Call<Void> deleteEvent(@Query("id") int id);

    @GET("tv_events.php?")
    Call<CreateEventResponse> createEvent(@Query("eventname") String event_name,
                                          @Query("eventlocation") String even_location,
                                          @Query("eventdate") String event_date,
                                          @Query("fullname") String full_name,
                                          @Query("creator_username") String creator_username);


    @GET("tv_selectideas.php?owner_username=fkjones")
    Call<EventIdeaResponse> showAllEventIdeas();

    @GET("tv_deleteeventidea.php?")
    Call<Void> deleteEventIdea(@Query("id") int id);

}
