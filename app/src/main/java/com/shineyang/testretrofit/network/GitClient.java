package com.shineyang.testretrofit.network;

import com.shineyang.testretrofit.bean.GitUser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ShineYang on 16/5/10.
 */
public interface GitClient {
    @GET("/users/{user}")      // here is the other url part.best way is to start using /
    Call<GitUser> getFeed(@Path("user") String user);

}
