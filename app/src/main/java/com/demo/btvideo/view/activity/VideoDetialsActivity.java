package com.demo.btvideo.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.demo.btvideo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import tcking.github.com.giraffeplayer2.PlayerManager;
import tcking.github.com.giraffeplayer2.VideoView;

public class VideoDetialsActivity extends AppCompatActivity {

	private PowerManager.WakeLock wakeLock;
	View rootView;
	VideoView videoView;

	@BindView(R.id.mainToolBar)
	Toolbar toolbar;
	@BindView(R.id.collapsintLayout)
	CollapsingToolbarLayout collapsingToolbarLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				Window window = getWindow();
				WindowManager.LayoutParams attributes = window.getAttributes();
				attributes.flags |= flagTranslucentNavigation;
				window.setAttributes(attributes);
				getWindow().setStatusBarColor(Color.TRANSPARENT);
			} else {
				Window window = getWindow();
				WindowManager.LayoutParams attributes = window.getAttributes();
				attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
				window.setAttributes(attributes);
			}
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
			}
		}
		setContentView(R.layout.activity_video_detail);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.TRANSPARENT);
		collapsingToolbarLayout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));      // 设置展开时标题颜色为透明,避免标题覆盖视频
		collapsingToolbarLayout.setExpandedTitleGravity(Gravity.TOP);       //标题置顶

		//有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
		String url1 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
		videoView =findViewById(R.id.video_view);
		videoView.setVideoPath(url1).getPlayer().start();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		PlayerManager.getInstance().onConfigurationChanged(newConfig);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus){
			PlayerManager.getInstance().getCurrentPlayer().pause();
		}else{
			//失去焦点
			PlayerManager.getInstance().getCurrentPlayer().start();

		}
	}

	@Override
	public void onBackPressed() {
		if (!PlayerManager.getInstance().onBackPressed()){
			super.onBackPressed();

		}
	}

	public void btnLogin(View view) {
		startActivity(new Intent(this,LoginActivity.class));
	}
}