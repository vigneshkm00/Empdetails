package com.vignesh.employeedetails.Interface;

import com.vignesh.employeedetails.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<List<User>> getUser();

    @GET("users")
    Call<Object> getUserOne(@Query("id") int id);
}
