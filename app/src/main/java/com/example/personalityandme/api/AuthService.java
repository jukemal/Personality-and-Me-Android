package com.example.personalityandme.api;

import com.example.personalityandme.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface AuthService {
    @POST("auth/login/")
    public Call<User> login(@Body User user);

    @POST("auth/register/")
    public Call<User> register(@Body User user);

    @POST("auth/logout/")
    public Call<Object> logout();

    @GET("auth/current")
    public Call<User> current_user();
}
