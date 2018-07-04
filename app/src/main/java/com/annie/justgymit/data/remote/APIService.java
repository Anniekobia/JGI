package com.annie.justgymit.data.remote;


import com.annie.justgymit.data.model.LoginPost;
import com.annie.justgymit.data.model.RegisterPost;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("/api/AppUser")
    @FormUrlEncoded
    Call<RegisterPost> savePost(@Field("firstname") String firstname,
                                @Field("lastname") String lastname,
                                @Field("email") String email,
                                @Field("password") String password);
    @POST("api/authenticate")
    @FormUrlEncoded
    Call<LoginPost> saveLoginPost(@Field("email") String email,
                                  @Field("password") String password);

}
