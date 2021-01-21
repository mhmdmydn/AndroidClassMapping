package com.del.androidclasses.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.del.androidclasses.R;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import com.del.androidclasses.adapters.FragmentsTabAdapter;
import com.del.androidclasses.fragments.FragementAndroidx;
import com.del.androidclasses.fragments.FragmentSupport;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
	private TabLayout tabs;
	private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); 
		initLogic();
		setSupportActionBar(toolbar);
    }
    private void initView(){
    toolbar=(Toolbar)findViewById(R.id.toolbar);
	tabs=(TabLayout)findViewById(R.id.tabs);
	viewPager = (ViewPager)findViewById(R.id.view_pager);
    }
	
	private void initLogic(){
		addFragmentsToViewPager(viewPager);
		tabs.setupWithViewPager(viewPager);
	}
	
	
	private void addFragmentsToViewPager(ViewPager viewPager) {
        FragmentsTabAdapter adapter = new FragmentsTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragementAndroidx(), "AndroidX");
        adapter.addFragment(new FragmentSupport(), "Support");
        viewPager.setAdapter(adapter);
    }
	
}
