package com.shineyang.testretrofit.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.shineyang.testretrofit.bean.GitUser;
import com.shineyang.testretrofit.iView.MainInfoView;
import com.shineyang.testretrofit.network.GitClient;
import com.shineyang.testretrofit.network.GitRetrofit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ShineYang on 16/5/11.
 */
public class MainPresenter extends MvpBasePresenter<MainInfoView> {
    public void setInfo(String username) {
        final MainInfoView view = getView();
        if (view != null) {
            GitRetrofit retrofit = new GitRetrofit();
            GitClient client = retrofit.getGitClient();
            Call<GitUser> call = client.getFeed(username);
            call.enqueue(new Callback<GitUser>() {
                @Override
                public void onResponse(Response<GitUser> response, Retrofit retrofit) {
                    Log.e("presenter", "method -onResponse- called");
                    GitUser user = response.body();
                    view.setUserInfo(user);
                    view.dismissDialog();
                }

                @Override
                public void onFailure(Throwable t) {
                    view.dismissDialog();
                }
            });
        } else {
            Log.e("presenter", "view = null");
        }

    }

}
