package com.shineyang.testretrofit.iView;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.shineyang.testretrofit.bean.GitUser;

/**
 * Created by ShineYang on 16/5/12.
 */
public interface MainInfoView extends MvpView {
    void setUserInfo(GitUser user);
    void dismissDialog();
}
