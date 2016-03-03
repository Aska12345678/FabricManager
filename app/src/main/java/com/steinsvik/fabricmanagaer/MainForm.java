package com.steinsvik.fabricmanagaer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.steinsvik.fabricmanagaer.adapter.TabAdapter;
import com.steinsvik.fabricmanagaer.fragment.NavigationFragment;
import com.steinsvik.fabricmanagaer.tabs.MyTabLayout;

public class MainForm extends ActionBarActivity {

    private final int Activity_Index = 1;
    private ViewPager mViewPager;
    private MyTabLayout myTabLayout;
    private TabAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationFragment navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_Fragment);
        navigationFragment.setUp(R.id.navigation_Fragment, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpaper_main);
        myTabLayout = (MyTabLayout) findViewById(R.id.tab_layout);

        mTabAdapter = new TabAdapter(getFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        myTabLayout.createTabs();
        myTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                myTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        GraphView graphView = (GraphView) findViewById(R.id.graph);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
//                new DataPoint(0,1),
//                new DataPoint(1,2),
//                new DataPoint(1,6),
//                new DataPoint(2,5),
//                new DataPoint(3,4),
//                new DataPoint(5,3),
//                new DataPoint(6,8),
//                new DataPoint(9,3),
//                new DataPoint(10,12),
//                new DataPoint(11,3),
//                new DataPoint(14,4),
//        });
//        series.setThickness(1);
////        series.setDrawDataPoints(true);
////        series.setDataPointsRadius(10);
//        graphView.addSeries(series);
//        series.setColor(Color.GREEN);

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK)
//            finish();
//        return super.onKeyDown(keyCode, event);
//    }
}
