package com.shineyang.testretrofit.view;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.shineyang.testretrofit.R;
import com.shineyang.testretrofit.bean.GitUser;
import com.shineyang.testretrofit.iView.MainInfoView;
import com.shineyang.testretrofit.presenter.MainPresenter;


public class MainActivity extends MvpActivity<MainInfoView, MainPresenter> implements MainInfoView {
    EditText et_username;
    Button btn_search;
    ProgressDialog proDialog;
    TextView tv_username, tv_id, tv_follower, tv_careated_at;
    SimpleDraweeView iv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        initView();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    public void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        btn_search = (Button) findViewById(R.id.btn_search);
        proDialog = new ProgressDialog(MainActivity.this);
        proDialog.setMessage("loading...");

        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_follower = (TextView) findViewById(R.id.tv_follower);
        tv_careated_at = (TextView) findViewById(R.id.tv_created_at);
        iv_head = (SimpleDraweeView) findViewById(R.id.iv_head_view);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                proDialog.show();
                getPresenter().setInfo(username);
            }
        });

    }

    @Override
    public void setUserInfo(GitUser user) {
        tv_username.setText("name: " + user.getName());
        tv_id.setText("id: " + user.getId());
        tv_careated_at.setText("created at: " + user.getCreated_at().substring(0, 10));
        tv_follower.setText("follower: " + user.getFollowers());
        Uri head_uri = Uri.parse(user.getAvatar_url());
        iv_head.setImageURI(head_uri);
    }

    @Override
    public void dismissDialog() {
        proDialog.dismiss();
    }
}
