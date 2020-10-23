package com.demo.btvideo.view.activity;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.demo.btvideo.R;
import com.demo.btvideo.view.fragment.FragmentIndex;
import com.demo.btvideo.view.fragment.FragmentUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.main_toolbar)
	Toolbar toolbar;
	@BindView(R.id.bottom_navi)
	BottomNavigationView bottomNavigationView;
	@BindView(R.id.main_viewpager)
	ViewPager viewPager;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			@NonNull
			@Override
			public Fragment getItem(int position) {
				if (position == 0) {
					return FragmentIndex.getInstance();
				} else {
					return FragmentUser.getInstance();
				}
			}

			@Override
			public int getCount() {
				return 2;
			}
		});

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(position).getItemId());

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {

				if (item.getItemId()==R.id.menu_index){
					viewPager.setCurrentItem(0);
							getSupportActionBar().setTitle("首页");
				}else if (item.getItemId()==R.id.menu_user_index){
					viewPager.setCurrentItem(1);
												getSupportActionBar().setTitle("个人中心");

				}
				return true;
			}
		});
	}
}