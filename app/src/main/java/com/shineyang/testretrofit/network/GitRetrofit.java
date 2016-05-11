package com.shineyang.testretrofit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by ShineYang on 16/5/10.
 */
public class GitRetrofit {
    public static final String BASE_URL = "https://api.github.com";
    private static GitClient client;
    private static Retrofit retrofit;

    static {
        Gson gson = new GsonBuilder()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public GitClient getGitClient() {
        if (client == null) {
            client = retrofit.create(GitClient.class);
        }
        return client;
    }

}
